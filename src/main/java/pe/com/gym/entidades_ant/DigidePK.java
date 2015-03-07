package pe.com.gym.entidades_ant;

import java.io.Serializable;

/**
 * 
 * @author Omar Yarleque
 * 
 */
public class DigidePK implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long digide;

	private String codtab;

	public DigidePK() {
	}
	public long getDigide() {
		return this.digide;
	}
	public void setDigide(long digide) {
		this.digide = digide;
	}
	public String getCodtab() {
		return this.codtab;
	}
	public void setCodtab(String codtab) {
		this.codtab = codtab;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DigidePK)) {
			return false;
		}
		DigidePK castOther = (DigidePK)other;
		return 
			(this.digide == castOther.digide)
			&& this.codtab.equals(castOther.codtab);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.digide ^ (this.digide >>> 32)));
		hash = hash * prime + this.codtab.hashCode();
		
		return hash;
	}
}