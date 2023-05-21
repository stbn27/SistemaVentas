/*
 * Fecha de creacion: 04/04/2023 11:40:40
 * Version: v.0.1
 * Detalles: 
 */

package modelos;

/**
 * @author Willy Stbn
 */

public class Config {
    
    private int id;
    private int rucc;
    private String nombre;
    private int telefono;
    private String direccion;
    private String razon;

    //Constructor vacio
    public Config() {
        
    }
    
    //Constructor con parametros:

    public Config(int id, int rucc, String nombre, int telefono, String direccion, String razon) {
        this.id = id;
        this.rucc = rucc;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.razon = razon;
    }
    
    //Getter and setters:

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

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }
     
}
