package pe.com.gym.facade;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import pe.com.gym.dto.ClienteDTO;
import pe.com.gym.entidades.Cliente;
import pe.com.gym.entidades.ModalidadPago;
import pe.com.gym.entidades.Servicio;


@Local
public interface FacadeGYMLocal {

	int guardarCliente(Cliente cli);

	long getCodigoClienteNvo();
	
	Map<String, Object> listaClientes(String valBus, String tipper,
			int[] limites);

	List<ClienteDTO> listaClientesPJ();

	Cliente getCliente(String tipPer, long codCli);

	int actualizaCliente(String tipPer, Cliente cli);

	int eliminaCliente(long codCli);

	long getCodigoModalidadNva();

	int actualizaModalidad(ModalidadPago modal);

	int registraModalidad(ModalidadPago modal);

	Map<String, Object> listaModalidades(String valBus, int[] limites);

	List<ModalidadPago> listaModalidades();

	ModalidadPago getModalidad(long codMod);

	Servicio getServicio(int codSer);

	int registraServicio(Servicio servic);

	int actualizaServicio(Servicio servic);

	int getCodigoServicioNvo();

	Map<String, Object> listaServicios(String valBus, int[] limites);

	List<Servicio> listaServicios();

	int cambiaEstadoModalidad(long codMod, int nvoEstado);

	int cambiaEstadoServicio(long codMod, int nvoEstado);
	
}
