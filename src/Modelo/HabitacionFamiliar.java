/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

public class HabitacionFamiliar extends Habitacion {
   public HabitacionFamiliar(String numero){
    super(numero); 
   }
   
   
   @Override
   public int capacidadMaxima(){
       return 4;
   }
   @Override
   public long tarifaPorNoche(){
       return 65000;
   }
   @Override
   public String tipo(){
       return "Habitacion Familiar";
   }
}
