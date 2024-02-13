package termicafueguina.ecommerce.servicios;

import termicafueguina.ecommerce.dtos.ProductoUniDTO;
import termicafueguina.ecommerce.modelos.ProductoUni;

import java.util.List;

public interface ProductoUniServicio {
    void saveProductoUni(ProductoUni productoUni);
    ProductoUni findByNombre (String nombre);
    List<ProductoUniDTO> getProductoUni();
    void deleteProductoUni(ProductoUni productoUni);
}
