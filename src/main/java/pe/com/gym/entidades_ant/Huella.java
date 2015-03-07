package pe.com.gym.entidades_ant;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Date;


/**
 * 
 * @author Omar Yarleque
 * 
 */
public class Huella implements Serializable {

	private static final long serialVersionUID = -9016426655739012288L;

	private HuellaPK id;

	private String dnicli;

	private int esthue;

	private Date fecreg;

	private ByteArrayInputStream huecli;

	private String usuReg;

	public Huella() {
	}

	public HuellaPK getId() {
		return this.id;
	}

	public void setId(HuellaPK id) {
		this.id = id;
	}

	public String getDnicli() {
		return this.dnicli;
	}

	public void setDnicli(String dnicli) {
		this.dnicli = dnicli;
	}

	public Date getFecreg() {
		return this.fecreg;
	}

	public void setFecreg(Date fecreg) {
		this.fecreg = fecreg;
	}

	public ByteArrayInputStream getHuecli() {
		return huecli;
	}

	public void setHuecli(ByteArrayInputStream huecli) {
		this.huecli = huecli;
	}

	public String getUsuReg() {
		return usuReg;
	}

	public void setUsuReg(String usuReg) {
		this.usuReg = usuReg;
	}

	public int getEsthue() {
		return esthue;
	}

	public void setEsthue(int esthue) {
		this.esthue = esthue;
	}

}