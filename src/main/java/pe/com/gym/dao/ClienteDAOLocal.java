package pe.com.gym.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import pe.com.gym.dto.ClienteDTO;
import pe.com.gym.entidades.Cliente;

@Local
public interface ClienteDAOLocal {

	long getCodigoClienteNvo();

	int guardarClientePN(Cliente cli);

	int guardarClientePJ(Cliente cli);

	Map<String, Object> listaClientesPN(String valBus, int[] limites);

	Map<String, Object> listaClientesPJ(String valBus, int[] limites);
	
	List<ClienteDTO> listaClientesPJ();

	Cliente obtenerClientePJ(long codcli);

	Cliente obtenerClientePN(long codcli);

	int actualizarClientePN(Cliente cli);

	int actualizarClientePJ(Cliente cli);

	int eliminarCliente(long codCli);

	ClienteDTO obtenerClienteDNI(String dni);

	ClienteDTO obtenerCliente(long codcli);

}
