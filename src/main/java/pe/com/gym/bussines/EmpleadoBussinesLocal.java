package pe.com.gym.bussines;


import java.util.Map;

import javax.ejb.Local;

import pe.com.gym.entidades.Empleado;



@Local
public interface EmpleadoBussinesLocal {

	Empleado getEmpleado(int codigoEmpleado);

	Long getCodigoEmpleadoNvo();

	int actualizarEmpleado(Empleado emp);

	int darBajaEmpleado(long codEmp);

	Map<String, Object> listaEmpleados(String valBus, int[] limites);

	int guardarEmpleado(Empleado emp, int codPer);	
}
