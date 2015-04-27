package pe.com.gym.dao;


import javax.ejb.Local;

import pe.com.gym.entidades.Empleado;



@Local
public interface EmpleadoDAOLocal {

	Empleado getEmpleado(int codigoEmpleado);


}
