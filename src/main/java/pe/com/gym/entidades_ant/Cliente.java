package pe.com.gym.entidades_ant;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;


/**
 * 
 * @author Omar Yarleque
 * 
 */

public class Cliente implements Serializable {
	
	private static final long serialVersionUID = -7137149198230926233L;
	private long codigoCliente;
	private String apellidoCliente;
	private long clienteEmpresa;
	private String direccionCliente;
	private String dni;
	private Time horaFinRutina;
	private Time horaInicioRutina;
	private String nombreCliente;
	private String repLegal;
	private String dniRepLegal;
	private String razonSocial;
	private String rucCliente;
	private String rutaFoto;
	private String telefonoCliente;
	private String tipoPersona;
	private String usuarioModificacion;
	private String usuarioRegistro;
	private Date fechaRegistro;
	private Date fechaModificacion;

	public Cliente() {
	}

	public long getCodigoCliente() {
		return this.codigoCliente;
	}

	public void setCodigoCliente(Long codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getApellidoCliente() {
		return this.apellidoCliente;
	}

	public void setApellidoCliente(String apellidoCliente) {
		this.apellidoCliente = apellidoCliente;
	}

	public Long getClienteEmpresa() {
		return this.clienteEmpresa;
	}

	public void setClienteEmpresa(Long clienteEmpresa) {
		this.clienteEmpresa = clienteEmpresa;
	}

	public String getDireccionCliente() {
		return this.direccionCliente;
	}

	public void setDireccionCliente(String direccionCliente) {
		this.direccionCliente = direccionCliente;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Time getHoraFinRutina() {
		return this.horaFinRutina;
	}

	public void setHoraFinRutina(Time horaFinRutina) {
		this.horaFinRutina = horaFinRutina;
	}

	public Time getHoraInicioRutina() {
		return this.horaInicioRutina;
	}

	public void setHoraInicioRutina(Time horaInicioRutina) {
		this.horaInicioRutina = horaInicioRutina;
	}

	public String getNombreCliente() {
		return this.nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getRazonSocial() {
		return this.razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getRucCliente() {
		return this.rucCliente;
	}

	public void setRucCliente(String rucCliente) {
		this.rucCliente = rucCliente;
	}

	public String getRutaFoto() {
		return this.rutaFoto;
	}

	public void setRutaFoto(String rutaFoto) {
		this.rutaFoto = rutaFoto;
	}

	public String getTelefonoCliente() {
		return this.telefonoCliente;
	}

	public void setTelefonoCliente(String telefonoCliente) {
		this.telefonoCliente = telefonoCliente;
	}

	public String getTipoPersona() {
		return this.tipoPersona;
	}

	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	public String getUsuarioModificacion() {
		return this.usuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	public String getUsuarioRegistro() {
		return this.usuarioRegistro;
	}

	public void setUsuarioRegistro(String usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

	public String getRepLegal() {
		return repLegal;
	}

	public void setRepLegal(String repLegal) {
		this.repLegal = repLegal;
	}

	public String getDniRepLegal() {
		return dniRepLegal;
	}

	public void setDniRepLegal(String dniRepLegal) {
		this.dniRepLegal = dniRepLegal;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	
}