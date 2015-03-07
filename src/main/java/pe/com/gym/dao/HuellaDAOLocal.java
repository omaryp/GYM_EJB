package pe.com.gym.dao;

import java.util.List;

import javax.ejb.Local;

import pe.com.gym.entidades_ant.Huella;


@Local
public interface HuellaDAOLocal {

	int guardaHuella(Huella hue, Integer tamhuella);

	List<byte[]> cargarHuellas(String dni);


}
