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
            ProductoM2 productoM201 =(new ProductoM2("PANEL TERMICO DE 30mm",TipoProducto.PANEL,"El panel térmico de 30 mm es una solución compacta y eficiente para mejorar el aislamiento térmico en construcciones. Su grosor optimizado garantiza un rendimiento superior en la regulación de temperatura, proporcionando confort térmico y reduciendo el consumo de energía. Ligero y fácil de instalar, es una opción ideal para proyectos residenciales, comerciales e industriales que buscan mejorar su eficiencia energética de manera sencilla y efectiva.",50.5,2310.0,PaisProducto.NACIONAL , 9,"https://www.orlandisa.com/images/uploads/ecommerce/70BS999.jpg"));

            ProductoM2 productoM202 = new ProductoM2("PANEL TÉRMICO DE 50 mm", TipoProducto.PANEL, "El panel térmico de 50 mm es una solución versátil y eficaz para mejorar el aislamiento térmico en construcciones. Con su grosor óptimo, ofrece un excelente rendimiento en la regulación de la temperatura, proporcionando confort térmico y reduciendo el consumo de energía. Ideal para proyectos residenciales, comerciales e industriales que requieren un aislamiento eficiente y de calidad.", 40.0, 2600.0, PaisProducto.NACIONAL, 8, "URL_IMAGEN_PRODUCTO");

            ProductoM2 productoM203 = new ProductoM2("PANEL TÉRMICO DE 60 mm", TipoProducto.PANEL, "El panel térmico de 60 mm es una solución confiable y resistente para mejorar el aislamiento térmico en construcciones. Con su grosor adecuado, garantiza un óptimo rendimiento en la regulación de la temperatura, proporcionando confort térmico y reduciendo el consumo de energía. Perfecto para proyectos residenciales, comerciales e industriales que buscan eficiencia energética.", 30.0, 2950.0, PaisProducto.NACIONAL, 8, "URL_IMAGEN_PRODUCTO");

            ProductoM2 productoM204 = new ProductoM2("PANEL TÉRMICO DE 80 mm", TipoProducto.PANEL, "El panel térmico de 80 mm es una solución robusta y duradera para mejorar el aislamiento térmico en construcciones. Su grosor óptimo proporciona un excelente rendimiento en la regulación de la temperatura, brindando confort térmico y reduciendo el consumo de energía. Ideal para proyectos residenciales, comerciales e industriales que buscan una eficiencia energética superior.", 25.0, 3200.0, PaisProducto.NACIONAL, 7, "URL_IMAGEN_PRODUCTO");

            ProductoM2 productoM205 = new ProductoM2("PANEL TÉRMICO DE 100 mm", TipoProducto.PANEL, "El panel térmico de 100 mm es una solución resistente y eficiente para mejorar el aislamiento térmico en construcciones. Con su grosor adecuado, proporciona un excelente rendimiento en la regulación de la temperatura, garantizando confort térmico y reduciendo el consumo de energía. Perfecto para proyectos residenciales, comerciales e industriales que requieren un aislamiento térmico superior.", 20.0, 3650.0, PaisProducto.NACIONAL, 7, "URL_IMAGEN_PRODUCTO");

            ProductoM2 productoM206 = new ProductoM2("PANEL TÉRMICO DE 150 mm", TipoProducto.PANEL, "El panel térmico de 150 mm es una solución potente y eficaz para mejorar el aislamiento térmico en construcciones. Con su grosor óptimo, proporciona un excelente rendimiento en la regulación de la temperatura, garantizando confort térmico y reduciendo el consumo de energía. Perfecto para proyectos residenciales, comerciales e industriales que buscan una eficiencia energética excepcional.", 15.0, 4200.0, PaisProducto.NACIONAL, 8, "URL_IMAGEN_PRODUCTO");

            ProductoM2 productoM207 = new ProductoM2("PANEL TÉRMICO DE 200 mm", TipoProducto.PANEL, "El panel térmico de 200 mm es una solución robusta y confiable para mejorar el aislamiento térmico en construcciones. Con su grosor adecuado, proporciona un rendimiento superior en la regulación de la temperatura, garantizando confort térmico y reduciendo el consumo de energía. Ideal para proyectos residenciales, comerciales e industriales que buscan una eficiencia energética excepcional.", 10.0, 4800.0, PaisProducto.NACIONAL, 9, "URL_IMAGEN_PRODUCTO");

            ProductoM2 productoM208 = new ProductoM2("Plegados de chapa prepintada", TipoProducto.CHAPA, "Los plegados de chapa prepintada son una solución versátil y duradera para revestimientos exteriores en construcciones. Fabricados con chapa de acero recubierta de pintura de alta calidad, ofrecen resistencia a la corrosión y a los elementos climáticos. Disponibles en una variedad de perfiles y colores para adaptarse a cualquier diseño arquitectónico.", 70.0, 1200.0, PaisProducto.NACIONAL, 8, "URL_IMAGEN_PRODUCTO");


            //CREACION DE PRODUCTO POR UNIDAD
            ProductoUni productoUni08 = new ProductoUni("LATA DE ESPUMA", TipoProducto.POLIURETANO, "La lata de espuma de poliuretano es una solución práctica y versátil para sellar, aislar y rellenar espacios en construcciones. Su fórmula expandible y fácil aplicación la convierten en una opción ideal para proyectos de bricolaje y profesionales. Ayuda a mejorar la eficiencia energética al reducir pérdidas de calor y frío, proporcionando un sellado duradero en una variedad de aplicaciones.", 100, 350.0, PaisProducto.NORTEAMERICANO, 7, "URL DE LA IMAGEN DEL PRODUCTO");

            ProductoUni productoUni02 = new ProductoUni("Tornillos", TipoProducto.HERRAMIENTAS, "Tornillos de alta resistencia y calidad, ideales para fijar materiales en proyectos de construcción o bricolaje. Disponibles en varios tamaños y tipos de cabeza para adaptarse a diferentes necesidades.", 500, 5.99, PaisProducto.NACIONAL, 9, "URL_DE_LA_IMAGEN_DEL_PRODUCTO");

            ProductoUni productoUni03 = new ProductoUni("Bisagras", TipoProducto.HERRAJES, "Bisagras de calidad premium para puertas y ventanas. Construidas con materiales duraderos y diseñadas para proporcionar un movimiento suave y silencioso. Disponibles en diferentes tamaños y acabados.", 300, 8.75, PaisProducto.NACIONAL, 8, "URL_DE_LA_IMAGEN_DEL_PRODUCTO");

            ProductoUni productoUni04 = new ProductoUni("Disco de corte", TipoProducto.HERRAMIENTAS, "Discos de corte de alta calidad para cortar metal, madera y otros materiales. Diseñados para ofrecer un corte preciso y limpio en diversas aplicaciones industriales y de bricolaje.", 200, 12.50, PaisProducto.NACIONAL, 8, "URL_DE_LA_IMAGEN_DEL_PRODUCTO");

            ProductoUni productoUni05 = new ProductoUni("Remaches", TipoProducto.HERRAMIENTAS, "Remaches resistentes y fiables para unir materiales de forma segura y duradera. Ideales para su uso en la industria automotriz, aeronáutica y en proyectos de construcción.", 400, 6.99, PaisProducto.NACIONAL, 7, "URL_DE_LA_IMAGEN_DEL_PRODUCTO");

            ProductoUni productoUni06 = new ProductoUni("Manijas de puertas", TipoProducto.HERRAJES, "Manijas de puertas elegantes y funcionales para mejorar el aspecto y la funcionalidad de cualquier puerta. Disponibles en una variedad de estilos y acabados para complementar cualquier diseño interior.", 250, 15.25, PaisProducto.NACIONAL, 9, "URL_DE_LA_IMAGEN_DEL_PRODUCTO");

            ProductoUni productoUni07 = new ProductoUni("Trabas para puertas", TipoProducto.HERRAJES, "Trabas para puertas seguras y confiables para garantizar la privacidad y seguridad en el hogar o lugar de trabajo. Fáciles de instalar y diseñadas para resistir el paso del tiempo.", 150, 18.99, PaisProducto.NACIONAL, 8, "URL_DE_LA_IMAGEN_DEL_PRODUCTO");


            //GUARDADO DE DATOS
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
