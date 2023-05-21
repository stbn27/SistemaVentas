/*
 * Fecha de creacion: 30/03/2023 22:42:48
 * Version: v.0.1
 * Detalles: 
 */

package modelos;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * @author Willy Stbn
 */

public class ClienteDAO {
    
    Conexion con = new Conexion();
    Connection cn;
    PreparedStatement pst;
    ResultSet rs;
    
    public boolean RegistrarCliente(Clientes cl){
        String instruccionSQL = "INSERT INTO clientes (dni, nombre, telefono, direccion, razon_social) VALUES (?,?,?,?,?)";
        
        try {
            cn = con.getConnection();
            pst = cn.prepareStatement(instruccionSQL);
            
            pst.setInt(1, cl.getDni());
            pst.setString(2, cl.getNombre());
            pst.setInt(3, cl.getTelefono());
            pst.setString(4, cl.getDireccion());
            pst.setString(5, cl.getRazon_Social());
            
            pst.execute();
            
            return true;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "¡Error al guardar la información!");
            System.err.println("Error al guardar la iformacion del cliente en la base de datos.\n");
            System.err.println("Clase: ClienteDAO\n " + e.toString());
            return false;
            
        } finally{
            try {
                cn.close();
            } catch (SQLException ex) {
                System.out.println("¡¡Conexion cerrada!!");
            }
        }
                
    }

    public List ListarCliente(){
        
        List<Clientes> ListaCl = new ArrayList();
        
        String IntruccionSQl = "select * from clientes";
        
        try {
            cn = con.getConnection();
            pst = cn.prepareStatement(IntruccionSQl);
            rs = pst.executeQuery();
            
            while(rs.next()){
                Clientes cl = new Clientes();
                
                cl.setId(rs.getInt("id"));
                cl.setDni(rs.getInt("dni"));
                cl.setNombre(rs.getString("nombre"));
                cl.setTelefono(rs.getInt("telefono"));
                cl.setDireccion(rs.getString("direccion"));
                cl.setRazon_Social(rs.getString("razon_social"));
                
                ListaCl.add(cl);
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar informacion de los clientes.\n");
            System.err.println("Clase ClienteDAO.\n" + e.toString());            
        } 
        return ListaCl;
    }
    
    public boolean EliminarClientes(int id){
        String instruccionSQL = "delete from clientes where id = ?";
        try {
            pst = cn.prepareStatement(instruccionSQL);
            pst.setInt(1, id);
            pst.execute();
            
            return true;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar el cliente\n");
            System.err.println("Clase: ClienteDAO.\n" + e.toString());
            return false;
        } finally{
            try {
                cn.close();
            } catch (SQLException er) {
                System.err.println("¡Error al cerrar la conexion con la base de datos!.\n" + er);
            }
        }
    }
    
    public boolean ModificarCliente(Clientes cl){
        String intruccionSQL = "update clientes set dni=?, nombre=?, telefono=?, direccion=?, razon_social=? where id=?";
        
        try {
            pst = cn.prepareStatement(intruccionSQL);
            pst.setInt(1, cl.getDni());
            pst.setString(2, cl.getNombre());
            pst.setInt(3, cl.getTelefono());
            pst.setString(4, cl.getDireccion());
            pst.setString(5, cl.getRazon_Social());
            pst.setInt(6, cl.getId());
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al modificar informacion del cliente.\n" + e);
            JOptionPane.showMessageDialog(null, "¡Error al modificar la informacion del cliente!.");
            return false;
        } finally{
            try {
                cn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerra conexion con la base datos.");
            }
        }
    }
    
    public Clientes BuscarCLiente(int dni){
        Clientes cl = new Clientes();
        String intruccionSQL = "select * from clientes where dni = ?";
        
        try {
            cn = con.getConnection();
            pst = cn.prepareStatement(intruccionSQL);
            pst.setInt(1, dni);
            rs = pst.executeQuery();
            
            if(rs.next()){
                cl.setNombre(rs.getString("nombre"));
                cl.setTelefono(rs.getInt("telefono"));
                cl.setDireccion(rs.getString("direccion"));
                cl.setRazon_Social(rs.getString("razon_social"));
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar informacion del cliente.\n Clase: ClienteDAO\n" + e);
        }
        return cl;
        
    }
    
}
