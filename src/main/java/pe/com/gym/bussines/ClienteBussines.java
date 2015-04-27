package pe.com.gym.bussines;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gym.dao.ClienteDAOLocal;
import pe.com.gym.dto.ClienteDTO;
import pe.com.gym.entidades.Cliente;

/**
 * 
 * @author Omar Yarleque
 *
 */
@Stateless
public class ClienteBussines implements ClienteBussinesLocal {
	
	@EJB
	private ClienteDAOLocal cliente;
	
	@Override
	public long getCodigoClienteNvo() {
		return cliente.getCodigoClienteNvo();
	}
	
	@Override
	public int guardarCliente(Cliente cli) {
		if(cli.getTipper().equals("N"))
			return cliente.guardarClientePN(cli);
		else 
			return cliente.guardarClientePJ(cli);
	}
	
	@Override
	public Map<String, Object> listaClientes(String valBus,String tipper,int[] limites){
		if(tipper.equals("N"))
			return cliente.listaClientesPN(valBus,limites);
		else 
			return cliente.listaClientesPJ(valBus,limites);
	}
	
	@Override
	public List<ClienteDTO> listaClientesPJ(){
		return cliente.listaClientesPJ();
	}
	
	@Override
	public Cliente getCliente(String tipper,long codCli){
		if(tipper.equals("N"))
			return cliente.obtenerClientePN(codCli);
		else 
			return cliente.obtenerClientePJ(codCli);
	}
	
	@Override
	public int actualizaCliente(String tipper,Cliente cli){
		if(tipper.equals("N"))
			return cliente.actualizarClientePN(cli);
		else 
			return cliente.actualizarClientePJ(cli);
	}
	
	@Override
	public int eliminaCliente(long codCli){
		return cliente.eliminarCliente(codCli);
	}
	
	@Override
	public ClienteDTO obtenerClienteDNI(String dni){
		return cliente.obtenerClienteDNI(dni);
	}
	
	@Override
	public Map<String, Object> busquedaGeneral(String valor,long codCli,int tipBus,int[]limites){
		List<ClienteDTO> clientes = new ArrayList<ClienteDTO>();
		Map<String,Object> map = new HashMap<String, Object>();
		switch (tipBus) {
		case 1:
			cliente.listaClientesPN(valor,limites);
			break;
		case 2:
			map.put("TOTAL", 1);
			clientes.add(cliente.obtenerCliente(codCli));
			map.put("CLIENTES", clientes);
			break;
		case 3:
			map.put("TOTAL", 1);
			clientes.add(cliente.obtenerClienteDNI(valor));
			map.put("CLIENTES", clientes);
			break;
		}
		return map;
	}
}
