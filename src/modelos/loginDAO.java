/*
 * Fecha de creacion: 30/03/2023 21:25:48
 * Version: v.0.1
 * Detalles: 
 */

package modelos;

import java.sql.*;


/**
 * @author Willy Stbn
 */

public class loginDAO {
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    Conexion cn = new Conexion();
    
    public login log(String correo, String pass){
        login l = new login();
        String sql = "SELECT * FROM usuarios  WHERE correo=? AND pass=?";
        
        try {
            con = cn.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, correo);
            pst.setString(2, pass);
            rs = pst.executeQuery();
            
            if(rs.next()){
                l.setId(rs.getInt("id"));
                l.setNombre(rs.getString("nombre"));
                l.setCorreo(rs.getString("correo"));
                l.setPass(rs.getString("pass"));
                l.setRol(rs.getString("rol"));
            }
            
        } catch (SQLException e) {
            System.err.println("Error al consultar la base de datos\n");
            System.err.println("Clase LoginDAO\n");
            System.err.println(e.toString());
        }
        return l;
    }
        
    public boolean RegistrarUsuarios(login reg){
        String intruccionSQL = "INSERT INTO usuarios (nombre, correo, pass, rol) VALUES (?,?,?,?)";
        try {
            con = cn.getConnection();
            pst = con.prepareStatement(intruccionSQL);
            pst.setString(1, reg.getNombre());
            pst.setString(2,reg.getCorreo());
            pst.setString(3, reg.getPass());
            pst.setString(4, reg.getRol());
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar nuevo usuario\n" + e);
            return false;
        }
    }
}
