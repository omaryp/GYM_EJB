package pe.com.gym.dao;

import java.util.List;

import javax.ejb.Local;

import pe.com.gym.entidades.Menu;


@Local
public interface MenuDAOLocal {

	List<Menu> getMenus(int codEmp);


}
