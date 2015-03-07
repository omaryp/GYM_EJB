package pe.com.gym.facade;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import pe.com.gym.dto.ClienteDTO;
import pe.com.gym.entidades_ant.Cliente;


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
	
}
