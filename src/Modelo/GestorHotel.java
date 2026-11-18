/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.awt.List;
import java.time.LocalDate;
import java.util.ArrayList;

public class GestorHotel {
    private static GestorHotel instancia;
    
    private ArrayList<Huesped> huespedes = new ArrayList<>();
    private ArrayList<Habitacion> habitaciones = new ArrayList<>();
    private ArrayList<Reserva> reservas = new ArrayList<>();
    private String recepcionistaActivo = null;
    
    private GestorHotel(){}
    
    public static synchronized GestorHotel getInstance(){
        return (instancia == null) ? (instancia = new GestorHotel()) : instancia;
    }

    public String getRecepcionistaActivo() {
        return recepcionistaActivo;
    }

    public void setRecepcionistaActivo(String recepcionistaActivo) {
        this.recepcionistaActivo = recepcionistaActivo;
    }

    public ArrayList<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public ArrayList<Huesped> getHuespedes() {
        return huespedes;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }
    
    
    public void agregarHuesped(Huesped huesped){
        for (Huesped h : huespedes) {
            if (h.getIdentificacion().equalsIgnoreCase(huesped.getIdentificacion())) {
                throw new IllegalArgumentException("El huésped con esta identificación ya existe");      
            } 
        }

        huespedes.add(huesped);
    }
    
    public void agregarReserva(Reserva reserva){
        for (Reserva r : reservas) {
            if (r.getCodigo().equalsIgnoreCase(reserva.getCodigo())) {
                throw new IllegalArgumentException("Ya existe una reserva con el codigo: "
                        + reserva.getCodigo());  
            }
            if (reserva.getCantidadNoches() <=  0)  {
                throw new IllegalArgumentException("La fecha de salida debe de ser"
                        + " posterior a la fecha de entrada"); 
            }
            
        }
        reservas.add(reserva);
    }
    
    public void agregarHabitacion(Habitacion habitacion) {
        for (Habitacion h : habitaciones) {
            if (h.getNumero().equalsIgnoreCase(habitacion.getNumero())) {
                throw new IllegalArgumentException("La habitación número " + habitacion.getNumero() + " ya está registrada.");
            }
        }
        habitaciones.add(habitacion);  
    }
    
    public synchronized void procesarIndicadoresCiclo() {
        int totalHabitaciones = habitaciones.size();
        int confirmadas = 0;
        int vencidas = 0;
        int ocupadas = 0;

        for (Reserva r : reservas) {
            String estado = r.getEstado();
            if ("Confirmada".equalsIgnoreCase(estado)) confirmadas++;
            else if ("Vencida".equalsIgnoreCase(estado)) vencidas++;
            else if ("Hospedado".equalsIgnoreCase(estado)) ocupadas++;
        }
        long ingresos = calcularIngresosEsperados();

        System.out.println("--- INDICADORES DEL HOTEL ---");
        System.out.println("Total Habitaciones: " + totalHabitaciones);
        System.out.println("Reservas Confirmadas: " + confirmadas);
        System.out.println("Reservas Vencidas: " + vencidas);
        System.out.println("Ingresos Esperados (Recursivo): ₡" + ingresos);
        System.out.println("-----------------------------");
        
 }
    public synchronized void actualizarReservasVencidas(LocalDate hoy) {
        for (Reserva r : reservas) {
            if ("Confirmada".equalsIgnoreCase(r.getEstado())) {
                if (r.getFechaEntrada().isBefore(hoy)) {
                    r.setEstado("Vencida");
                    System.out.println("[GestorHotel] Reserva "  + r.getCodigo() +  " actualizada a Vencida automáticamente.");   
                }     
            }   
        }     
    } 
    
    public long calcularIngresosEsperados(){
        return calcularIngresosEsperadosR(reservas, 0);
    }
    
    private long calcularIngresosEsperadosR(ArrayList<Reserva> lista, int indice) {
 
        if (lista == null || lista.isEmpty() || indice >= lista.size()) {
            return 0;
        }
        
        Reserva actual = lista.get(indice);
        long importe = "Confirmada".equalsIgnoreCase(actual.getEstado()) ? actual.getImporteTotal() : 0;
        
        return importe + calcularIngresosEsperadosR(lista, indice + 1);
    }
    public Huesped buscarHuesped(String identificacion) {
        for (Huesped h : huespedes) {
            if (h.getIdentificacion().equalsIgnoreCase(identificacion)) {
                return h;
            }
        }
        return null;
    }

    public Habitacion buscarHabitacion(String numero) {
        for (Habitacion hab : habitaciones) {
            if (hab.getNumero().equalsIgnoreCase(numero)) {
                return hab;
            }
        }
        return null;
    }
    public ArrayList<Habitacion> obtenerHabitacionesDisponibles(LocalDate fechaConsulta) {
        ArrayList<Habitacion> disponibles = new ArrayList<>();
        
        for (Habitacion hab : habitaciones) {
            boolean ocupada = false;
            for (Reserva r : reservas) {
                // Verificamos si la reserva está activa/confirmada/hospedada
                String estado = r.getEstado();
                if ("Confirmada".equalsIgnoreCase(estado) || "Hospedado".equalsIgnoreCase(estado) || "Activa".equalsIgnoreCase(estado)) {
                    // Si la fecha de consulta está entre la entrada y la salida de la reserva
                    if (!fechaConsulta.isBefore(r.getFechaEntrada()) && fechaConsulta.isBefore(r.getFechaSalida())) {
                        if (r.getHabitacion().getNumero().equalsIgnoreCase(hab.getNumero())) {
                            ocupada = true;
                            break;
                        }
                    }
                }
            }
            if (!ocupada) {
                disponibles.add(hab);
            }
        }
        return disponibles;
    }
    public Reserva buscarReserva(String codigo) {
        for (Reserva r : reservas) {
            if (r.getCodigo().equalsIgnoreCase(codigo)) {
                return r;
            }
        }
        return null;
    }
}    