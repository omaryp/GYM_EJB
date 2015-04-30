package pe.com.gym.bussines;


import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gym.dao.ServicioDAOLocal;
import pe.com.gym.dao.TarifaServicioDAOLocal;
import pe.com.gym.entidades.Servicio;
import pe.com.gym.entidades.TarifaServicio;

/**
 * 
 * @author Omar Yarleque
 *
 */
@Stateless
public class ServicioBussines implements ServicioBussinesLocal {
	
	@EJB
	private ServicioDAOLocal servicio;
	
	@EJB
	private TarifaServicioDAOLocal tarifa;
		
	@Override
	public Servicio getServicio(int codSer){
		return servicio.getServicio(codSer);
	}

	@Override
	public int registraServicio(Servicio servic,List<TarifaServicio> tarifas){
		int res = servicio.registraServicio(servic);
		if(res == 0){
			for (TarifaServicio tarifaServicio : tarifas) {
				tarifa.registraTarifa(tarifaServicio);
			}
		}
		return res;
	}

	@Override
	public int actualizaServicio(Servicio servic){
		return servicio.actualizaServicio(servic);
	}

	@Override
	public int getCodigoServicioNvo(){
		return servicio.getCodigoServicioNvo();
	}

	@Override
	public Map<String, Object> listaServicios(String valBus, int[] limites){
		return servicio.listaServicios(valBus, limites);
	}

	@Override
	public List<Servicio> listaServicios(){
		return servicio.listaServicios();
	}
	
	@Override
	public int cambiaEstadoServicio(long codMod, int nvoEstado){
		return servicio.cambiaEstadoServicio(codMod, nvoEstado);
	}
}
