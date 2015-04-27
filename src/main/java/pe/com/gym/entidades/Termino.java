package pe.com.gym.entidades;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the tb_termino database table.
 * 
 */
public class Termino implements Serializable {
	private static final long serialVersionUID = 1L;

	private long codter;

	private String descrip;

	private Date fecmod;

	private Date fecreg;

	private String nomter;

	private String usumod;

	private String usureg;

	public Termino() {
	}

	public long getCodter() {
		return this.codter;
	}

	public void setCodter(long codter) {
		this.codter = codter;
	}

	public String getDescrip() {
		return this.descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
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

	public String getNomter() {
		return this.nomter;
	}

	public void setNomter(String nomter) {
		this.nomter = nomter;
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