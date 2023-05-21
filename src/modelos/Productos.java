/*
 * Fecha de creacion: 01/04/2023 10:35:00
 * Version: v.0.1
 * Detalles: 
 */

package modelos;

/**
 * @author Willy Stbn
 */

public class Productos {    
    
    private int id;
    private String codigo;
    private String descripcion;
    private String proveedor;
    private int stock;
    private double precio;

    public Productos() {
        
    }

    public Productos(int id, String codigo, String descripcion, String proveedor, int stock, double precio) {
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.proveedor = proveedor;
        this.stock = stock;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    
    
}
