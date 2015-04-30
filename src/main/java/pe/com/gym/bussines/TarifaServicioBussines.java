package pe.com.gym.bussines;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gym.dao.TarifaServicioDAOLocal;
import pe.com.gym.entidades.Servicio;
import pe.com.gym.entidades.TarifaServicio;
import pe.com.gym.entidades.TarifaServicioPK;


/**
 * 
 * @author Omar Yarleque
 *
 */
@Stateless
public class TarifaServicioBussines implements TarifaServicioBussinesLocal {
	
	@EJB
	private TarifaServicioDAOLocal tarifa;
	
	@Override
	public TarifaServicio getTarifa(TarifaServicioPK id){
		return tarifa.getTarifa(id);
	}
	
	@Override
	public TarifaServicioPK getCodigoTarifaNva(int codser, int codmod){
		return tarifa.getCodigoTarifaNva(codser, codmod);
	}

	@Override
	public int darBajaTarifa(TarifaServicioPK id){
		return tarifa.darBajaTarifa(id);
	}

	@Override
	public List<TarifaServicio> listaTarifas(Servicio servicio){
		return tarifa.listaTarifas(servicio);
	}

	@Override
	public int registraTarifa(TarifaServicio tar){
		return tarifa.registraTarifa(tar);
	}
	
}
