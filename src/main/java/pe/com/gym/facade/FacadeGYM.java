package pe.com.gym.facade;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gym.bussines.AsistenciaBussinesLocal;
import pe.com.gym.bussines.ClienteBussinesLocal;
import pe.com.gym.bussines.HuellaBussinesLocal;
import pe.com.gym.bussines.ModalidadPagoBussinesLocal;
import pe.com.gym.bussines.ServicioBussinesLocal;
import pe.com.gym.dto.ClienteDTO;
import pe.com.gym.entidades.Cliente;
import pe.com.gym.entidades.ModalidadPago;
import pe.com.gym.entidades.PlantillaHuella;
import pe.com.gym.entidades.Servicio;


@Stateless
public class FacadeGYM implements FacadeGYMLocal,FacadeGYMRemote {	
	
	@EJB
	private ClienteBussinesLocal cliente;
	
	@EJB
	private HuellaBussinesLocal huella;
	
	@EJB
	private AsistenciaBussinesLocal asistencia;
	
	@EJB
	private ModalidadPagoBussinesLocal modalidad;
	
	@EJB
	private ServicioBussinesLocal servicio;

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
	
	//Modalidades de Pago
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
	public ModalidadPago getModalidad(long codMod){
		return modalidad.getModalidad(codMod);
	}
	
	@Override
	public int cambiaEstadoModalidad(long codMod, int nvoEstado){
		return modalidad.cambiaEstadoModalidad(codMod, nvoEstado);
	}
	
	//servicios
	@Override
	public Servicio getServicio(int codSer){
		return servicio.getServicio(codSer);
	}

	@Override
	public int registraServicio(Servicio servic){
		return servicio.registraServicio(servic);
	}

	@Override
	public int actualizaServicio(Servicio servic){
		return servicio.actualizaServicio(servic);
	}

	@Override
	public int getCodigoServicioNvo(){
		return servicio.getCodigoServicioNvo();
	}

	@Override
	public Map<String, Object> listaServicios(String valBus, int[] limites){
		return servicio.listaServicios(valBus, limites);
	}

	@Override
	public List<Servicio> listaServicios(){
		return servicio.listaServicios();
	}
	
	@Override
	public int cambiaEstadoServicio(long codMod, int nvoEstado){
		return servicio.cambiaEstadoServicio(codMod, nvoEstado);
	}
}
