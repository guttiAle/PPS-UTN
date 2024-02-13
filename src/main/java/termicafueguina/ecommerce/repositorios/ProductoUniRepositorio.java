package termicafueguina.ecommerce.repositorios;

import termicafueguina.ecommerce.modelos.ProductoUni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductoUniRepositorio extends JpaRepository<ProductoUni, Long> {
    ProductoUni findByNombre (String nombre);
}
