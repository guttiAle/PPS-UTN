package termicafueguina.ecommerce.servicios.implementaciones;

import termicafueguina.ecommerce.dtos.ClienteDTO;
import termicafueguina.ecommerce.modelos.Cliente;
import termicafueguina.ecommerce.repositorios.ClienteRepositorio;
import termicafueguina.ecommerce.servicios.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServicioImplementacion implements ClienteServicio {
    @Autowired
    private ClienteRepositorio clienteRepositorio;
    @Override
    public List<ClienteDTO> getCliente() {
        return clienteRepositorio.findAll().stream().map(client -> new ClienteDTO(client)).collect(Collectors.toList());
    }


}
