/*
 * Fecha de creacion: 01/04/2023 10:35:09
 * Version: v.0.1
 * Detalles: 
 */
package modelos;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JComboBox;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Willy Stbn
 */
public class ProductosDAO {

    Connection cn;
    Conexion con = new Conexion();
    PreparedStatement pst;
    ResultSet rs;

    public boolean RegistrarProducto(Productos prod) {
        String intruccionSQL = "insert into productos (codigo, nombre, proveedor, stock, precio) values (?,?,?,?,?)";
        try {
            cn = con.getConnection();
            pst = cn.prepareStatement(intruccionSQL);

            pst.setString(1, prod.getCodigo());
            pst.setString(2, prod.getDescripcion());
            pst.setString(3, prod.getProveedor());
            pst.setInt(4, prod.getStock());
            pst.setDouble(5, prod.getPrecio());

            pst.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar valores.\n");
            System.err.println("Clase: ProductosDAO.\n" + e);
            return false;
        }
    }

    public void ConsultarProvedor(JComboBox provedor) {
        String intruccionSQL = "select nombre from proveedor";

        try {
            cn = con.getConnection();
            pst = cn.prepareStatement(intruccionSQL);
            rs = pst.executeQuery();

            while (rs.next()) {
                provedor.addItem(rs.getString("nombre"));
            }

        } catch (SQLException e) {
            System.err.println("¡Error al consultar lista de provedores!.\n" + e);
        }
    }

    public List ListarProductos() {

        List<Productos> ListarProd = new ArrayList();

        String IntruccionSQl = "select * from productos";

        try {
            cn = con.getConnection();
            pst = cn.prepareStatement(IntruccionSQl);
            rs = pst.executeQuery();

            while (rs.next()) {
                Productos prod = new Productos();

                prod.setId(rs.getInt("id"));
                prod.setCodigo(rs.getString("codigo"));
                prod.setDescripcion(rs.getString("nombre"));
                prod.setProveedor(rs.getString("proveedor"));
                prod.setStock(rs.getInt("stock"));
                prod.setPrecio(rs.getDouble("precio"));

                ListarProd.add(prod);
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar informacion de los productos.\n");
            System.err.println("Clase ProductosDAO.\n" + e.toString());
        }
        return ListarProd;
    }

    public boolean EliminarProducto(int id) {
        String instruccionSQL = "delete from productos where id = ?";
        try {
            pst = cn.prepareStatement(instruccionSQL);
            pst.setInt(1, id);
            pst.execute();

            return true;

        } catch (SQLException e) {
            System.err.println("Error al eliminar el cliente\n");
            System.err.println("Clase: ClienteDAO.\n" + e.toString());
            return false;
        } finally {
            try {
                cn.close();
            } catch (SQLException er) {
                System.err.println("¡Error al cerrar la conexion con la base de datos!.\n" + er);
            }
        }
    }

    public boolean ModificarProducto(Productos prod) {
        String intruccionSQL = "update productos set codigo=?, nombre=?, proveedor=?, stock=?, precio=? where id=?";

        try {
            pst = cn.prepareStatement(intruccionSQL);
            pst.setString(1, prod.getCodigo());
            pst.setString(2, prod.getDescripcion());
            pst.setString(3, prod.getProveedor());
            pst.setInt(4, prod.getStock());
            pst.setDouble(5, prod.getPrecio());
            pst.setInt(6, prod.getId());
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al modificar informacion del producto.\n" + e);
            return false;
        } finally {
            try {
                cn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexion con la base datos.");
            }
        }
    }
    
    public Productos BuscarProductos(String codigo){
        Productos producto = new Productos();
        String intruccionSQL = "select * from productos where codigo=?";
        try {
            cn = con.getConnection();
            pst = cn.prepareStatement(intruccionSQL);
            pst.setString(1, codigo);
            rs = pst.executeQuery();
            
            if(rs.next()){
                producto.setDescripcion(rs.getString("nombre"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));
                producto.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la busqueda del producto.\n");
            System.err.println("Clase: ProductosDAO.\n" + e);
        }
        return producto;
    }
    
    /* ************************************** Informacion de la empresa: ***************************************************/
    public Config BuscarDatos(){
        Config conf = new Config();
        String intruccionSQL = "select * from config";
        try {
            cn = con.getConnection();
            pst = cn.prepareStatement(intruccionSQL);
            rs = pst.executeQuery();
            
            if(rs.next()){
                conf.setId(rs.getInt("id"));
                conf.setRucc(rs.getInt("ruc"));
                conf.setNombre(rs.getString("nombre"));
                conf.setTelefono(rs.getInt("telefono"));
                conf.setDireccion(rs.getString("direccion"));
                conf.setRazon(rs.getString("razon"));
            }
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la busqueda de informacion de la empresa.\n");
            System.err.println("Clase: ProductosDAO.\n" + e);
        }
        return conf;
    }
    
    public boolean ModificarInformacionEmpresa(Config conf) {
        String intruccionSQL = "update config set ruc=?, nombre=?, telefono=?, direccion=?, razon=? where id=?";

        try {
            pst = cn.prepareStatement(intruccionSQL);
            pst.setInt(1, conf.getRucc());
            pst.setString(2, conf.getNombre());
            pst.setInt(3, conf.getTelefono());
            pst.setString(4, conf.getDireccion());
            pst.setString(5, conf.getRazon());
            pst.setInt(6, conf.getId());
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al modificar informacion de la empresa.\n" + e);
            return false;
        } finally {
            try {
                cn.close();
            } catch (SQLException e) {
                System.out.println("Clase: ProductosDAO - Informacion de la empresa.\n");
                System.err.println("Error al cerrar conexion con la base datos.");
            }
        }
    }
}
