package pe.com.gym.facade;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gym.bussines.AsistenciaBussinesLocal;
import pe.com.gym.bussines.ClienteBussinesLocal;
import pe.com.gym.bussines.HuellaBussinesLocal;
import pe.com.gym.dto.ClienteDTO;
import pe.com.gym.entidades_ant.Cliente;
import pe.com.gym.entidades_ant.PlantillaHuella;


@Stateless
public class FacadeGYM implements FacadeGYMLocal,FacadeGYMRemote {	
	
	@EJB
	private ClienteBussinesLocal cliente;
	
	@EJB
	private HuellaBussinesLocal huella;
	
	@EJB
	private AsistenciaBussinesLocal asistencia;

	@Override
	public int guardarCliente(Cliente cli) {
		return cliente.guardarCliente(cli);
	}
	
	@Override
	public long getCodigoClienteNvo() {
		return cliente.getCodigoClienteNvo();
	}
	
	@Override
	public Map<String, Object> listaClientes(String valBus,String tipper, int[] limites){
		return cliente.listaClientes(valBus,tipper, limites);
	}
	
	@Override
	public List<ClienteDTO> listaClientesPJ(){
		return cliente.listaClientesPJ();
	}
	
	@Override
	public Cliente getCliente(String tipPer, long codCli){
		return cliente.getCliente(tipPer, codCli);
	}
	
	@Override
	public int actualizaCliente(String tipper,Cliente cli){
		return cliente.actualizaCliente(tipper, cli);
	}
	
	@Override
	public int eliminaCliente(long codCli){
		return cliente.eliminaCliente(codCli);
	}
	
	@Override
	public ClienteDTO obtenerClienteDNI(String dni){
		return cliente.obtenerClienteDNI(dni);
	}
	
	//Huellas
	@Override
	public int guardarHuella(ClienteDTO cliente,PlantillaHuella template) {
		return huella.guardarHuella(cliente,template);
	}
	
	//Asistencia
	@Override
	public int marcarAsistencia(ClienteDTO cliente, PlantillaHuella verifi){
		return asistencia.marcarAsistencia(cliente, verifi);
	}
}
