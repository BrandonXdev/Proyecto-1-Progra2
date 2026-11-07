/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.lang.classfile.Signature;


public class HabitacionFamiliar extends Habitacion {
   public HabitacionFamiliar(String numero){
    super(numero); 
   }
   public HabitacionFamiliar(String numero){
      super(numero);
   }
   public int CapacidadMaxima(){
       return 4;
   }
   public long TarifaPorNoche(){
       return 65000;
   }
   public String Tipo(){
       return "Hbitacion Familiar";
   }
}
