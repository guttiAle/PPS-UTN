package termicafueguina.ecommerce.modelos;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class ProductoUni {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String nombre;
    private TipoProducto tipo;
    private String descripcion;
    private int stock;
    private double precio;
    private PaisProducto origen;
    private String url;
    private double descuento;
    private boolean estaActivo;
    private boolean esPorPeso;
    @ElementCollection
    private List<Double> puntuaciones = new ArrayList<>();
    @OneToMany(mappedBy="productoUni", fetch = FetchType.EAGER)
    private Set<OrdenProductoUni> ordenProductoUnis = new HashSet<>();

    //CONSTRUCTORES
    public ProductoUni(){}

    public ProductoUni(String nombre, TipoProducto tipo, String descripcion, int stock, double precio, PaisProducto origen, double puntuacion, String url) {
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
        this.esPorPeso = false;
    }



}
