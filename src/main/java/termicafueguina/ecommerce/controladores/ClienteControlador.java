package termicafueguina.ecommerce.controladores;

import termicafueguina.ecommerce.dtos.ClienteDTO;
import termicafueguina.ecommerce.dtos.ClienteRegistroDTO;
import termicafueguina.ecommerce.modelos.Cliente;
import termicafueguina.ecommerce.servicios.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//import java.util.stream.Collectors;

@RestController
public class ClienteControlador {

    @Autowired
    private ClienteServicio clienteServicio;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/api/clientes")
    public List<ClienteDTO> getCliente() {
        return clienteServicio.getCliente();
    }

    @GetMapping("/api/clientes/{id}")
    public ClienteDTO getCliente(@PathVariable Long id){
        return clienteServicio.getClienteDTO(id);
    }

    @GetMapping("/api/clientes/actual")
    public ClienteDTO getClienteActual(Authentication authentication) {
        return clienteServicio.getClienteActual(authentication);
    }


}
