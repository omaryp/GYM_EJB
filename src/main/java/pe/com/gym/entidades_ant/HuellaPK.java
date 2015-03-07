package pe.com.gym.entidades_ant;

import java.io.Serializable;

/**
 * 
 * @author Omar Yarleque
 * 
 */
public class HuellaPK implements Serializable {
	private static final long serialVersionUID = 1L;

	private long codcli;

	private long corhue;

	public HuellaPK() {
	}
	public long getCodcli() {
		return this.codcli;
	}
	public void setCodcli(long codcli) {
		this.codcli = codcli;
	}
	public long getCorhue() {
		return this.corhue;
	}
	public void setCorhue(long corhue) {
		this.corhue = corhue;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof HuellaPK)) {
			return false;
		}
		HuellaPK castOther = (HuellaPK)other;
		return 
			(this.codcli == castOther.codcli)
			&& (this.corhue == castOther.corhue);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.codcli ^ (this.codcli >>> 32)));
		hash = hash * prime + ((int) (this.corhue ^ (this.corhue >>> 32)));
		
		return hash;
	}
}