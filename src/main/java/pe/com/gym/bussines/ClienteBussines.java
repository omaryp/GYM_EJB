package pe.com.gym.bussines;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gym.dao.ClienteDAOLocal;
import pe.com.gym.dto.ClienteDTO;
import pe.com.gym.entidades_ant.Cliente;

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
		if(cli.getTipoPersona().equals("N"))
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
}
