package termicafueguina.ecommerce.repositorios;

import termicafueguina.ecommerce.modelos.OrdenProductoM2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface OrdenProductoM2Repositorio extends JpaRepository<OrdenProductoM2, Long> {
}
