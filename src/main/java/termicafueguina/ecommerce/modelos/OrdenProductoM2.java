package termicafueguina.ecommerce.modelos;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class OrdenProductoM2 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private double cantidadKg;
    private double total;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="idOrden")
    private Orden orden;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="idProductoM2")
    private ProductoM2 productoM2;

    //CONSTRUCTORES
    public OrdenProductoM2(){}

    public OrdenProductoM2(double cantidadKg, double total) {
        this.cantidadKg = cantidadKg;
        this.total = total;
    }

    //GETTERS
    public long getId() {return id;}
    public double getCantidadKg() {return cantidadKg;}
    public Orden getOrden() {return orden;}
    public ProductoM2 getProductoM2() {return productoM2;}
    public double getTotal() {return total;}

    //SETTERS
    public void setCantidadKg(double cantidadKg) {this.cantidadKg = cantidadKg;}
    public void setOrden(Orden orden) {this.orden = orden;}
    public void setProductoM2(ProductoM2 productoM2) {this.productoM2 = productoM2;}
    public void setTotal(double total) {this.total = total;}

}
