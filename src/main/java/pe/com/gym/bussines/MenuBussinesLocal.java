package pe.com.gym.bussines;


import java.util.List;

import javax.ejb.Local;

import pe.com.gym.entidades.Menu;



@Local
public interface MenuBussinesLocal {

	List<Menu> getMenus(int codEmp);

	List<Menu> getMenus();

}
