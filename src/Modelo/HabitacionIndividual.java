/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author brand
 */
public class HabitacionIndividual extends Habitacion {

    public HabitacionIndividual(String numero) {
        super(numero);
    }

    @Override
    public int capacidadMaxima() {
        return 1;
        
    }

    @Override
    public long tarifaPorNoche() {
        return 25000;
            
    }

    @Override
    public String tipo() {
        return "Habitacion Individual";
        
    }
    
   
    
    
    
}
