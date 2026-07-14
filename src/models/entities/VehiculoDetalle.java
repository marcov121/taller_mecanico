/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.entities;

/**
 *
 * @author marco
 */
public class VehiculoDetalle {
    private int idVehiculo;
    private String clienteNombreCompleto, documentoCliente, marca, modelo, placa;
    private Integer anio;
    private String color;

    public VehiculoDetalle() {
    }

    public VehiculoDetalle(int idVehiculo, String clienteNombreCompleto, String marca, String modelo, String placa, Integer anio, String color) {
        this.idVehiculo = idVehiculo;
        this.clienteNombreCompleto = clienteNombreCompleto;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.anio = anio;
        this.color = color;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }
    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }
    public String getClienteNombreCompleto() {
        return clienteNombreCompleto;
    }
    public void setClienteNombreCompleto(String clienteNombreCompleto) {
        this.clienteNombreCompleto = clienteNombreCompleto;
    }
    public String getDocumentoCliente() {
        return documentoCliente;
    }
    public void setDocumentoCliente(String documentoCliente) {
        this.documentoCliente = documentoCliente;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
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
}
