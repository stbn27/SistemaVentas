/*
 * Fecha de creacion: 02/04/2023 16:49:02
 * Version: v.0.1
 * Detalles: 
 */

package modelos;

/**
 * @author Willy Stbn
 */

public class Venta {
    
    private int id;
    private String cliente;
    private String vendedor;
    private double total;
    private String fecha;
    
    //Constructor vacio;

    public Venta() {
        
    }
    
    //Constructor con parametros:

    public Venta(int id, String cliente, String vendedor, double total, String fecha) {
        this.id = id;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.total = total;
        this.fecha = fecha;
    }
    
    //Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
}
