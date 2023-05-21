/*
 * Fecha de creacion: 04/04/2023 17:41:29
 * Version: v.0.1
 * Detalles: 
 */

package reportes;

import modelos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 * @author Willy Stbn
 */

public class Grafico {
    
    public static void Graficar(String fecha){
        Connection cn;
        Conexion con = new Conexion();
        PreparedStatement pst;
        ResultSet rs;
        
        try {
            String intruccionSQL = "SELECT total FROM ventas WHERE fecha = ?";
            cn = con.getConnection();
            pst = cn.prepareStatement(intruccionSQL);
            pst.setString(1, fecha);
            rs = pst.executeQuery();
            DefaultPieDataset dateset = new DefaultPieDataset();
            
            while(rs.next()){
                dateset.setValue(rs.getString("total"), rs.getDouble("total"));
            }
            
            JFreeChart jf = ChartFactory.createPieChart("Reporte de venta:", dateset);
            ChartFrame f = new ChartFrame("Grafica de ventas.", jf);   //Titulo de la ventana.
            f.setSize(1000, 600);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        } catch (SQLException e) {
            System.out.println("Clase: Grafica");
            System.err.println("Error al consultar informacion de las ventas\n" + e);
        }
    }
}
