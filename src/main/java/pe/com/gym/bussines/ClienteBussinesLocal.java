package pe.com.gym.bussines;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import pe.com.gym.dto.ClienteDTO;
import pe.com.gym.entidades.Cliente;



@Local
public interface ClienteBussinesLocal {

	long getCodigoClienteNvo();

	int guardarCliente(Cliente cli);

	Map<String, Object> listaClientes(String valBus, String tipper,
			int[] limites);

	List<ClienteDTO> listaClientesPJ();

	Cliente getCliente(String tipper, long codCli);

	int actualizaCliente(String tipper, Cliente cli);

	int eliminaCliente(long codCli);

	ClienteDTO obtenerClienteDNI(String dni);

}
