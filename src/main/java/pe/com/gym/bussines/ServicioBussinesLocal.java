package pe.com.gym.bussines;


import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import pe.com.gym.entidades.Servicio;
import pe.com.gym.entidades.TarifaServicio;


@Local
public interface ServicioBussinesLocal {


	Servicio getServicio(int codSer);

	int actualizaServicio(Servicio servic);

	int getCodigoServicioNvo();

	Map<String, Object> listaServicios(String valBus, int[] limites);

	List<Servicio> listaServicios();

	int cambiaEstadoServicio(long codMod, int nvoEstado);

	int registraServicio(Servicio servic, List<TarifaServicio> tarifas);

}
