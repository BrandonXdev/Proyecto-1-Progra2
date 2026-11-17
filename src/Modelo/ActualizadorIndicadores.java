/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author brand
 */
public class ActualizadorIndicadores implements Runnable {
    @Override
    public void run() {
        System.out.println("ActualizadorIndicadores: Iniciando...");

        for (int i = 1; i <= 5; i++) {
            System.out.println("ActualizadorIndicadores: Ciclo " + i);
            

            GestorHotel.getInstance().procesarIndicadoresCiclo();

            if (i < 5) {
                try {
                    Thread.sleep(1000); 
                } catch (InterruptedException e) {
                    System.out.println("ActualizadorIndicadores: Interrumpido.");
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }

        System.out.println("ActualizadorIndicadores: Finalizado.");
    }
    
}
