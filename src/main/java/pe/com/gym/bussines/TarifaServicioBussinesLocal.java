package pe.com.gym.bussines;


import java.util.List;

import javax.ejb.Local;

import pe.com.gym.entidades.Servicio;
import pe.com.gym.entidades.TarifaServicio;
import pe.com.gym.entidades.TarifaServicioPK;



@Local
public interface TarifaServicioBussinesLocal {

	int registraTarifa(TarifaServicio tar);

	int darBajaTarifa(TarifaServicioPK id);

	TarifaServicioPK getCodigoTarifaNva(int codser, int codmod);

	TarifaServicio getTarifa(TarifaServicioPK id);

	List<TarifaServicio> listaTarifas(Servicio servicio);	
}
