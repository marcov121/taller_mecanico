/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/* Patrón Singleton: una sola instancia guarda la configuración y carga el driver una única vez*/
public final class MySqlConexion {
    private static volatile MySqlConexion instancia;
    private final String url = "jdbc:mysql://localhost/taller_mecanico?serverTimezone=UTC";
    private final String user = "root";
    private final String pass = "mysql";
    
    // Constructor privado
    private MySqlConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static MySqlConexion getInstacia() {
        if (instancia == null) {
            synchronized (MySqlConexion.class) {
                if (instancia == null) instancia = new MySqlConexion();
            }
        }
        return instancia;
    }
    
    public Connection abrirConexion() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }
    
    // Se mantiene el método estático original para no romper los DAO
    public static Connection getConexion() {
        try {
            return getInstacia().abrirConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}