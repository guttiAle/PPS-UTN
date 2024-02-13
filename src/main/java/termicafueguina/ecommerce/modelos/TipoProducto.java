package termicafueguina.ecommerce.modelos;

import java.util.Arrays;
import java.util.List;

public enum TipoProducto {
    METAL,
    ACERO,
    MADERA,
    DURLOCK,
    HORMIGON,
    LADRILLO,
    CHAPA,
    VIDRIO,
    HERRAJES,
    PANEL,
    POLIURETANO,
    HERRAMIENTAS;

    public static List<TipoProducto> obtenerTiposDeProducto() {
        return Arrays.asList(TipoProducto.values());
    }
}
