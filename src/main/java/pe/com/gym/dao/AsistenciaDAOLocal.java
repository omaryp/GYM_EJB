package pe.com.gym.dao;

import java.sql.Date;

import javax.ejb.Local;

import pe.com.gym.dto.ClienteDTO;
import pe.com.gym.entidades_ant.Asistencia;

@Local
public interface AsistenciaDAOLocal {

	int registraAsistencia(Asistencia asis);

	boolean getAsistencia(ClienteDTO cli, Date fechaHoy);

}
