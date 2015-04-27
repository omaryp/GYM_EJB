package pe.com.gym.entidades;

import java.io.Serializable;

/**
 * 
 * @author Omar Yarleque
 */
public class InscripcionPk implements Serializable {
	private static final long serialVersionUID = 1L;

	private long codcli;

	private int codmod;

	private int correl;

	private int codser;

	public long getCodcli() {
		return codcli;
	}

	public void setCodcli(long codcli) {
		this.codcli = codcli;
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

	public int getCodser() {
		return codser;
	}

	public void setCodser(int codser) {
		this.codser = codser;
	}
	
	
}