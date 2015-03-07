package pe.com.gym.entidades_ant;

import java.io.Serializable;

/**
 * 
 * @author Omar Yarleque
 * 
 */
public class PagoPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long corpag;

	private long codcli;

	public PagoPK() {
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PagoPK)) {
			return false;
		}
		PagoPK castOther = (PagoPK)other;
		return 
			(this.corpag == castOther.corpag)
			&& (this.codcli == castOther.codcli);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.corpag ^ (this.corpag >>> 32)));
		hash = hash * prime + ((int) (this.codcli ^ (this.codcli >>> 32)));
		
		return hash;
	}
}