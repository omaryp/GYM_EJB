/**
 * 
 */
package pe.com.gym.util;

/**
 * @author usuario
 *
 */
public enum Estado {
	ACTIVO(0),
	DESACTIVADO(1);
	private final int estado;
	
	Estado(int estado){
		this.estado = estado;
	}
	
	public int getValue(){
		return this.estado;
	}
}
