package pe.com.gym.entidades_ant;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 
 * @author Omar Yarleque
 * 
 */
public class ModalidadPago implements Serializable {
	private static final long serialVersionUID = 1L;

	private long codmod;

	private String desmod;

	private int diamod;

	private int estmod;

	private BigDecimal moncuo;

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
		return diamod;
	}

	public void setDiamod(int diamod) {
		this.diamod = diamod;
	}

	public int getEstmod() {
		return estmod;
	}

	public void setEstmod(int estmod) {
		this.estmod = estmod;
	}

	public BigDecimal getMoncuo() {
		return this.moncuo;
	}

	public void setMoncuo(BigDecimal moncuo) {
		this.moncuo = moncuo;
	}

}