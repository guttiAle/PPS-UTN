package termicafueguina.ecommerce.servicios.implementaciones;

import termicafueguina.ecommerce.dtos.OrdenDTO;
import termicafueguina.ecommerce.modelos.Orden;
import termicafueguina.ecommerce.repositorios.OrdenRepositorio;
import termicafueguina.ecommerce.servicios.OrdenServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdenServicioImplementacion implements OrdenServicio {
    @Autowired
    private OrdenRepositorio ordenRepositorio;

    @Override
    public OrdenDTO getOrden(Long id) {
        Optional<Orden> optionalOrden = ordenRepositorio.findById(id);
        return optionalOrden.map(orden -> new OrdenDTO(orden)).orElse(null);
    }
    @Override
    public List<OrdenDTO> getOrdenes(){
        return ordenRepositorio.findAll().stream().map(orden -> new OrdenDTO(orden)).collect(Collectors.toList());
    }

    @Override
    public Orden findByNumeroDeOrden (String numeroDeOrden) {
        return ordenRepositorio.findByNumeroDeOrden(numeroDeOrden);
    }

    @Override
    public void saveNewOrden(Orden orden) {
        ordenRepositorio.save(orden);
    }

    @Override
    public void deleteOrden(Orden orden){
        ordenRepositorio.delete(orden);
    }
}
