package pe.com.gym.entidades_ant;

import java.io.Serializable;

/**
 * 
 * @author Omar Yarleque
 * 
 */
public class ClienteModalidadPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private long codcli;

	private long codmod;

	private long correl;

	public ClienteModalidadPK() {
	}
	public long getCodcli() {
		return this.codcli;
	}
	public void setCodcli(long codcli) {
		this.codcli = codcli;
	}
	public long getCodmod() {
		return this.codmod;
	}
	public void setCodmod(long codmod) {
		this.codmod = codmod;
	}
	public long getCorrel() {
		return this.correl;
	}
	public void setCorrel(long correl) {
		this.correl = correl;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ClienteModalidadPK)) {
			return false;
		}
		ClienteModalidadPK castOther = (ClienteModalidadPK)other;
		return 
			(this.codcli == castOther.codcli)
			&& (this.codmod == castOther.codmod)
			&& (this.correl == castOther.correl);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.codcli ^ (this.codcli >>> 32)));
		hash = hash * prime + ((int) (this.codmod ^ (this.codmod >>> 32)));
		hash = hash * prime + ((int) (this.correl ^ (this.correl >>> 32)));
		
		return hash;
	}
}