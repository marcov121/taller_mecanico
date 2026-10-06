/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author James
 */
public class MySqlConexion {
    public static Connection getConexion(){
        Connection cn = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String user = "root";
            String pass= "mysql";
            String url = "jdbc:mysql://localhost/taller_mecanico?serverTimezone=UTC";
            cn = DriverManager.getConnection(url,user,pass);
        }catch(Exception e){
            e.printStackTrace();
        }
        return cn;
    }
}
