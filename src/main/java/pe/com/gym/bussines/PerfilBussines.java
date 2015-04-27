package pe.com.gym.bussines;

import javax.ejb.EJB;
import javax.ejb.Stateless;

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
	
	@Override
	public Perfil getPerfil(int codEmp){
		return perfil.getPerfil(codEmp);
	}
}
