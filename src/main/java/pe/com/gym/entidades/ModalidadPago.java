package pe.com.gym.entidades;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Omar Yarleque
 */

public class ModalidadPago implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long codmod;

	private String desmod;

	private int diamod;

	private int estmod;

	private Date fecmod;

	private Date fecreg;

	private String nommod;

	private String usumod;

	private String usureg;

	public ModalidadPago() {
	}

	public long getCodmod() {
		return this.codmod;
	}

	public void setCodmod(long codmod) {
		this.codmod = codmod;
	}

	public String getDesmod() {
		return this.desmod;
	}

	public void setDesmod(String desmod) {
		this.desmod = desmod;
	}

	public int getDiamod() {
		return this.diamod;
	}

	public void setDiamod(int diamod) {
		this.diamod = diamod;
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

	public String getNommod() {
		return this.nommod;
	}

	public void setNommod(String nommod) {
		this.nommod = nommod;
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