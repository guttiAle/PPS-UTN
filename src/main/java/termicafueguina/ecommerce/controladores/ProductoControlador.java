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


}


