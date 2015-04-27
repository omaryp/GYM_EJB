package pe.com.gym.entidades;

import java.io.Serializable;


/**
 *
 * @author Omar Yarleque
 */

public class PagoPk implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long corpag;

	private long codcli;

	public PagoPk() {
	}
	
	public long getCorpag() {
		return this.corpag;
	}
	public void setCorpag(long corpag) {
		this.corpag = corpag;
	}
	public long getCodcli() {
		return this.codcli;
	}
	public void setCodcli(long codcli) {
		this.codcli = codcli;
	}

}