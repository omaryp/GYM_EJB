package pe.com.gym.bussines;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gym.dao.MenuDAOLocal;
import pe.com.gym.dao.PerfilDAOLocal;
import pe.com.gym.entidades.Perfil;


/**
 * 
 * @author Omar Yarleque
 *
 */
@Stateless
public class PerfilBussines implements PerfilBussinesLocal {
	
	@EJB
	private PerfilDAOLocal perfil;
	@EJB
	private MenuDAOLocal menuDAO;
	
	@Override
	public Perfil getPerfil(int codEmp){
		return perfil.getPerfil(codEmp);
	}
	
	@Override
	public List<Perfil> getPerfiles(){
		return perfil.getPerfiles();
	}
	
	@Override
	public int getCodigoPerfilNvo(){
		return perfil.getCodigoPerfilNvo();
	}

	@Override
	public int registraPerfil(Perfil per,int [] menus){
		int res = perfil.registraPerfil(per);
		if(res == 0){
			for (int menu : menus) {
				menuDAO.asociarPerfil(per, menu);
			}
		}
		return res;
	}

	@Override
	public int actualizaPerfil(Perfil per){
		return perfil.actualizaPerfil(per);
	}
	
	@Override
	public Map<String, Object> listaPerfiles(String valBus, int[] limites){
		return perfil.listaPerfiles(valBus, limites);
	}

}
