package pe.com.gym.entidades;

import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the tb_menu database table.
 * 
 */
public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;

	private int codmen;

	private String desmen;
	
	private Date fecmod;

	private Date fecreg;

	private String nommen;

	private String rutmen;

	private String usumod;

	private String usureg;
	
	private int estmen;

	public Menu() {
	}

	public int getCodmen() {
		return codmen;
	}

	public void setCodmen(int codmen) {
		this.codmen = codmen;
	}

	public String getDesmen() {
		return this.desmen;
	}

	public void setDesmen(String desmen) {
		this.desmen = desmen;
	}

	public int getEstmen() {
		return estmen;
	}

	public void setEstmen(int estmen) {
		this.estmen = estmen;
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

	public String getNommen() {
		return this.nommen;
	}

	public void setNommen(String nommen) {
		this.nommen = nommen;
	}

	public String getRutmen() {
		return this.rutmen;
	}

	public void setRutmen(String rutmen) {
		this.rutmen = rutmen;
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