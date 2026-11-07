/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author hilar
 */
public class Huesped {
 private String Identificacion;
 private String NombreCompleto;   
 private String Telefono;
 private String CorreoElectronico;

    public Huesped(String Identificacion, String NombreCompleto, String Telefono, String CorreoElectronico) {
        this.Identificacion = Identificacion;
        this.NombreCompleto = NombreCompleto;
        this.Telefono = Telefono;
        this.CorreoElectronico = CorreoElectronico;
    }

    public String getIdentificacion() {
        return Identificacion;
    }

    public String getNombreCompleto() {
        return NombreCompleto;
    }

    public String getTelefono() {
        return Telefono;
    }

    public String getCorreoElectronico() {
        return CorreoElectronico;
    }

    public void setNombreCompleto(String NombreCompleto) {
        this.NombreCompleto = NombreCompleto;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public void setCorreoElectronico(String CorreoElectronico) {
        this.CorreoElectronico = CorreoElectronico;
    }
 
 
}
