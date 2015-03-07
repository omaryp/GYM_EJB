package pe.com.gym.bussines;


import javax.ejb.Local;

import pe.com.gym.dto.ClienteDTO;
import pe.com.gym.entidades_ant.PlantillaHuella;



@Local
public interface AsistenciaBussinesLocal {

	int marcarAsistencia(ClienteDTO cliente, PlantillaHuella verificador);

}
