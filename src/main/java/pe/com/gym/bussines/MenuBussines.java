package pe.com.gym.bussines;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gym.dao.MenuDAOLocal;
import pe.com.gym.entidades.Menu;

/**
 * 
 * @author Omar Yarleque
 *
 */
@Stateless
public class MenuBussines implements MenuBussinesLocal {
	
	@EJB
	private MenuDAOLocal menu;
	
	@Override
	public List<Menu> getMenus(int codEmp){
		return menu.getMenus(codEmp);
	}
	
	@Override
	public List<Menu> getMenus(){
		return menu.getMenus();
	}
	
}
