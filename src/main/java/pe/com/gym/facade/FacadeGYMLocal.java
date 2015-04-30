package pe.com.gym.facade;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import pe.com.gym.dto.ClienteDTO;
import pe.com.gym.entidades.Cliente;
import pe.com.gym.entidades.Empleado;
import pe.com.gym.entidades.Inscripcion;
import pe.com.gym.entidades.InscripcionPk;
import pe.com.gym.entidades.Menu;
import pe.com.gym.entidades.ModalidadPago;
import pe.com.gym.entidades.Perfil;
import pe.com.gym.entidades.Servicio;
import pe.com.gym.entidades.TarifaServicio;
import pe.com.gym.entidades.TarifaServicioPK;
import pe.com.gym.entidades.Usuario;


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

	int actualizaServicio(Servicio servic);

	int getCodigoServicioNvo();

	Map<String, Object> listaServicios(String valBus, int[] limites);

	List<Servicio> listaServicios();

	int cambiaEstadoModalidad(long codMod, int nvoEstado);

	int cambiaEstadoServicio(long codMod, int nvoEstado);

	Inscripcion getIncripcion(InscripcionPk id);

	int registraInscripcion(Inscripcion ins);

	int actualizaInscripcion(Inscripcion ins);

	int getCorrelativoIncripcion();

	Map<String, Object> listaInscripciones(String valBus, int[] limites);

	int cancelarInscripcion(InscripcionPk id, int nvoEstado);

	int registraUsuario(Usuario usu);

	int actualizaUsuario(Usuario usu);

	boolean validaUsuario(String usu, String pass);

	Usuario getUsuario(int codemp);

	int eliminaUsuario(int codEmp);

	List<Menu> getMenus(int codEmp);

	Perfil getPerfil(int codEmp);

	Empleado getEmpleado(int codigoEmpleado);

	Usuario getUsuario(String user);

	Map<String, Object> busquedaGeneral(String valor, long codCli, int tipBus,
			int[] limites);

	Long getCodigoEmpleadoNvo();

	int actualizarEmpleado(Empleado emp);

	int darBajaEmpleado(long codEmp);

	Map<String, Object> listaEmpleados(String valBus, int[] limites);

	List<Perfil> getPerfiles();

	int guardarEmpleado(Empleado emp, int perfil);

	int getCodigoPerfilNvo();

	int actualizaPerfil(Perfil per);

	Map<String, Object> listaPerfiles(String valBus, int[] limites);

	List<Menu> getMenus();

	int registraPerfil(Perfil per, int[] menus);

	TarifaServicio getTarifa(TarifaServicioPK id);

	TarifaServicioPK getCodigoTarifaNva(int codser, int codmod);

	int darBajaTarifa(TarifaServicioPK id);

	int registraTarifa(TarifaServicio tar);

	List<TarifaServicio> listaTarifas(Servicio servicio);

	int registraServicio(Servicio servic, List<TarifaServicio> tarifas);
	
}
