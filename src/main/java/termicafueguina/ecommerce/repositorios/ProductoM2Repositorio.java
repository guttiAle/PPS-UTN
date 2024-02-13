package termicafueguina.ecommerce.repositorios;

import termicafueguina.ecommerce.modelos.ProductoM2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductoM2Repositorio extends JpaRepository<ProductoM2, Long> {
    ProductoM2 findByNombre (String nombre);
}
