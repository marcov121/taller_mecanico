/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.entities;

/**
 *
 * @author James
 */
public class Vehiculo {
    private int idVehiculo;
    private int idPersona;
    private String placa;
    private int idMarca;
    private int idModelo;
    private Integer anio;
    private String color;
    private String nombreCliente;

    public Vehiculo() {
    }

    public Vehiculo(int idVehiculo, int idPersona, String placa, int idMarca, int idModelo, Integer anio, String color) {
        this.idVehiculo = idVehiculo;
        this.idPersona = idPersona;
        this.placa = placa;
        this.idMarca = idMarca;
        this.idModelo = idModelo;
        this.anio = anio;
        this.color = color;
    }
    
    

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public int getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
}
