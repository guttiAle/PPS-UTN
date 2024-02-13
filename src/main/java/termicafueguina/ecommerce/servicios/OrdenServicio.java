package termicafueguina.ecommerce.servicios;

import termicafueguina.ecommerce.dtos.OrdenDTO;
import termicafueguina.ecommerce.modelos.Orden;

import java.util.List;

public interface OrdenServicio {
    OrdenDTO getOrden(Long id);
    List<OrdenDTO> getOrdenes();
    Orden findByNumeroDeOrden(String numeroDeOrden);
    void saveNewOrden(Orden orden);
    void deleteOrden(Orden orden);
}
