/*
 * Fecha de creacion: 30/03/2023 21:04:21
 * Version: v.0.1
 * Proyecto: Clase para la conexion a la base datos.
 * Cambio de base de datos de MySQL a Acces: https://youtu.be/KunjMvPIVDE?list=PLMPZIgg1n4JlSr_81Lhp8lem8Dtfe9qxV
 */

package modelos;

import java.sql.*;

/**
 * @author Willy Stbn
 */

public class Conexion {
    
    Connection con;
    
    public Connection getConnection(){
        
        try {
            
            //Conectar a la base de datos:
            String home = System.getProperty("user.home");
            
            //String acces = "jdbc:ucanaccess://" + home + ":/Documents/SistemaDeVentas/baseDatos/SistemaVenta.accdb";
            
            //String rutaNueva = "jdbc:ucanaccess://src/bd/SistemaVen.accdb";
            String access = "jdbc:ucanaccess://" + home + "/Documents/SistemaDeVentas/baseDatos/SistemaVen.accdb";
            //String mysql = "jdbc:mysql://localhost:3306/sistemaventa?serverTimezone=UTC";
            
            con = DriverManager.getConnection(access, "root", "");
            
            return con;
            
        } catch (SQLException e) {
            
            System.err.println("Error al conectar a la base de datos. Conexion\n");
            System.err.println(e.toString());
        }
        return null;
    }
}
