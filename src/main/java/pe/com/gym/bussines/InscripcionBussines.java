package pe.com.gym.bussines;


import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gym.dao.InscripcionDAOLocal;
import pe.com.gym.entidades.Inscripcion;
import pe.com.gym.entidades.InscripcionPk;

/**
 * 
 * @author Omar Yarleque
 *
 */
@Stateless
public class InscripcionBussines implements InscripcionBussinesLocal {
	
	@EJB
	private InscripcionDAOLocal inscripcion;
	
	//private final static Logger logger = Logger.getLogger(InscripcionBussines.class.getName());
	
	@Override
	public Inscripcion getIncripcion(InscripcionPk id){
		return inscripcion.getIncripcion(id);
	}

	@Override
	public int registraInscripcion(Inscripcion ins){
		return inscripcion.registraInscripcion(ins);
	}

	@Override
	public int actualizaInscripcion(Inscripcion ins){
		return inscripcion.actualizaInscripcion(ins);
	}
	
	@Override
	public int getCorrelativoIncripcion(){
		return inscripcion.getCorrelativoIncripcion();
	}

	@Override
	public Map<String, Object> listaInscripciones(String valBus, int[] limites){
		return inscripcion.listaInscripciones(valBus, limites);
	}

	@Override
	public int cancelarInscripcion(InscripcionPk id, int nvoEstado){
		return inscripcion.cancelarInscripcion(id, nvoEstado);
	}
}
