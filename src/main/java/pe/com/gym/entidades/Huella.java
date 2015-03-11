package pe.com.gym.entidades;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Date;


/**
 * 
 * @author Omar Yarleque
 */
public class Huella implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long codcli;

	private long corhue;

	private String dnicli;

	private int esthue;

	private Date fecreg;
	
	private ByteArrayInputStream huecli;

	private String usureg;

	public Huella() {
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

	public String getDnicli() {
		return this.dnicli;
	}

	public void setDnicli(String dnicli) {
		this.dnicli = dnicli;
	}

	public int getEsthue() {
		return this.esthue;
	}

	public void setEsthue(int esthue) {
		this.esthue = esthue;
	}

	public Date getFecreg() {
		return this.fecreg;
	}

	public void setFecreg(Date fecreg) {
		this.fecreg = fecreg;
	}

	public ByteArrayInputStream getHuecli() {
		return this.huecli;
	}

	public void setHuecli(ByteArrayInputStream huecli) {
		this.huecli = huecli;
	}

	public String getUsureg() {
		return this.usureg;
	}

	public void setUsureg(String usureg) {
		this.usureg = usureg;
	}

}