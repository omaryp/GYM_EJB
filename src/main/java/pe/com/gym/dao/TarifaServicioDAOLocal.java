package pe.com.gym.dao;

import java.util.List;

import javax.ejb.Local;

import pe.com.gym.entidades.Servicio;
import pe.com.gym.entidades.TarifaServicio;
import pe.com.gym.entidades.TarifaServicioPK;


@Local
public interface TarifaServicioDAOLocal {

	TarifaServicio getTarifa(TarifaServicioPK id);

	TarifaServicioPK getCodigoTarifaNva(int codser, int codmod);

	int darBajaTarifa(TarifaServicioPK id);

	int registraTarifa(TarifaServicio tarifa);

	List<TarifaServicio> listaTarifas(Servicio servicio);

}
