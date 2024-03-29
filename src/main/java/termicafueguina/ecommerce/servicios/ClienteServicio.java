package termicafueguina.ecommerce.servicios;

import termicafueguina.ecommerce.dtos.ClienteDTO;
import termicafueguina.ecommerce.modelos.Cliente;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ClienteServicio {
    List<ClienteDTO> getCliente();
    ClienteDTO getClienteDTO(Long id);
    ClienteDTO getClienteActual(Authentication authentication);
    Cliente findByEmail(String email);
    void saveCliente(Cliente cliente);
}
