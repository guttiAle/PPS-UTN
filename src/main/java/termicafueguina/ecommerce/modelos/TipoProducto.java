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
    VIDRIO,
    PIEDRA,
    PARQUET;

    public static List<TipoProducto> obtenerTiposDeProducto() {
        return Arrays.asList(TipoProducto.values());
    }
}
