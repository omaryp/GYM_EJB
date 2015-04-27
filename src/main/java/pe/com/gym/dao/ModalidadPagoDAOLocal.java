package pe.com.gym.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import pe.com.gym.entidades.ModalidadPago;

@Local
public interface ModalidadPagoDAOLocal {

	long getCodigoModalidadNva();

	int actualizaModalidad(ModalidadPago modal);

	Map<String, Object> listaModalidades(String valBus, int[] limites);

	List<ModalidadPago> listaModalidades();

	ModalidadPago getModalidad(long codMod);

	int cambiaEstadoModalidad(long codMod, int nvoEstado);

	int registraModalidad(ModalidadPago modal);

}
