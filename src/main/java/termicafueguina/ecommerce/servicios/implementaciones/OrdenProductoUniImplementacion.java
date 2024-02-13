package termicafueguina.ecommerce.servicios.implementaciones;

import termicafueguina.ecommerce.modelos.OrdenProductoUni;
import termicafueguina.ecommerce.repositorios.OrdenProductoUniRepositorio;
import termicafueguina.ecommerce.servicios.OrdenProductoUniServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdenProductoUniImplementacion implements OrdenProductoUniServicio {

    @Autowired
    private OrdenProductoUniRepositorio ordenProductoUniRepositorio;

    @Override
    public void saveOrdenProductoUni(OrdenProductoUni ordenProductoUni) {
        ordenProductoUniRepositorio.save(ordenProductoUni);
    }
}
