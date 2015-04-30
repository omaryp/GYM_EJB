package pe.com.gym.dao;

import java.util.List;

import javax.ejb.Local;

import pe.com.gym.entidades.Menu;
import pe.com.gym.entidades.Perfil;


@Local
public interface MenuDAOLocal {

	List<Menu> getMenus(int codEmp);

	List<Menu> getMenus();

	int asociarPerfil(Perfil per, int menu);

}
