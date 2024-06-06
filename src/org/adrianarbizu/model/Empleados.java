package org.adrianarbizu.model;

import java.sql.Time;

public class Empleados {

    private int empleadoId;
    private String nombreEmpleado;
    private String apellidoEmpleado;
    private double sueldo;
    private Time horaEntrada;
    private Time horaSalida;
    private int cargoId;
    private int encargadoId;

    // Constructor
    public Empleados(int empleadoId, String nombreEmpleado, String apellidoEmpleado, double sueldo, Time horaEntrada, Time horaSalida, int cargoId, int encargadoId) {
        this.empleadoId = empleadoId;
        this.nombreEmpleado = nombreEmpleado;
        this.apellidoEmpleado = apellidoEmpleado;
        this.sueldo = sueldo;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.cargoId = cargoId;
        this.encargadoId = encargadoId;
    }

    public Empleados(int empleadoId, String nombreEmpleado, String apellidoEmpleado, double sueldo, Time horaentrada, Time horaSalida, String cargoId, String encargadoId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Empleados(int aInt, String string, String string0, double aDouble, Time time, Time time0, int aInt0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Getters and Setters
    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getApellidoEmpleado() {
        return apellidoEmpleado;
    }

    public void setApellidoEmpleado(String apellidoEmpleado) {
        this.apellidoEmpleado = apellidoEmpleado;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public Time getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Time horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Time getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Time horaSalida) {
        this.horaSalida = horaSalida;
    }

    public int getCargoId() {
        return cargoId;
    }

    public void setCargoId(int cargoId) {
        this.cargoId = cargoId;
    }

    public int getEncargadoId() {
        return encargadoId;
    }

    public void setEncargadoId(int encargadoId) {
        this.encargadoId = encargadoId;
    }

    // toString method for easy printing
    @Override
    public String toString() {
        return "Empleados{" +
                "empleadoId=" + empleadoId +
                ", nombreEmpleado='" + nombreEmpleado + '\'' +
                ", apellidoEmpleado='" + apellidoEmpleado + '\'' +
                ", sueldo=" + sueldo +
                ", horaEntrada=" + horaEntrada +
                ", horaSalida=" + horaSalida +
                ", cargoId=" + cargoId +
                ", encargadoId=" + encargadoId +
                '}';
        
    }
}


