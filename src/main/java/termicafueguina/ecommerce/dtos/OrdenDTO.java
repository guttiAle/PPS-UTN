package termicafueguina.ecommerce.dtos;

import termicafueguina.ecommerce.modelos.Orden;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class OrdenDTO {
    private long id;
    private LocalDateTime fecha;
    private boolean envio;
    private boolean pagado;
    private double total;
//    private TicketDTO ticket;
    private Set<OrdenProductoUniDTO> ordenProductoUnis;
    private Set<OrdenProductoM2DTO> ordenProductoM2s;

    //CONSTRUCTOR

    public OrdenDTO(Orden orden) {
        this.id = orden.getId();
        this.fecha = orden.getFecha();
        this.envio = orden.getEnvio();
        this.pagado = orden.getPagado();
        this.total = orden.getTotal();
//        this.ticket = new TicketDTO(orden.getTicket());
        this.ordenProductoUnis = orden.getOrdenProductoUnis().stream().map(opu -> new OrdenProductoUniDTO(opu)).collect(Collectors.toSet());
        this.ordenProductoM2s = orden.getordenProductoM2s().stream().map(opp -> new OrdenProductoM2DTO(opp)).collect(Collectors.toSet());

    }

    //GETTERS
    public long getId() {return id;}
    public LocalDateTime getFecha() {return fecha;}
    public boolean isEnvio() {return envio;}
    public boolean isPagado() {return pagado;}
    public double getTotal() {return total;}
//    public TicketDTO getTicket() {return ticket;}
    public Set<OrdenProductoUniDTO> getOrdenProductoUnis() {return ordenProductoUnis;}
    public Set<OrdenProductoM2DTO> getOrdenProductoM2s() {return ordenProductoM2s;}

}
