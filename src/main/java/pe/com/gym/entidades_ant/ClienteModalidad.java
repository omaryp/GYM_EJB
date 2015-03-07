package pe.com.gym.entidades_ant;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 
 * @author Omar Yarleque
 * 
 */
public class ClienteModalidad implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private ClienteModalidadPK id;

	private BigDecimal estmod;

	private Date fecreg;

	private Date feprov;

	public ClienteModalidad() {
	}

	public ClienteModalidadPK getId() {
		return this.id;
	}

	public void setId(ClienteModalidadPK id) {
		this.id = id;
	}

	public BigDecimal getEstmod() {
		return this.estmod;
	}

	public void setEstmod(BigDecimal estmod) {
		this.estmod = estmod;
	}

	public Date getFecreg() {
		return this.fecreg;
	}

	public void setFecreg(Date fecreg) {
		this.fecreg = fecreg;
	}

	public Date getFeprov() {
		return this.feprov;
	}

	public void setFeprov(Date feprov) {
		this.feprov = feprov;
	}

}