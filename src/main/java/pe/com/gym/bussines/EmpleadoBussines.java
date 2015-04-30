package pe.com.gym.bussines;

import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gym.dao.EmpleadoDAOLocal;
import pe.com.gym.dao.PerfilDAOLocal;
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
	@EJB
	private PerfilDAOLocal perfil;
	
	@Override
	public Empleado getEmpleado(int codigoEmpleado){
		return empleado.getEmpleado(codigoEmpleado);
	}
	
	@Override
	public Long getCodigoEmpleadoNvo(){
		return empleado.getCodigoEmpleadoNvo();
	}
	
	@Override
	public int actualizarEmpleado(Empleado emp){
		return empleado.actualizarEmpleado(emp);
	}

	@Override
	public int darBajaEmpleado(long codEmp){
		return empleado.darBajaEmpleado(codEmp);
	}

	@Override
	public Map<String, Object> listaEmpleados(String valBus, int[] limites){
		return empleado.listaEmpleados(valBus, limites);
	}
	
	@Override
	public int guardarEmpleado(Empleado emp,int codPer){
		empleado.guardarEmpleado(emp);
		return perfil.asociarPerfil(emp.getCodemp(),codPer);
	}
}
