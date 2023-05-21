/*
 * Fecha de creacion: 30/03/2023 21:25:34
 * Version: v.0.1
 * Detalles: 
 */

package modelos;

/**
 * @author Willy Stbn
 */

public class login {
    
    //Variables de la tabla usuarios de la base datos.;
    private int id;
    private String nombre;
    private String correo;
    private String pass;
    private String rol;
    
    //Constructor vacio:
    public login() {
        
    }

    //Constructor con parametros:
    public login(int id, String nombre, String correo, String pass, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.pass = pass;
        this.rol = rol;
    }
    
    
    //Getter y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}
