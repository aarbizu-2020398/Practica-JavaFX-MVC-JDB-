package org.adrianarbizu.bean;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;


public class Factura {
    private int facturaId;
    private int codigoCliente;
    private int empleadoId;
    private LocalDate facha;
    private  Time hora ;
    private Double total;

    public Factura() {
        
    }

    public Factura(int facturaId, int codigoCliente, int empleadoId, LocalDate facha, Time hora, Double total) {
        this.facturaId = facturaId;
        this.codigoCliente = codigoCliente;
        this.empleadoId = empleadoId;
        this.facha = facha;
        this.hora = hora;
        this.total = total;
    }

    public int getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(int facturaId) {
        this.facturaId = facturaId;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public LocalDate getFacha() {
        return facha;
    }

    public void setFacha(LocalDate facha) {
        this.facha = facha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setFecha(Date valueOf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}