package pe.com.gym.dao;

import javax.ejb.Local;

import pe.com.gym.entidades.Usuario;

@Local
public interface UsuarioDAOLocal {

	int registraUsuario(Usuario usu);

	int actualizaUsuario(Usuario usu);

	boolean validaUsuario(String usu, String pass);

	Usuario getUsuario(int codemp);

	int eliminaUsuario(int codEmp);

	Usuario getUsuario(String usuario);
	
}
