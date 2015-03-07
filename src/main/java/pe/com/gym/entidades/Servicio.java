package pe.com.gym.entidades;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * @author Omar Yarleque
 */

public class Servicio implements Serializable {
	private static final long serialVersionUID = 1L;

	private int codser;

	private String desser;

	private int estser;

	private Date fecmod;

	private Date fecreg;

	private int monser;

	private String nomser;

	private String usumod;

	private String usureg;

	public Servicio() {
	}

	public int getCodser() {
		return this.codser;
	}

	public void setCodser(int codser) {
		this.codser = codser;
	}

	public String getDesser() {
		return this.desser;
	}

	public void setDesser(String desser) {
		this.desser = desser;
	}

	public int getEstser() {
		return this.estser;
	}

	public void setEstser(int estser) {
		this.estser = estser;
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

	public int getMonser() {
		return this.monser;
	}

	public void setMonser(int monser) {
		this.monser = monser;
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