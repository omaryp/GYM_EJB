package pe.com.gym.entidades;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the tb_usuario database table.
 * 
 */

public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	private UsuarioPK id;

	private Date fecreg;

	private String pasemp;

	private String pasenc;

	private String usuemp;

	private String usureg;
	
	private int estusr;

	public Usuario() {
	}

	public UsuarioPK getId() {
		return this.id;
	}

	public void setId(UsuarioPK id) {
		this.id = id;
	}

	public Date getFecreg() {
		return this.fecreg;
	}

	public void setFecreg(Date fecreg) {
		this.fecreg = fecreg;
	}

	public String getPasemp() {
		return this.pasemp;
	}

	public void setPasemp(String pasemp) {
		this.pasemp = pasemp;
	}

	public String getPasenc() {
		return this.pasenc;
	}

	public void setPasenc(String pasenc) {
		this.pasenc = pasenc;
	}

	public String getUsuemp() {
		return this.usuemp;
	}

	public void setUsuemp(String usuemp) {
		this.usuemp = usuemp;
	}

	public String getUsureg() {
		return this.usureg;
	}

	public void setUsureg(String usureg) {
		this.usureg = usureg;
	}

	public int getEstusr() {
		return estusr;
	}

	public void setEstusr(int estusr) {
		this.estusr = estusr;
	}

}