package pe.com.gym.dao;

import java.util.List;

import javax.ejb.Local;

import pe.com.gym.entidades.Termino;

@Local
public interface TerminoDAOLocal {

	List<Termino> listaTerminos();


}
