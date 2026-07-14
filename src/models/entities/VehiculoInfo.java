/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.entities;

/**
 *
 * @author marco
 */
public class VehiculoInfo {
    private int idVehiculo;
    private int idPersona;
    private String placa;
    private String clienteNombreCompleto;

    public VehiculoInfo() {
    }
    public VehiculoInfo(int idVehiculo, int idPersona, String placa, String clienteNombreCompleto) {
        this.idVehiculo = idVehiculo;
        this.idPersona = idPersona;
        this.placa = placa;
        this.clienteNombreCompleto = clienteNombreCompleto;
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
    public String getClienteNombreCompleto() {
        return clienteNombreCompleto;
    }
    public void setClienteNombreCompleto(String clienteNombreCompleto) {
        this.clienteNombreCompleto = clienteNombreCompleto;
    }
}
