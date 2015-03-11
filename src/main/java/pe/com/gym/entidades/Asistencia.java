package pe.com.gym.entidades;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

/**
 * Entidad Asistencia
 * 
 * @author Omar Yarleque
 */
public class Asistencia implements Serializable {
	private static final long serialVersionUID = 1L;

	private long codcli;

	private int tipmar;

	private Date fecmar;

	private Time hormar;

	public Asistencia() {
	}

	public long getCodcli() {
		return this.codcli;
	}

	public void setCodcli(long codcli) {
		this.codcli = codcli;
	}

	public int getTipmar() {
		return this.tipmar;
	}

	public void setTipmar(int tipmar) {
		this.tipmar = tipmar;
	}

	public Date getFecmar() {
		return this.fecmar;
	}

	public void setFecmar(Date fecmar) {
		this.fecmar = fecmar;
	}

	public Time getHormar() {
		return this.hormar;
	}

	public void setHormar(Time hormar) {
		this.hormar = hormar;
	}

}