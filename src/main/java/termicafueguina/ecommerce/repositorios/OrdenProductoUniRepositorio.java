package termicafueguina.ecommerce.repositorios;

import termicafueguina.ecommerce.modelos.OrdenProductoUni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface OrdenProductoUniRepositorio extends JpaRepository<OrdenProductoUni, Long> {
}
