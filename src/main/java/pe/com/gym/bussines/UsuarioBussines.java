package pe.com.gym.bussines;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gym.dao.UsuarioDAOLocal;
import pe.com.gym.entidades.Usuario;


/**
 * 
 * @author Omar Yarleque
 *
 */
@Stateless
public class UsuarioBussines implements UsuarioBussinesLocal {

	@EJB
	UsuarioDAOLocal usuario;
	
	@Override
	public int registraUsuario(Usuario usu){
		return usuario.registraUsuario(usu);
	}
	
	@Override
	public  int actualizaUsuario(Usuario usu){
		return usuario.actualizaUsuario(usu);
	}

	@Override	
	public  boolean validaUsuario(String usu, String pass){
		return usuario.validaUsuario(usu, pass);
	}

	@Override
	public Usuario getUsuario(int codemp){
		return usuario.getUsuario(codemp);
	}

	@Override
	public int eliminaUsuario(int codEmp){
		return usuario.eliminaUsuario(codEmp);
	}
	
	@Override
	public Usuario getUsuario(String user){
		return usuario.getUsuario(user);
	}
}
