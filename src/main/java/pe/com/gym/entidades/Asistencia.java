package pe.com.gym.entidades;

import java.io.Serializable;
import java.sql.Time;

/**
 * Entidad Asistencia
 * 
 * @author Omar Yarleque
 */
public class Asistencia implements Serializable {
	private static final long serialVersionUID = 1L;

	private long codcli;

	private long tipmar;

	private java.util.Date fecmar;

	private Time hormar;

	public Asistencia() {
	}

	public long getCodcli() {
		return this.codcli;
	}

	public void setCodcli(long codcli) {
		this.codcli = codcli;
	}

	public long getTipmar() {
		return this.tipmar;
	}

	public void setTipmar(long tipmar) {
		this.tipmar = tipmar;
	}

	public java.util.Date getFecmar() {
		return this.fecmar;
	}

	public void setFecmar(java.util.Date fecmar) {
		this.fecmar = fecmar;
	}

	public Time getHormar() {
		return this.hormar;
	}

	public void setHormar(Time hormar) {
		this.hormar = hormar;
	}

}