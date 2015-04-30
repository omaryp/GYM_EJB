package pe.com.gym.entidades;

import java.io.Serializable;

public class TarifaServicioPK implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int codser;
	private int codmod;
	private int correl;
	
	public TarifaServicioPK(){}

	public int getCodser() {
		return codser;
	}

	public void setCodser(int codser) {
		this.codser = codser;
	}

	public int getCodmod() {
		return codmod;
	}

	public void setCodmod(int codmod) {
		this.codmod = codmod;
	}

	public int getCorrel() {
		return correl;
	}

	public void setCorrel(int correl) {
		this.correl = correl;
	}

		
}
