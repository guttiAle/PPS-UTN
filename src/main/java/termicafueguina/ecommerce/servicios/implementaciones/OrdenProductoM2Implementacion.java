package termicafueguina.ecommerce.servicios.implementaciones;

import termicafueguina.ecommerce.modelos.OrdenProductoM2;
import termicafueguina.ecommerce.repositorios.OrdenProductoM2Repositorio;
import termicafueguina.ecommerce.servicios.OrdenProductoM2Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdenProductoM2Implementacion implements OrdenProductoM2Servicio {

    @Autowired
    private OrdenProductoM2Repositorio ordenProductoPesoRepositorio;

    @Override
    public void saveOrdenProductoM2(OrdenProductoM2 ordenProductoPeso) {
        ordenProductoPesoRepositorio.save(ordenProductoPeso);
    }
}
