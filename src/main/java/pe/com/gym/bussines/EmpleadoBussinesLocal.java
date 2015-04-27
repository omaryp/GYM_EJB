package pe.com.gym.bussines;


import javax.ejb.Local;

import pe.com.gym.entidades.Empleado;



@Local
public interface EmpleadoBussinesLocal {

	Empleado getEmpleado(int codigoEmpleado);

	
}
