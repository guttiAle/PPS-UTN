package termicafueguina.ecommerce.modelos;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class OrdenProductoM2 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private double cantidadM2;
    private double total;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="idOrden")
    private Orden orden;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="idProductoM2")
    private ProductoM2 productoM2;

    //CONSTRUCTORES
    public OrdenProductoM2(){}

    public OrdenProductoM2(double cantidadM2, double total) {
        this.cantidadM2 = cantidadM2;
        this.total = total;
    }

    //GETTERS
    public long getId() {return id;}
    public double getCantidadM2() {return cantidadM2;}
    public Orden getOrden() {return orden;}
    public ProductoM2 getProductoM2() {return productoM2;}
    public double getTotal() {return total;}

    //SETTERS
    public void setCantidadM2(double cantidadM2) {this.cantidadM2 = cantidadM2;}
    public void setOrden(Orden orden) {this.orden = orden;}
    public void setProductoM2(ProductoM2 productoM2) {this.productoM2 = productoM2;}
    public void setTotal(double total) {this.total = total;}

}
