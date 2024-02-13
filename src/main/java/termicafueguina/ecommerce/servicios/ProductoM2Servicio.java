package termicafueguina.ecommerce.servicios;

import termicafueguina.ecommerce.dtos.ProductoM2DTO;
import termicafueguina.ecommerce.modelos.ProductoM2;

import java.util.List;

public interface ProductoM2Servicio {
    void saveProductoM2 (ProductoM2 productoM2);
    ProductoM2 findByNombre (String nombre);
    List<ProductoM2DTO> getProductoM2();
    void deleteProductoM2(ProductoM2 productoM2);
}
