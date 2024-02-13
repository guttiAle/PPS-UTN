package termicafueguina.ecommerce.repositorios;

import termicafueguina.ecommerce.modelos.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TicketRepositorio extends JpaRepository<Ticket, Long> {

}
