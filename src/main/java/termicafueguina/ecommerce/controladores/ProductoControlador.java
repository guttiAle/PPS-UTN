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

        if (cliente == null){
            return new ResponseEntity<>("Tienes que estar logueado para realizar esta acción", HttpStatus.FORBIDDEN);
        }
        if (carritoCompraDTO.getProductos().size() == 0){
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

            if (productoUni == null && productoM2 == null){
                throw new RuntimeException("No existe un produto: " + parteNombre);
            }
            if (productoM2 != null) {
                //obtener precio, descuento y multiplicarlo por la cantidad seleccionada
                double cantidadPrecio1 = productoM2.getPrecio() * productoM2.getDescuento() * Double.parseDouble(parteCantidad);
                totalCompra += cantidadPrecio1;

                // validar si hay stock suficiente
                if (productoM2.getStock()-Double.parseDouble(parteCantidad) < 0){
                    throw new RuntimeException("No hay suficiente stock de: " + parteNombre);
                }

                //validar si el producto esta activo
                if (!productoM2.getEstaActivo()){
                    throw new RuntimeException("Este producto ha sido eliminado: " + parteNombre);
                }
        }
            



    return null;
    }

}


