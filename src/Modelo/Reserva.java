/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author brand
 */
public class Reserva {
    private String codigo;
    private Huesped huesped;
    private Habitacion habitacion;
    private int cantidadPersonas;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private LocalDate fechaSalidaReal;
    private int cantidadNoches;
    private long importeTotal;
    private String estado;
    private String recepcionista;  

    public Reserva(String codigo, Huesped huesped, Habitacion habitacion, 
            int cantidadPersonas, LocalDate fechaEntrada, LocalDate fechaSalida,
            LocalDate fechaSalidaReal, String estado, String recepcionista) {
        this.codigo = codigo;
        this.huesped = huesped;
        this.habitacion = habitacion;
        this.cantidadPersonas = cantidadPersonas;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.fechaSalidaReal = fechaSalidaReal;       
        this.estado = estado;
        this.recepcionista = recepcionista;
        this.importeTotal = cantidadNoches * habitacion.tarifaPorNoche();
        this.cantidadNoches = (int) ChronoUnit.DAYS.between(fechaEntrada, fechaSalida);
    }

    public String getCodigo() {
        return codigo;
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public LocalDate getFechaSalidaReal() {
        return fechaSalidaReal;
    }

    public int getCantidadNoches() {
        return cantidadNoches;
    }

    public long getImporteTotal() {
        return importeTotal;
    }

    public String getEstado() {
        return estado;
    }

    public String getRecepcionista() {
        return recepcionista;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public void setFechaSalidaReal(LocalDate fechaSalidaReal) {
        this.fechaSalidaReal = fechaSalidaReal;
    }

    public void setImporteTotal(long importeTotal) {
        this.importeTotal = importeTotal;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setRecepcionista(String recepcionista) {
        this.recepcionista = recepcionista;
    }
    
    
    
}
