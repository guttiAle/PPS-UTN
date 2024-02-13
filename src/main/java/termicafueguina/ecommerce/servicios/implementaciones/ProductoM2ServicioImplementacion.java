package termicafueguina.ecommerce.servicios.implementaciones;

import termicafueguina.ecommerce.dtos.ProductoM2DTO;
import termicafueguina.ecommerce.modelos.ProductoM2;
import termicafueguina.ecommerce.repositorios.ProductoM2Repositorio;
import termicafueguina.ecommerce.servicios.ProductoM2Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoM2ServicioImplementacion implements ProductoM2Servicio {
    @Autowired
    private ProductoM2Repositorio productoM2Repositorio;

    @Override
    public void saveProductoM2(ProductoM2 productoM2) {
        productoM2Repositorio.save(productoM2);
    }
    @Override
    public ProductoM2 findByNombre(String nombre){return productoM2Repositorio.findByNombre(nombre);}
    @Override
    public List<ProductoM2DTO> getProductoM2(){
        return productoM2Repositorio.findAll().stream().map(prod -> new ProductoM2DTO(prod)).collect(Collectors.toList());
    }
    @Override
    public void deleteProductoM2(ProductoM2 productoM2){
       productoM2Repositorio.delete(productoM2);
    }
}
