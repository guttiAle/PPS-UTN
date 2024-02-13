package termicafueguina.ecommerce;

import termicafueguina.ecommerce.modelos.*;
import termicafueguina.ecommerce.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@SpringBootApplication
public class ECommerceTermicaFueguinaApplication {
    public static void main(String[] args) {
        SpringApplication.run(ECommerceTermicaFueguinaApplication.class, args);
    }
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Bean
    public CommandLineRunner initData(ClienteRepositorio clienteRepositorio, OrdenRepositorio ordenRepositorio, ProductoM2Repositorio productoM2Repositorio, ProductoUniRepositorio productoUniRepositorio, TicketRepositorio ticketRepositorio, OrdenProductoM2Repositorio ordenProductoM2Repositorio, OrdenProductoUniRepositorio ordenProductoUniRepositorio) {
        return (args) -> {
            //CREACION DE CLIENTES
            Cliente cliente01 = (new Cliente("Juanjo", "Sepaso","juanjo@mail.com","Av. Siempreviva 123", "Springfield", 2400, "2604-112233", passwordEncoder.encode("juanjo123")));
            Cliente cliente02 = (new Cliente("Melba", "Morel","melba@utn.com","Av. Nuncaviva 321", "Shelbyville", 5600, "2604-112247", passwordEncoder.encode("melba123")));
            Cliente admin01 = (new Cliente("admin", "istrador","admin@admin.com","Av. Todo Permitido 322", "Usa", 5600, "2604-312123", passwordEncoder.encode("admin123")));


            //CREACION DE PRODUCTO POR M2
            ProductoM2 productoM201 =(new ProductoM2("PANEL TERMICO DE 30mm",TipoProducto.PANEL,"El panel térmico de 30 mm.",50.5,2310.0,PaisProducto.NACIONAL , 9,"https://www.orlandisa.com/images/uploads/ecommerce/70BS999.jpg"));

            ProductoM2 productoM202 = new ProductoM2("PANEL TÉRMICO DE 50 mm", TipoProducto.PANEL, "El panel térmico de 50 mm.", 40.0, 2600.0, PaisProducto.NACIONAL, 8, "URL_IMAGEN_PRODUCTO");

            ProductoM2 productoM203 = new ProductoM2("PANEL TÉRMICO DE 60 mm", TipoProducto.PANEL, "El panel térmico de 60 mm.", 30.0, 2950.0, PaisProducto.NACIONAL, 8, "URL_IMAGEN_PRODUCTO");

            ProductoM2 productoM204 = new ProductoM2("PANEL TÉRMICO DE 80 mm", TipoProducto.PANEL, "El panel térmico de 80 mm.", 25.0, 3200.0, PaisProducto.NACIONAL, 7, "URL_IMAGEN_PRODUCTO");

            ProductoM2 productoM205 = new ProductoM2("PANEL TÉRMICO DE 100 mm", TipoProducto.PANEL, "El panel térmico de 100 mm.", 20.0, 3650.0, PaisProducto.NACIONAL, 7, "URL_IMAGEN_PRODUCTO");

            ProductoM2 productoM206 = new ProductoM2("PANEL TÉRMICO DE 150 mm", TipoProducto.PANEL, "El panel térmico de 150 mm.", 15.0, 4200.0, PaisProducto.NACIONAL, 8, "URL_IMAGEN_PRODUCTO");

            ProductoM2 productoM207 = new ProductoM2("PANEL TÉRMICO DE 200 mm", TipoProducto.PANEL, "El panel térmico de 200 mm.", 10.0, 4800.0, PaisProducto.NACIONAL, 9, "URL_IMAGEN_PRODUCTO");

            ProductoM2 productoM208 = new ProductoM2("Plegados de chapa prepintada", TipoProducto.CHAPA, "Los plegados de chapa prepintada son una solución versátil y duradera para revestimientos exteriores en construcciones.", 70.0, 1200.0, PaisProducto.NACIONAL, 8, "URL_IMAGEN_PRODUCTO");


            //CREACION DE PRODUCTO POR UNIDAD
            ProductoUni productoUni08 = new ProductoUni("LATA DE ESPUMA", TipoProducto.POLIURETANO, "La lata de espuma de poliuretano es una solución práctica y versátil para sellar, aislar y rellenar espacios en construcciones.", 100, 350.0, PaisProducto.NORTEAMERICANO, 7, "URL DE LA IMAGEN DEL PRODUCTO");

            ProductoUni productoUni02 = new ProductoUni("Tornillos", TipoProducto.HERRAMIENTAS, "Tornillos de alta resistencia y calidad, ideales para fijar materiales en proyectos de construcción o bricolaje.", 500, 5.99, PaisProducto.NACIONAL, 9, "URL_DE_LA_IMAGEN_DEL_PRODUCTO");

            ProductoUni productoUni03 = new ProductoUni("Bisagras", TipoProducto.HERRAJES, "Bisagras de calidad premium para puertas y ventanas.", 300, 8.75, PaisProducto.NACIONAL, 8, "URL_DE_LA_IMAGEN_DEL_PRODUCTO");

            ProductoUni productoUni04 = new ProductoUni("Disco de corte", TipoProducto.HERRAMIENTAS, "Discos de corte de alta calidad para cortar metal, madera y otros materiales. .", 200, 12.50, PaisProducto.NACIONAL, 8, "URL_DE_LA_IMAGEN_DEL_PRODUCTO");

            ProductoUni productoUni05 = new ProductoUni("Remaches", TipoProducto.HERRAMIENTAS, "Remaches resistentes y fiables para unir materiales de forma segura y duradera.", 400, 6.99, PaisProducto.NACIONAL, 7, "URL_DE_LA_IMAGEN_DEL_PRODUCTO");

            ProductoUni productoUni06 = new ProductoUni("Manijas de puertas", TipoProducto.HERRAJES, "Manijas de puertas elegantes y funcionales para mejorar el aspecto y la funcionalidad de cualquier puerta.", 250, 15.25, PaisProducto.NACIONAL, 9, "URL_DE_LA_IMAGEN_DEL_PRODUCTO");

            ProductoUni productoUni07 = new ProductoUni("Trabas para puertas", TipoProducto.HERRAJES, "Trabas para puertas seguras y confiables para garantizar la privacidad y seguridad en el hogar o lugar de trabajo.", 150, 18.99, PaisProducto.NACIONAL, 8, "URL_DE_LA_IMAGEN_DEL_PRODUCTO");


            //GUARDADO DE DATOS
            //cliente
            clienteRepositorio.save(cliente01);
            clienteRepositorio.save(cliente02);
            clienteRepositorio.save(admin01);

            //productos
            productoM2Repositorio.save(productoM201);
            productoM2Repositorio.save(productoM202);
            productoM2Repositorio.save(productoM203);
            productoM2Repositorio.save(productoM204);
            productoM2Repositorio.save(productoM205);
            productoM2Repositorio.save(productoM206);
            productoM2Repositorio.save(productoM207);
            productoM2Repositorio.save(productoM208);


            productoUniRepositorio.save(productoUni02);
            productoUniRepositorio.save(productoUni03);
            productoUniRepositorio.save(productoUni04);
            productoUniRepositorio.save(productoUni05);
            productoUniRepositorio.save(productoUni06);
            productoUniRepositorio.save(productoUni07);
            productoUniRepositorio.save(productoUni08);

        };
    }
}
