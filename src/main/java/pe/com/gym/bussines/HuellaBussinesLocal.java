package pe.com.gym.bussines;


import javax.ejb.Local;

import pe.com.gym.dto.ClienteDTO;
import pe.com.gym.entidades.PlantillaHuella;



@Local
public interface HuellaBussinesLocal {

	int guardarHuella(ClienteDTO cliente, PlantillaHuella template);


}
