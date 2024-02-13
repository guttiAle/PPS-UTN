package termicafueguina.ecommerce.dtos;

import termicafueguina.ecommerce.modelos.PaisProducto;
import termicafueguina.ecommerce.modelos.ProductoM2;
import termicafueguina.ecommerce.modelos.TipoProducto;

public class ProductoM2DTO {
    private long id;
    private String nombre;
    private TipoProducto tipo;
    private String descripcion;
    private double stock;
    private double precio;
    private PaisProducto origen;
    private double puntuaciones;
    private String url;
    private double descuento;
    private boolean estaActivo;
    private boolean esPorM2;

    //CONSTRUCTOR
    public ProductoM2DTO(ProductoM2 productoM2){
        this.id = productoM2.getId();
        this.nombre = productoM2.getNombre();
        this.tipo = productoM2.getTipo();
        this.descripcion = productoM2.getDescripcion();
        this.stock = productoM2.getStock();
        this.precio = productoM2.getPrecio();
        this.origen = productoM2.getOrigen();
        this.puntuaciones = productoM2.getPuntuaciones().stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        this.url = productoM2.getUrl();
        this.descuento = productoM2.getDescuento();
        this.estaActivo = productoM2.getEstaActivo();
        this.esPorM2 = productoM2.esPorM2();
    }
    //GETTERS
    public long getId() {return id;}
    public String getNombre() {return nombre;}
    public TipoProducto getTipo() {return tipo;}
    public String getDescripcion() {return descripcion;}
    public double getStock() {return stock;}
    public double getPrecio() {return precio;}
    public PaisProducto getOrigen() {return origen;}
    public double getPuntuaciones() {return puntuaciones;}
    public String getUrl() {return url;}
    public double getDescuento() {return descuento;}
    public boolean getEstaActivo() {return estaActivo;}

    public boolean isEsPorM2() {return esPorM2;}
}
