package termicafueguina.ecommerce.modelos;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;;

@Entity
public class ProductoM2 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String nombre;
    private TipoProducto tipo;
    private String descripcion;
    private double stock;
    private double precio;
    private PaisProducto origen;
    private String url;
    private double descuento;
    private boolean estaActivo;
    private boolean esPorM2;
    @ElementCollection
    private List<Double> puntuaciones = new ArrayList<>();
    @OneToMany(mappedBy="productoM2", fetch = FetchType.EAGER)
    private Set<OrdenProductoM2> ordenProductoM2s = new HashSet<>();

    //CONSTRUCTORES
    public ProductoM2(){}

    public ProductoM2(String nombre, TipoProducto tipo, String descripcion, double stock, double precio, PaisProducto origen, double puntuacion, String url) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precio = precio;
        this.origen = origen;
        this.puntuaciones.add(puntuacion);
        this.url = url;
        this.descuento = 1;
        this.estaActivo = true;
        this.esPorM2 = true;
    }

    //GETTERS
    public long getId() {return id;}
    public String getNombre() {return nombre;}
    public TipoProducto getTipo() {return tipo;}
    public String getDescripcion() {return descripcion;}
    public double getStock() {return stock;}
    public double getPrecio() {return precio;}
    public PaisProducto getOrigen() {return origen;}
    public Set<OrdenProductoM2> getOrdenProductoM2s() {return ordenProductoM2s;}
    public List<Double> getPuntuaciones() {return puntuaciones;}
    public String getUrl() {return url;}
    public double getDescuento() {return descuento;}
    public boolean getEstaActivo() {return estaActivo;}
    public boolean esPorM2() {return esPorM2;}

    //SETTERS
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setTipo(TipoProducto tipo) {this.tipo = tipo;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
    public void setStock(double stock) {this.stock = stock;}
    public void setPrecio(double precio) {this.precio = precio;}
    public void setOrigen(PaisProducto origen) {this.origen = origen;}
    public void setOrdenProductoM2s(Set<OrdenProductoM2> ordenProductoM2s) {this.ordenProductoM2s = ordenProductoM2s;}
    public void setPuntuaciones(List<Double> puntuaciones) {this.puntuaciones = puntuaciones;}
    public void setUrl(String url) {this.url = url;}
    public void setDescuento(double descuento) {this.descuento = descuento;}
    public void setEstaActivo(boolean estaActivo) {this.estaActivo = estaActivo;}

    //ADDERS
    public void addOrdenProductoM2(OrdenProductoM2 ordenProductoM2) {
        ordenProductoM2.setProductoM2(this);
        ordenProductoM2s.add(ordenProductoM2);
    }
    public void addPuntuacion (double valor){
        puntuaciones.add(valor);
    }
}
