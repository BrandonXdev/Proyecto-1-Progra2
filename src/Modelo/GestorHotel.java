/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author brand
 */
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
    
    
    
}
