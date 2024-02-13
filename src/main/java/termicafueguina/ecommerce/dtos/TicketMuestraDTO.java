package termicafueguina.ecommerce.dtos;

import termicafueguina.ecommerce.modelos.Ticket;

import java.util.List;
import java.util.stream.Collectors;

public class TicketMuestraDTO {
    private String nombreFiambreria;
    private String direccion;
    private long id;
    private String numero;
    private String nombreDueñoTicket;
    private String apellidoDueñoTicket;
    private List<String> productosCompradosPeso;
    private List<String> productosCompradosUnidad;

    //CONSTRUCTOR
    public TicketMuestraDTO(Ticket ticket) {
        this.productosCompradosPeso = ticket.getOrden().getordenProductoM2s().stream().map(producto -> producto.getProductoM2().getNombre()).collect(Collectors.toList());
        this.productosCompradosUnidad = ticket.getOrden().getOrdenProductoUnis().stream().map(producto1 -> producto1.getProductoUni().getNombre()).collect(Collectors.toList());
        this.numero = ticket.getNumero();
        this.nombreDueñoTicket = ticket.getOrden().getClient().getNombre();
        this.apellidoDueñoTicket = ticket.getOrden().getClient().getApellido();
    }

    //GETTERS
//    public long getId() {return id;}

    public String getNumero() {return numero;}
    public String getNombreDueñoTicket() {return nombreDueñoTicket;}
    public String getApellidoDueñoTicket() {return apellidoDueñoTicket;}

}
