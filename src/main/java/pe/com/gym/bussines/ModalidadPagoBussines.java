package pe.com.gym.bussines;


import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gym.dao.ModalidadPagoDAOLocal;
import pe.com.gym.entidades.ModalidadPago;

/**
 * 
 * @author Omar Yarleque
 *
 */
@Stateless
public class ModalidadPagoBussines implements ModalidadPagoBussinesLocal {
	
	@EJB
	private ModalidadPagoDAOLocal modalidad;
		
	@Override
	public ModalidadPago getModalidad(long codMod){
		return modalidad.getModalidad(codMod); 
	}
	@Override
	public long getCodigoModalidadNva(){
		return modalidad.getCodigoModalidadNva();
	}
	
	@Override
	public int actualizaModalidad(ModalidadPago modal){
		return modalidad.actualizaModalidad(modal);
	}

	@Override
	public int registraModalidad(ModalidadPago modal){
		return modalidad.registraModalidad(modal);
	}
	
	@Override
	public Map<String, Object> listaModalidades(String valBus,int[] limites){
		return modalidad.listaModalidades(valBus, limites);
	}
	
	@Override
	public List<ModalidadPago> listaModalidades(){
		return modalidad.listaModalidades();
	}
	
	@Override
	public int cambiaEstadoModalidad(long codMod, int nvoEstado){
		return modalidad.cambiaEstadoModalidad(codMod, nvoEstado);
	}
}
