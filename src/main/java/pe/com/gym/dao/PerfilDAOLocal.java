package pe.com.gym.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import pe.com.gym.entidades.Perfil;

@Local
public interface PerfilDAOLocal {

	Perfil getPerfil(int codEmp);

	List<Perfil> getPerfiles();

	int asociarPerfil(int cod_empleado, int perfil);

	int getCodigoPerfilNvo();

	int registraPerfil(Perfil perfil);

	int actualizaPerfil(Perfil perfil);

	Map<String, Object> listaPerfiles(String valBus, int[] limites);

}
