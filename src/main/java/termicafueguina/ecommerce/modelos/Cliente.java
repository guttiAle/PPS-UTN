package termicafueguina.ecommerce.modelos;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String nombre;
    private String apellido;
    private String email;
    private String calle;
    private String ciudad;
    private int codPostal;
    private String telefono;
    private String contrasena;


    //CONSTRUCTORES
    public Cliente(){}

    public Cliente(String nombre, String apellido, String email, String calle, String ciudad, int codPostal, String telefono, String contrasena) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.calle = calle;
        this.ciudad = ciudad;
        this.codPostal = codPostal;
        this.telefono = telefono;
        this.contrasena = contrasena;
    }


}