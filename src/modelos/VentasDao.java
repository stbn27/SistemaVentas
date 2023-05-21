/*
 * Fecha de creacion: 02/04/2023 16:49:09
 * Version: v.0.1
 * Detalles: 
 */
package modelos;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Willy Stbn
 */
public class VentasDao {

    Connection cn;
    Conexion con = new Conexion();
    PreparedStatement pst;
    ResultSet rs;
    int r;

    public int IDVenta() {
        int id = 0;
        String intruccionSQL = "SELECT MAX(id) FROM ventas";
        try {
            cn = con.getConnection();
            pst = cn.prepareStatement(intruccionSQL);
            rs = pst. executeQuery();
            
            if(rs.next()){
                id = rs.getInt(1);
            }
            
        } catch (SQLException e) {
            System.out.println("Clase: VentasDAO");
            System.err.println("Error al consultar ID maximo de ventas.\n" + e);
        }
        return id;
    }

    public int RegistrarVenta(Venta v) {
        String intruccionSQL = "INSERT INTO ventas (cliente, vendedor, total, fecha) VALUES (?,?,?,?)";

        try {
            cn = con.getConnection();
            pst = cn.prepareStatement(intruccionSQL);
            pst.setString(1, v.getCliente());
            pst.setString(2, v.getVendedor());
            pst.setDouble(3, v.getTotal());
            pst.setString(4, v.getFecha());

            pst.execute();

        } catch (SQLException e) {
            System.err.println("Error al insertar informacion de la venta.\n");
            System.err.println("CLase: VentasDAO\n" + e);

        } finally {
            try {
                cn.close();
            } catch (SQLException e) {
                System.out.println("Clase: VentasDAO.\n");
                System.err.println("Error al cerrar al cerrar conexion.");
            }
        }
        return r;
    }

    public int RegistrarDetalle(Detalle det) {
        String intruccionSQL = "insert into detalle (cod_pro, cantidad, precio, id_venta) values (?,?,?,?)";
        try {
            cn = con.getConnection();
            pst = cn.prepareStatement(intruccionSQL);
            pst.setString(1, det.getCod_pro());
            pst.setInt(2, det.getCantidad());
            pst.setDouble(3, det.getPrecio());
            pst.setInt(4, det.getId());
            pst.execute();

        } catch (SQLException e) {
            System.out.println("Clase: VentasDAO\n");
            System.err.println("Error al insertar informacion en la tabla detalles.\n");
        } finally {
            try {
                cn.close();
            } catch (SQLException e) {
                System.out.println("Clase: VentasDAO.\n");
                System.err.println("Error al cerrar al cerrar conexion.");
            }
        }
        return r;
    }
    
    public boolean ActualizarStock(int cantidad, String codigo){
        String instruccionSQL = "UPDATE productos SET stock=? WHERE codigo =?";
        try {
            cn = con.getConnection();
            pst = cn.prepareStatement(instruccionSQL);
            pst.setInt(1, cantidad);
            pst.setString(2, codigo);
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Clase: VentasDAO\n");
            System.err.println("Error al actualizar stock.\n" + e);
            return false;
        }
    }
    
    public List ListarVentas() {

        List<Venta> ListaVenta = new ArrayList();

        String IntruccionSQl = "select * from ventas";

        try {
            cn = con.getConnection();
            pst = cn.prepareStatement(IntruccionSQl);
            rs = pst.executeQuery();

            while (rs.next()) {
                Venta vent = new Venta();

                vent.setId(rs.getInt("id"));
                vent.setCliente(rs.getString("cliente"));
                vent.setVendedor(rs.getString("vendedor"));
                vent.setTotal(rs.getDouble("total"));


                ListaVenta.add(vent);
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar informacion de las ventas.\n");
            System.err.println("Clase ProductosDAO.\n" + e.toString());
        }
        return ListaVenta;
    }
    
    
}
