package org.adrianarbizu.model;

public class TicketSoporte {
    private int ticketSoporteId;
    private String descripcion;
    private String estatus;
    private String cliente;
    private int facturaId;

    public TicketSoporte(int ticketSoporteId, String descripcion, String estatus, String cliente, int facturaId) {
        this.ticketSoporteId = ticketSoporteId;
        this.descripcion = descripcion;
        this.estatus = estatus;
        this.cliente = cliente;
        this.facturaId = facturaId;
    }

    public int getTicketSoporteId() {
        return ticketSoporteId;
    }

    public void setTicketSoporteId(int ticketSoporteId) {
        this.ticketSoporteId = ticketSoporteId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(int facturaId) {
        this.facturaId = facturaId;
    }
}

