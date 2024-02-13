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
    private ProductoM2Servicio productoPesoServicio;
    @Autowired
    private ProductoUniServicio productoUniServicio;
    @Autowired
    private OrdenServicio ordenServicio;
    @Autowired
    private OrdenProductoUniServicio ordenProductoUniServicio;
    @Autowired
    private OrdenProductoM2Servicio ordenProductoPesoServicio;
    @Autowired
    private EmailService emailService;
    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/api/productoM2")
    public List<ProductoM2DTO> getProductoPeso() {
        return productoPesoServicio.getProductoM2().stream().filter(productoPeso -> productoPeso.getEstaActivo()).collect(Collectors.toList());
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
        
    return null;
    }

}


