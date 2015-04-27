package pe.com.gym.bussines;


import javax.ejb.Local;

import pe.com.gym.entidades.Usuario;



@Local
public interface UsuarioBussinesLocal {

	int registraUsuario(Usuario usu);

	int actualizaUsuario(Usuario usu);

	boolean validaUsuario(String usu, String pass);

	Usuario getUsuario(int codemp);

	int eliminaUsuario(int codEmp);

	Usuario getUsuario(String user);

}
