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
    public java.util.List<Huesped> getListaHuespedes() {
        return gestor.getHuespedes();
    }

    public void registrarHuesped(String cedula, String nombre, String telefono, String correo) {
        Huesped huesped = new Huesped(cedula, nombre, telefono, correo);
        gestor.agregarHuesped(huesped);
    }


    public void registrarHabitacion(String tipo, String numero, double precioPorNoche, int capacidad, boolean extra) {
        Habitacion hab = HabitacionFactory.crearHabitacion(tipo, numero);
        gestor.agregarHabitacion(hab);
    }

    public void registrarHabitacion(String tipo, String numero, String tarifaStr, String capacidadStr) {
        double tarifa = Double.parseDouble(tarifaStr);
        int capacidad = Integer.parseInt(capacidadStr);
        registrarHabitacion(tipo, numero, tarifa, capacidad, false);
    }

    public void registrarReserva(String codigo, Huesped huesped, Habitacion habitacion, int cantidadPersonas,
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
    public int getTotalHabitaciones() {
        return gestor.getHabitaciones().size();
    }

    public int getHabitacionesOcupadas() {
        int count = 0;
        for (Reserva r : gestor.getReservas()) {
            if ("Hospedado".equalsIgnoreCase(r.getEstado())) {
                count++;
            }
        }
        return count;
    }

    public int getHabitacionesNoOcupadas() {
        return getTotalHabitaciones() - getHabitacionesOcupadas();
    }

    public int getReservasConfirmadas() {
        int count = 0;
        for (Reserva r : gestor.getReservas()) {
            if ("Confirmada".equalsIgnoreCase(r.getEstado())) {
                count++;
            }
        }
        return count;
    }

    public int getReservasVencidas() {
        int count = 0;
        for (Reserva r : gestor.getReservas()) {
            if ("Vencida".equalsIgnoreCase(r.getEstado())) {
                count++;
            }
        }
        return count;
    }

    public String getIngresosEsperadosTexto() {
        long ingresos = gestor.calcularIngresosEsperados(); 
        return "₡ " + ingresos;
    }
    public java.util.List<Habitacion> getListaHabitaciones() {
        return gestor.getHabitaciones();
    }
    public void registrarReserva(String codigo, String idHuesped, String numHabitacion, 
            int personas, java.time.LocalDate entrada, java.time.LocalDate salida) throws Exception {
        
        Huesped h = gestor.buscarHuesped(idHuesped);
        if (h == null) {
            throw new Exception("El huésped con identificación " + idHuesped + " no está registrado.");
        }
        
        Habitacion hab = gestor.buscarHabitacion(numHabitacion);
        if (hab == null) {
            throw new Exception("La habitación " + numHabitacion + " no existe.");
        }
        
        if (personas > hab.capacidadMaxima()) {
            throw new Exception("La cantidad de personas supera la capacidad máxima de la habitación (" + hab.capacidadMaxima() + ").");
        }
        
               Reserva nuevaReserva = new Reserva(codigo, h, hab, personas, entrada, salida, null, "Confirmada", gestor.getRecepcionistaActivo());
        
        gestor.agregarReserva(nuevaReserva);
    }
    
    public java.util.List<Reserva> getListaReservas() {
        return gestor.getReservas();
    }
    public java.util.List<Habitacion> consultarDisponibilidad(String fechaStr) throws Exception {
        LocalDate fecha = LocalDate.parse(fechaStr.trim()); // Espera formato YYYY-MM-DD
        return gestor.obtenerHabitacionesDisponibles(fecha);
    }
    public Reserva buscarReservaPorCodigo(String codigo) throws Exception {
        Reserva r = gestor.buscarReserva(codigo);
        if (r == null) {
            throw new Exception("No se encontró ninguna reserva con el código: " + codigo);
        }
        return r;
    }

    public void procesarCheckInCheckOut(String codigo, boolean esCheckIn) throws Exception {
        Reserva r = gestor.buscarReserva(codigo);
        if (r == null) {
            throw new Exception("Reserva no encontrada.");
        }
        
        if (esCheckIn) {
            if (!"Confirmada".equalsIgnoreCase(r.getEstado()) && !"Vencida".equalsIgnoreCase(r.getEstado())) {
                throw new Exception("La reserva debe estar Confirmada para hacer Check-in. Estado actual: " + r.getEstado());
            }
            r.setEstado("Hospedado");
        } else {
            if (!"Hospedado".equalsIgnoreCase(r.getEstado())) {
                throw new Exception("La reserva debe estar en estado 'Hospedado' para hacer Check-out.");
            }
            r.setEstado("Finalizada");
            r.setFechaSalidaReal(java.time.LocalDate.now());
        }
    }
 
}