package pe.com.gym.bussines;

import javax.ejb.Local;

import pe.com.gym.entidades.Perfil;




@Local
public interface PerfilBussinesLocal {

	Perfil getPerfil(int codEmp);

	
}
