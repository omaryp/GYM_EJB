/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gym.util;

import javax.ejb.ApplicationException;

/**
 * Clase para abstraer 
 * 
 * @author Nelson Abel Barranzuela Iman.
 */

@ApplicationException(rollback = true)
public class AplicationException extends Exception {
    private static final long serialVersionUID = 8152158019835185441L;
    

    /**
     * Creates a new instance of
     * <code>AplicationException</code> without detail message.
     */
    
    
    public AplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AplicationException(Throwable cause) {
        super(cause);
    }

    
    
    public AplicationException() {
    }

    /**
     * Constructs an instance of
     * <code>AplicationException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public AplicationException(String msg) {
        super(msg);
    }
}
