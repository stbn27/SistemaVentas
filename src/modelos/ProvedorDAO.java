/*
 * Fecha de creacion: 31/03/2023 23:05:31
 * Version: v.0.1
 * Detalles: 
 */

package modelos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;



/**
 * @author Willy Stbn
 */

public class ProvedorDAO {
    
    Connection cn;
    Conexion con = new Conexion();
    PreparedStatement pst;
    ResultSet rs;
    
    public boolean RegistrarProvedor(Provedor pr){
        String instruccionSQL = "insert into proveedor (ruc, nombre, telefono, direccion, razon_social) values (?,?,?,?,?)";
        
        try {
            cn = con.getConnection();
            pst = cn.prepareStatement(instruccionSQL);
            
            pst.setInt(1, pr.getRucc());
            pst.setString(2, pr.getNombre());
            pst.setInt(3, pr.getTelefono());
            pst.setString(4, pr.getDireccion());
            pst.setString(5, pr.getRazonSocial());
            
            pst.execute();            
            return true;
            
        } catch (SQLException e) {
            System.err.println("Error al Registrar el provedor.\n" + e);
            JOptionPane.showMessageDialog(null, "¡Error al registrar provedor!");
            return false;
        } finally{
            try {
                cn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexion. \n" + e);
            }
        }
    }
    
    public List ListarProvedor(){
        List<Provedor> ListarPro = new ArrayList();
        String InstruccionSQL = "select * from proveedor";
        
        try {
            cn = con.getConnection();
            pst = cn.prepareStatement(InstruccionSQL);
            rs = pst.executeQuery();
            
            while(rs.next()){
                Provedor pr = new Provedor();
                pr.setId(rs.getInt("id"));
                pr.setRucc(rs.getInt("ruc"));
                pr.setNombre(rs.getString("nombre"));
                pr.setTelefono(rs.getInt("telefono"));
                pr.setDireccion(rs.getString("direccion"));
                pr.setRazonSocial(rs.getString("direccion"));
                
                ListarPro.add(pr);
            }
        } catch (SQLException e) {
            System.err.println("Error al consuntal informacion de los provedores.\n");
            System.err.println("Clase: ProvedorDAO.\n" + e);
            JOptionPane.showMessageDialog(null, "¡Error al cargar información de los provedores!\n"
                    + "Contacte al administrador.");
        }
        return ListarPro;
    }
    
    public boolean EliminarProvedor(int id){
        String intruccionSQL = "delete from proveedor where id=?";
        
        try {
            cn = con.getConnection();
            pst = cn.prepareStatement(intruccionSQL);
            pst.setInt(1, id);
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar.\n");
            System.err.println("Clase: ClientesDAO.\n" + e);
            return false;
        } finally{
            try {
                cn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión.\n" + e);
            }
        }
    }
    
    public boolean ModificarProvedor(Provedor pr){
        String intruccionSQL = "update proveedor set ruc=?, nombre=?, telefono=?, direccion=?, razon_social=? where id=?";
        
        try {
            cn = con.getConnection();
            pst = cn.prepareStatement(intruccionSQL);
            
            pst.setInt(1, pr.getRucc());
            pst.setString(2, pr.getNombre());
            pst.setInt(3, pr.getTelefono());
            pst.setString(4, pr.getDireccion());
            pst.setString(5, pr.getRazonSocial());
            pst.setInt(6, pr.getId());
            pst.execute();
            
            return true;
        } catch (SQLException e) {
            System.err.println("Error al modificar cliente.\n");
            System.err.println("ClaseDAO.\n" + e);
            return false;
        }finally{
            try {
                cn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexion.\n" + e.toString());
            }
        }
         
         
    }
}
