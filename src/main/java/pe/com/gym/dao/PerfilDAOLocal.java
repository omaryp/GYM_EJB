package pe.com.gym.dao;

import javax.ejb.Local;

import pe.com.gym.entidades.Perfil;

@Local
public interface PerfilDAOLocal {

	Perfil getPerfil(int codEmp);


}
