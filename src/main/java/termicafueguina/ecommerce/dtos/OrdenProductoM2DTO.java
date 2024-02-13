package termicafueguina.ecommerce.dtos;

import termicafueguina.ecommerce.modelos.OrdenProductoM2;

public class OrdenProductoM2DTO {
    private long id;
    private String nombreProductoM2;
    private double cantidadM2;
    private double precioM2;
    private double total;
    private double puntuacion;
    private double descuento;

    //CONSTRUCTOR
    public OrdenProductoM2DTO(OrdenProductoM2 ordenProductoM2) {
        this.id = ordenProductoM2.getId();
        this.cantidadM2 = ordenProductoM2.getCantidadM2();
        this.precioM2 = ordenProductoM2.getProductoM2().getPrecio();
        this.nombreProductoM2 = ordenProductoM2.getProductoM2().getNombre();
        this.total = ordenProductoM2.getTotal();
        this.puntuacion = ordenProductoM2.getProductoM2().getPuntuaciones().stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        this.descuento = ((ordenProductoM2.getProductoM2().getDescuento()) * 100) -100;
    }

    //GETTERS
    public long getId() {return id;}
    public String getNombreProductoM2() {return nombreProductoM2;}
    public double getCantidadM2() {return cantidadM2;}
    public double getPrecioM2() {return precioM2;}
    public double getTotal() {return total;}
    public double getPuntuacion() {return puntuacion;}
    public double getDescuento() {return descuento;}
}
