/*
 * Fecha de creacion: 04/04/2023 13:31:23
 * Version: v.0.1
 * Detalles: 
 */
package modelos;

import java.awt.event.KeyEvent;
import javax.swing.JTextField;

/**
 * @author Willy Stbn
 */
public class Eventos {
    
    //Funcion que permite introducir solo letras:
    public void textKeyPress(KeyEvent evt) {
    // declaramos una variable y le asignamos un evento
        char car = evt.getKeyChar();
        if ((car < 'a' || car > 'z') && (car < 'A' || car > 'Z')
                && (car != (char) KeyEvent.VK_BACK_SPACE) && (car != (char) KeyEvent.VK_SPACE)) {
            evt.consume();
        }
    }
    
    //Funcion que permite introducir solo numeros:
    public void numberKeyPress(KeyEvent evt) {
    // declaramos una variable y le asignamos un evento
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && (car != (char) KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
        }
    }

    public void numberDecimalKeyPress(KeyEvent evt, JTextField textField) {
    // declaramos una variable y le asignamos un evento
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && textField.getText().contains(".") && (car != (char) KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
        } else if ((car < '0' || car > '9') && (car != '.') && (car != (char) KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
        }
    }

}
