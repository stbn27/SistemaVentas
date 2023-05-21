/*
 * Fecha de creacion: 30/03/2023 22:42:42
 * Version: v.0.1
 * Detalles: 
 */

package modelos;

/**
 * @author Willy Stbn
 */

public class Clientes {
    private int id;
    private int dni;
    private String nombre;
    private int telefono;
    private String direccion;
    private String razon_Social;
    
    //Constructor vacio
    public Clientes() {
        
    }
    
    //Constructor con argumentos:

    public Clientes(int id, int dni, String nombre, int telefono, String direccion, String razon_Social) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.razon_Social = razon_Social;
    }
    
    //Getter and Setter:
    public int getId() {    
        return id;
    }

    public void setId(int id) {    
        this.id = id;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRazon_Social() {
        return razon_Social;
    }

    public void setRazon_Social(String razon_Social) {
        this.razon_Social = razon_Social;
    }
    
    
    
    
    
}
