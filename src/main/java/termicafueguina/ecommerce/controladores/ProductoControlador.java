package termicafueguina.ecommerce.controladores;


import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import termicafueguina.ecommerce.dtos.CarritoCompraDTO;
import termicafueguina.ecommerce.dtos.ProductoM2DTO;
import termicafueguina.ecommerce.dtos.ProductoUniDTO;
import termicafueguina.ecommerce.modelos.*;
import termicafueguina.ecommerce.servicios.*;
import termicafueguina.ecommerce.utilidades.NumerosUtilidad;
import termicafueguina.ecommerce.utilidades.PagarConTarjetaUtilidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class ProductoControlador {
    @Autowired
    private ClienteServicio clienteServicio;
    @Autowired
    private ProductoM2Servicio productoM2Servicio;
    @Autowired
    private ProductoUniServicio productoUniServicio;
    @Autowired
    private OrdenServicio ordenServicio;
    @Autowired
    private OrdenProductoUniServicio ordenProductoUniServicio;
    @Autowired
    private OrdenProductoM2Servicio ordenProductoM2Servicio;
    @Autowired
    private EmailService emailService;
    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/api/productoM2")
    public List<ProductoM2DTO> getProductoM2() {
        return productoM2Servicio.getProductoM2().stream().filter(productoM2 -> productoM2.getEstaActivo()).collect(Collectors.toList());
    }

    @GetMapping("/api/productoUni")
    public List<ProductoUniDTO> getProductoUni() {
        return productoUniServicio.getProductoUni().stream().filter(productoUni -> productoUni.getEstaActivo()).collect(Collectors.toList());
    }

    @GetMapping("/api/tipos-producto")
    public List<TipoProducto> getTiposDeProducto() {
        return TipoProducto.obtenerTiposDeProducto();
    }

    @GetMapping("/api/pais-producto")
    public List<PaisProducto> getPaaisProducto() {
        return PaisProducto.obtenerPaisProducto();
    }

    @Transactional
    @PostMapping("/api/carrito-compra")
    public ResponseEntity<Object> compraProducto(HttpServletResponse response, Authentication authentication, @RequestBody CarritoCompraDTO carritoCompraDTO) {

        Cliente cliente = clienteServicio.findByEmail(authentication.getName());
        String numero;

        do {
            numero = NumerosUtilidad.getNumero();
        } while (ordenServicio.findByNumeroDeOrden(numero) != null);

        if (cliente == null) {
            return new ResponseEntity<>("Tienes que estar logueado para realizar esta acción", HttpStatus.FORBIDDEN);
        }
        if (carritoCompraDTO.getProductos().size() == 0) {
            return new ResponseEntity<>("Tienes que agregar productos al carrito", HttpStatus.FORBIDDEN);
        }


        String mailUsuario = cliente.getEmail();

        Double totalCompra = 0.0;

        Orden orden = (new Orden(LocalDateTime.now(), false, true, totalCompra, numero));
        cliente.addOrden(orden);
        ordenServicio.saveNewOrden(orden);


        for (int i = 0; i < carritoCompraDTO.getProductos().size(); i++) {
            String entrada = carritoCompraDTO.getProductos().get(i);
            String[] partes = entrada.split("-");
            String parteNombre = partes[0].toUpperCase();
            String parteCantidad = partes[1];

            ProductoM2 productoM2 = productoM2Servicio.findByNombre(parteNombre);
            ProductoUni productoUni = productoUniServicio.findByNombre(parteNombre);

            if (productoUni == null && productoM2 == null) {
                throw new RuntimeException("No existe un produto: " + parteNombre);
            }
            if (productoM2 != null) {
                //obtener precio, descuento y multiplicarlo por la cantidad seleccionada
                double cantidadPrecio1 = productoM2.getPrecio() * productoM2.getDescuento() * Double.parseDouble(parteCantidad);
                totalCompra += cantidadPrecio1;

                // validar si hay stock suficiente
                if (productoM2.getStock() - Double.parseDouble(parteCantidad) < 0) {
                    throw new RuntimeException("No hay suficiente stock de: " + parteNombre);
                }

                //validar si el producto esta activo
                if (!productoM2.getEstaActivo()) {
                    throw new RuntimeException("Este producto ha sido eliminado: " + parteNombre);
                }


                OrdenProductoM2 ordenProductoM2 = (new OrdenProductoM2(Double.parseDouble(parteCantidad), cantidadPrecio1));
                productoM2.setStock(productoM2.getStock() - Double.parseDouble(parteCantidad));
                orden.addordenProductoM2(ordenProductoM2);
                productoM2.addOrdenProductoM2(ordenProductoM2);
                ordenProductoM2Servicio.saveOrdenProductoM2(ordenProductoM2);
            } else if (productoUni != null) {
                double cantidadPrecio = productoUni.getPrecio() * productoUni.getDescuento() * Integer.parseInt(parteCantidad);
                totalCompra += cantidadPrecio;

                if (productoUni.getStock() - Integer.parseInt(parteCantidad) < 0) {
                    throw new RuntimeException("No hay suficiente stock de: " + parteNombre);
                }

                if (!productoUni.getEstaActivo()) {
                    throw new RuntimeException("Este producto ha sido eliminado: " + parteNombre);
                }

                OrdenProductoUni ordenProductoUni = (new OrdenProductoUni(Integer.parseInt(parteCantidad), cantidadPrecio));
                productoUni.setStock(productoUni.getStock() - Integer.parseInt(parteCantidad));
                orden.addOrdenProductoUni(ordenProductoUni);
                productoUni.addOrdenProductoUni(ordenProductoUni);
                ordenProductoUniServicio.saveOrdenProductoUni(ordenProductoUni);

            }
        }

        ResponseEntity<Object> pagarConTarjeta = PagarConTarjetaUtilidad.pagarConTarjeta(carritoCompraDTO, totalCompra);

        if (pagarConTarjeta.getStatusCode() == HttpStatus.CREATED) {
            orden.setTotal(totalCompra);
            ordenServicio.saveNewOrden(orden);
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=MB-TICKET.pdf";
            response.setHeader(headerKey, headerValue);
            sendMail(response, orden, mailUsuario);
        } else {
            throw new RuntimeException("Se produjo un error");
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    public ResponseEntity<Object> sendMail(HttpServletResponse response, Orden orden, String mailUsuario) {
        try {
            // Crear el documento PDF
            Document document = new Document(PageSize.A4);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, outputStream);
            document.open();

            // Agregar contenido al documento
            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA, Font.DEFAULTSIZE, Font.BOLD, new Color(0, 0, 0));
            fontTitle.setSize(16);
            Font fontTableTitle = FontFactory.getFont(FontFactory.HELVETICA, Font.DEFAULTSIZE, new Color(0, 0, 0));
            fontTableTitle.setSize(12);
            Font fontBody = FontFactory.getFont(FontFactory.HELVETICA, Font.DEFAULTSIZE, new Color(0, 0, 0));
            fontBody.setSize(12);

            //LOGO
            Image img = Image.getInstance("src/main/resources/static/web/assets/images/Tecnica-Fueguina.png");
            img.scaleAbsoluteWidth(100);
            img.scaleAbsoluteHeight(100);
            document.add(img);

            // titulo
            Paragraph title = new Paragraph("TECNICA FUEGUINA", fontTitle);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(10);
            document.add(title);

            Paragraph paragraph = new Paragraph("Tu ticket de compra N°: " + orden.getNumeroDeOrden());
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.setSpacingAfter(10);
            document.add(paragraph);

//            tabla

            PdfPTable pdfPTable = new PdfPTable(4);

            PdfPCell headerCell2 = new PdfPCell(new Paragraph("CANTIDAD", fontTableTitle));
            PdfPCell headerCell4 = new PdfPCell(new Paragraph("PRODUCTO", fontTableTitle));
            PdfPCell headerCell9 = new PdfPCell(new Paragraph("PRECIO", fontTableTitle));
            PdfPCell headerCell3 = new PdfPCell(new Paragraph("SUBTOTAL", fontTableTitle));

            headerCell2.setBackgroundColor(new Color(255, 255, 255));
            headerCell2.setBorder(com.lowagie.text.Rectangle.TOP | com.lowagie.text.Rectangle.BOTTOM | com.lowagie.text.Rectangle.LEFT | com.lowagie.text.Rectangle.RIGHT);
            headerCell2.setBorderColor(Color.BLACK);
            headerCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell2.setFixedHeight(25f);

            headerCell4.setBackgroundColor(new Color(255, 255, 255));
            headerCell4.setBorder(com.lowagie.text.Rectangle.TOP | com.lowagie.text.Rectangle.BOTTOM | com.lowagie.text.Rectangle.LEFT | com.lowagie.text.Rectangle.RIGHT);
            headerCell4.setBorderColor(Color.BLACK);
            headerCell4.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell4.setFixedHeight(25f);

            headerCell9.setBackgroundColor(new Color(255, 255, 255));
            headerCell9.setBorder(com.lowagie.text.Rectangle.TOP | com.lowagie.text.Rectangle.BOTTOM | com.lowagie.text.Rectangle.LEFT | com.lowagie.text.Rectangle.RIGHT);
            headerCell9.setBorderColor(Color.BLACK);
            headerCell9.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell9.setFixedHeight(25f);

            headerCell3.setBackgroundColor(new Color(255, 255, 255));
            headerCell3.setBorder(com.lowagie.text.Rectangle.TOP | com.lowagie.text.Rectangle.BOTTOM | com.lowagie.text.Rectangle.LEFT | com.lowagie.text.Rectangle.RIGHT);
            headerCell3.setBorderColor(Color.BLACK);
            headerCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell3.setFixedHeight(25f);

            pdfPTable.addCell(headerCell2);
            pdfPTable.addCell(headerCell4);
            pdfPTable.addCell(headerCell9);
            pdfPTable.addCell(headerCell3);

            Set<OrdenProductoM2> productoPesos = orden.getordenProductoM2s();
            Set<OrdenProductoUni> productoUni = orden.getOrdenProductoUnis();

            for (OrdenProductoUni elemento : productoUni) {
                PdfPCell pdfPCell5 = new PdfPCell(new Paragraph(String.valueOf(elemento.getCantidadUni() + " u"), fontBody));
                PdfPCell pdfPCell7 = new PdfPCell(new Paragraph(elemento.getProductoUni().getNombre(), fontBody));
                PdfPCell pdfPCell9 = new PdfPCell(new Paragraph("$" + String.valueOf(elemento.getProductoUni().getPrecio()) + " /u", fontBody));
                PdfPCell pdfPCell6 = new PdfPCell(new Paragraph("$" + String.valueOf(elemento.getTotal()), fontBody));

                pdfPCell5.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell5.setVerticalAlignment(Element.ALIGN_CENTER);
                pdfPCell7.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell7.setVerticalAlignment(Element.ALIGN_CENTER);
                pdfPCell9.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell9.setVerticalAlignment(Element.ALIGN_CENTER);
                pdfPCell6.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell6.setVerticalAlignment(Element.ALIGN_CENTER);

                pdfPCell5.setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.LEFT | Rectangle.RIGHT);
                pdfPCell5.setBorderColor(Color.BLACK);
                pdfPCell5.setFixedHeight(40f);

                pdfPCell7.setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.LEFT | Rectangle.RIGHT);
                pdfPCell7.setBorderColor(Color.BLACK);
                pdfPCell7.setFixedHeight(40f);

                pdfPCell9.setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.LEFT | Rectangle.RIGHT);
                pdfPCell9.setBorderColor(Color.BLACK);
                pdfPCell9.setFixedHeight(40f);

                pdfPCell6.setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.LEFT | Rectangle.RIGHT);
                pdfPCell6.setBorderColor(Color.BLACK);
                pdfPCell6.setFixedHeight(40f);

                pdfPTable.addCell(pdfPCell5);
                pdfPTable.addCell(pdfPCell7);
                pdfPTable.addCell(pdfPCell9);
                pdfPTable.addCell(pdfPCell6);
            }
            for (OrdenProductoM2 elemento : productoPesos) {
                PdfPCell pdfPCell5 = new PdfPCell(new Paragraph(String.valueOf(elemento.getCantidadM2()) + " m2", fontBody));
                PdfPCell pdfPCell7 = new PdfPCell(new Paragraph(elemento.getProductoM2().getNombre(), fontBody));
                PdfPCell pdfPCell9 = new PdfPCell(new Paragraph("$" + String.valueOf(elemento.getProductoM2().getPrecio()) + " /m2", fontBody));
                PdfPCell pdfPCell6 = new PdfPCell(new Paragraph("$" + String.valueOf(elemento.getTotal()), fontBody));

                pdfPCell5.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell5.setVerticalAlignment(Element.ALIGN_CENTER);
                pdfPCell7.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell7.setVerticalAlignment(Element.ALIGN_CENTER);
                pdfPCell9.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell9.setVerticalAlignment(Element.ALIGN_CENTER);
                pdfPCell6.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell6.setVerticalAlignment(Element.ALIGN_CENTER);

                pdfPCell5.setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.LEFT | Rectangle.RIGHT);
                pdfPCell5.setBorderColor(Color.BLACK);
                pdfPCell5.setFixedHeight(40f);

                pdfPCell7.setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.LEFT | Rectangle.RIGHT);
                pdfPCell7.setBorderColor(Color.BLACK);
                pdfPCell7.setFixedHeight(40f);

                pdfPCell9.setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.LEFT | Rectangle.RIGHT);
                pdfPCell9.setBorderColor(Color.BLACK);
                pdfPCell9.setFixedHeight(40f);

                pdfPCell6.setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.LEFT | Rectangle.RIGHT);
                pdfPCell6.setBorderColor(Color.BLACK);
                pdfPCell6.setFixedHeight(40f);

                pdfPTable.addCell(pdfPCell5);
                pdfPTable.addCell(pdfPCell7);
                pdfPTable.addCell(pdfPCell9);
                pdfPTable.addCell(pdfPCell6);
            }
            document.add(pdfPTable);

            Paragraph total = new Paragraph("TOTAL: $" + String.valueOf(orden.getTotal()), fontBody);
            total.setAlignment(Element.ALIGN_CENTER);
            total.setSpacingAfter(10);
            document.add(total);

            document.close();
            // Enviar el correo electrónico con el PDF adjunto
            sendConfirmationEmailWithAttachment(outputStream.toByteArray(), mailUsuario);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return new ResponseEntity<>("Ticket enviado!", HttpStatus.CREATED);
    }

    private void sendConfirmationEmailWithAttachment(byte[] pdfBytes, String mailUsuario) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(mailUsuario);
        helper.setSubject("Ticket compra - TECNICA FUEGUINA");
        helper.setText("Adjunto encontrarás el TICKET de compra.");

        // Adjuntar el PDF al correo electrónico
        helper.addAttachment("ticket.pdf", new ByteArrayResource(pdfBytes));

        mailSender.send(message);
    }

    //    VALORAR UN PRODUCTO - CLIENTE
    @PostMapping("/api/valorar-producto")
    public ResponseEntity<Object> valorarProducto(Authentication authentication,
                                                  @RequestParam String nombre,
                                                  @RequestParam double valor){
        Cliente cliente = clienteServicio.findByEmail(authentication.getName());
        if (cliente == null){
            return new ResponseEntity<>("Debes registrarte para valorar un producto", HttpStatus.FORBIDDEN);
        }
        if (nombre.isBlank()){
            return new ResponseEntity<>("Falta el nombre del producto", HttpStatus.FORBIDDEN);
        }
        if (valor < 0 || valor > 10){
            return new ResponseEntity<>("El puntaje debe estar entre 0 y 10", HttpStatus.FORBIDDEN);
        }

        String nombreProd = nombre.toUpperCase();

        ProductoUni productoUni = productoUniServicio.findByNombre(nombreProd);
        ProductoM2 productoM2 = productoM2Servicio.findByNombre(nombreProd);

        if (productoUni != null && productoUni.getEstaActivo()){
            productoUni.addPuntuacion(valor);
            productoUniServicio.saveProductoUni(productoUni);
        } else if(productoM2 != null && productoM2.getEstaActivo()){
            productoM2.addPuntuacion(valor);
            productoM2Servicio.saveProductoM2(productoM2);
        } else {
            return new ResponseEntity<>("Ese producto no existe, o ha sido eliminado.", HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>("Gracias por valorar el producto!", (HttpStatus.CREATED));
    }
}