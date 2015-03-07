package pe.com.gym.facade;

import javax.ejb.Remote;

import pe.com.gym.dto.ClienteDTO;
import pe.com.gym.entidades_ant.PlantillaHuella;



@Remote
public interface FacadeGYMRemote {

	ClienteDTO obtenerClienteDNI(String dni);

	int guardarHuella(ClienteDTO cliente, PlantillaHuella template);

	int marcarAsistencia(ClienteDTO cliente, PlantillaHuella verifi);
	
}
