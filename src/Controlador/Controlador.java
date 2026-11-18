/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ActualizadorIndicadores;
import Modelo.GestorHotel;
import Modelo.Habitacion;
import Modelo.HabitacionFactory;
import Modelo.Huesped;
import Modelo.Reserva;
import Modelo.SupervisorReservas;
import java.time.LocalDate;

/**
 *
 * @author brand
 */
public class Controlador {
    private GestorHotel gestor = GestorHotel.getInstance();
    
    public boolean validarLogin(String usuario, String password){
        if (("recepcion1".equals(usuario) && "Hotel123".equals(password)) ||
            ("recepcion2".equals(usuario) && "Hotel456".equals(password))) {
            gestor.setRecepcionistaActivo(usuario);
            return true;
        }
        return false;
    }
    public String getRecepcionistaActivo() {
        return gestor.getRecepcionistaActivo();
    }

    public void registrarHuesped(String cedula, String nombre, String telefono, String correo) {
        Huesped huesped = new Huesped(cedula, nombre, telefono, correo);
        gestor.agregarHuesped(huesped);
    }

    public void registrarHabitacion(String tipo, String numero, double precioPorNoche, int capacidad, boolean extra) {
        Habitacion hab = HabitacionFactory.crearHabitacion(tipo, numero);
        gestor.agregarHabitacion(hab);
    }

    public void registrarReserva(String codigo, Huesped huesped, Habitacion habitacion,int cantidadPersonas,
            LocalDate fechaEntrada, LocalDate fechaSalida) {
        String recepcionistaActual = gestor.getRecepcionistaActivo();
        Reserva reserva = new Reserva(codigo, huesped, habitacion, cantidadPersonas, fechaEntrada, fechaSalida,
        fechaSalida, "Confirmada", recepcionistaActual);
        gestor.agregarReserva(reserva);
    }

    public void iniciarProcesosSegundoPlano() {
        Thread hilo1 = new Thread(new SupervisorReservas(), "HiloSupervisor");
        Thread hilo2 = new Thread(new ActualizadorIndicadores(), "HiloIndicadores");
        
        hilo1.start();
        hilo2.start();
    }
    
   
    
}
