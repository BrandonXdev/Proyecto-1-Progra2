/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalDate;

/**
 *
 * @author brand
 */
public class SupervisorReservas implements Runnable {


   @Override
    public void run() {
        System.out.println("SupervisorReservas: Iniciando...");

        for (int i = 1; i <= 5; i++) {
            System.out.println("SupervisorReservas: Ciclo " + i);

          
            GestorHotel.getInstance().actualizarReservasVencidas(LocalDate.now());

            if (i < 5) {
                try {
                    Thread.sleep(1000); 
                } catch (InterruptedException e) {
                    System.out.println("SupervisorReservas: Interrumpido.");
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }

        System.out.println("SupervisorReservas: Finalizado");
    }
}
