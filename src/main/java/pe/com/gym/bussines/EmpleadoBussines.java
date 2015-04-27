package pe.com.gym.bussines;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gym.dao.EmpleadoDAOLocal;
import pe.com.gym.entidades.Empleado;


/**
 * 
 * @author Omar Yarleque
 *
 */
@Stateless
public class EmpleadoBussines implements EmpleadoBussinesLocal {
	
	@EJB
	private EmpleadoDAOLocal empleado;
	
	@Override
	public Empleado getEmpleado(int codigoEmpleado){
		return empleado.getEmpleado(codigoEmpleado);
	}
		
}
