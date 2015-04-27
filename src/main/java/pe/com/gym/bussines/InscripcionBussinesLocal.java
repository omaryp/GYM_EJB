package pe.com.gym.bussines;


import java.util.Map;

import javax.ejb.Local;

import pe.com.gym.entidades.Inscripcion;
import pe.com.gym.entidades.InscripcionPk;


@Local
public interface InscripcionBussinesLocal {

	Inscripcion getIncripcion(InscripcionPk id);

	int registraInscripcion(Inscripcion ins);

	int actualizaInscripcion(Inscripcion ins);

	int getCorrelativoIncripcion();

	Map<String, Object> listaInscripciones(String valBus, int[] limites);

	int cancelarInscripcion(InscripcionPk id, int nvoEstado);
	
}
