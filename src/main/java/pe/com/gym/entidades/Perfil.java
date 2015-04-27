package pe.com.gym.entidades;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the tb_perfil database table.
 * 
 */
public class Perfil implements Serializable {
	private static final long serialVersionUID = 1L;

	private int codper;

	private String desper;

	private int estper;
	
	private Date fecmod;

	private Date fecreg;

	private String nomper;

	private String usumod;

	private String usureg;

	public Perfil() {
	}

	public int getCodper() {
		return codper;
	}

	public void setCodper(int codper) {
		this.codper = codper;
	}

	public int getEstper() {
		return estper;
	}

	public void setEstper(int estper) {
		this.estper = estper;
	}

	public String getDesper() {
		return this.desper;
	}

	public void setDesper(String desper) {
		this.desper = desper;
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

	public String getNomper() {
		return this.nomper;
	}

	public void setNomper(String nomper) {
		this.nomper = nomper;
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