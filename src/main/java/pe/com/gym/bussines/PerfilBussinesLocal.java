package pe.com.gym.bussines;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import pe.com.gym.entidades.Perfil;




@Local
public interface PerfilBussinesLocal {

	Perfil getPerfil(int codEmp);

	List<Perfil> getPerfiles();

	int getCodigoPerfilNvo();

	int actualizaPerfil(Perfil perfil);

	Map<String, Object> listaPerfiles(String valBus, int[] limites);

	int registraPerfil(Perfil per, int[] menus);
	
}
