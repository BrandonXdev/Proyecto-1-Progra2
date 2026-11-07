/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author brand
 */
public class HabitacionFactory {
    
    public static Habitacion crearHabitacion(String tipo, String numero){
        if (tipo == null) {
            return null;
        }
        
    switch (tipo.toLowerCase()) {
            case "individual":
                return new HabitacionIndividual(numero);
            case "doble":
                return new HabitacionDoble(numero);
            case "familiar":
                return new HabitacionFamiliar(numero);
            default:
                return null; 
        }
    }
}

