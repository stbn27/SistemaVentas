/*
 * Fecha de creacion: 31/03/2023 23:05:39
 * Version: v.0.1
 * Detalles: 
 */

package modelos;

/**
 * @author Willy Stbn
 */

public class Provedor {
    
    private int id;
    private int rucc;
    private String nombre;
    private int telefono;
    private String direccion;
    private String razonSocial;
    
    //Constructor vacio:
    public Provedor() {
        
    }
    
    //Constructor con parametros:
    public Provedor(int id, int rucc, String nombre, int telefono, String direccion, String razonSocial) {
        this.id = id;
        this.rucc = rucc;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.razonSocial = razonSocial;
    }
    
    //Geters and setters:

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRucc() {
        return rucc;
    }

    public void setRucc(int rucc) {
        this.rucc = rucc;
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

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
    
    
    
}
