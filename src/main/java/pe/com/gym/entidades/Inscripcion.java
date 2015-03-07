package pe.com.gym.entidades;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Omar Yarleque
 */
public class Inscripcion implements Serializable {
	private static final long serialVersionUID = 1L;

	private long codcli;

	private long codmod;

	private long correl;

	private int codser;

	private int estmod;

	private Date fecmod;

	private Date fecreg;

	private String nomcli;

	private String nommod;

	private String nomser;

	private String usumod;

	private String usureg;

	public Inscripcion() {
	}

	public long getCodcli() {
		return this.codcli;
	}

	public void setCodcli(long codcli) {
		this.codcli = codcli;
	}

	public long getCodmod() {
		return this.codmod;
	}

	public void setCodmod(long codmod) {
		this.codmod = codmod;
	}

	public long getCorrel() {
		return this.correl;
	}

	public void setCorrel(long correl) {
		this.correl = correl;
	}

	public int getCodser() {
		return this.codser;
	}

	public void setCodser(int codser) {
		this.codser = codser;
	}

	public int getEstmod() {
		return this.estmod;
	}

	public void setEstmod(int estmod) {
		this.estmod = estmod;
	}

	public Date getFecmod() {
		return this.fecmod;
	}

	public void setFecmod(Date fecmod) {
		this.fecmod = fecmod;
	}

	public Date getFecreg() {
		return this.fecreg;
	}

	public void setFecreg(Date fecreg) {
		this.fecreg = fecreg;
	}

	public String getNomcli() {
		return this.nomcli;
	}

	public void setNomcli(String nomcli) {
		this.nomcli = nomcli;
	}

	public String getNommod() {
		return this.nommod;
	}

	public void setNommod(String nommod) {
		this.nommod = nommod;
	}

	public String getNomser() {
		return this.nomser;
	}

	public void setNomser(String nomser) {
		this.nomser = nomser;
	}

	public String getUsumod() {
		return this.usumod;
	}

	public void setUsumod(String usumod) {
		this.usumod = usumod;
	}

	public String getUsureg() {
		return this.usureg;
	}

	public void setUsureg(String usureg) {
		this.usureg = usureg;
	}

}