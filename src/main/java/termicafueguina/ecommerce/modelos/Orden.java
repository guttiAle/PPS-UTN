package termicafueguina.ecommerce.modelos;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String numeroDeOrden;
    private LocalDateTime fecha;
    private boolean envio;
    private boolean pagado;
    private double total;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="idCliente")
    private Cliente cliente;




    //CONSTRUCTORES
    public Orden(){}

    public Orden(LocalDateTime fecha, boolean envio, boolean pagado, double total, String numeroDeOrden) {
        this.numeroDeOrden = numeroDeOrden;
        this.fecha = fecha;
        this.envio = envio;
        this.pagado = pagado;
        this.total = total;
    }

}
