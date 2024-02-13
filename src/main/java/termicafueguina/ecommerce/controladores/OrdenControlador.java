package termicafueguina.ecommerce.controladores;

import termicafueguina.ecommerce.dtos.OrdenDTO;
import termicafueguina.ecommerce.modelos.Cliente;
import termicafueguina.ecommerce.servicios.ClienteServicio;
import termicafueguina.ecommerce.servicios.OrdenServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OrdenControlador {
    @Autowired
    private OrdenServicio ordenServicio;
    @Autowired
    private ClienteServicio clienteServicio;

    @GetMapping("/api/ordenes")
        public List<OrdenDTO> getOrdenes(){return ordenServicio.getOrdenes();}


}
