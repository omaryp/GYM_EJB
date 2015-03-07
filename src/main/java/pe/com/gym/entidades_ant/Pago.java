package pe.com.gym.entidades_ant;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 
 * @author Omar Yarleque
 * 
 */
public class Pago implements Serializable {
	private static final long serialVersionUID = 1L;

	private PagoPK id;

	private BigDecimal descue;

	private Date fecpag;

	private BigDecimal monpag;

	private BigDecimal monto;

	public Pago() {
	}

	public PagoPK getId() {
		return this.id;
	}

	public void setId(PagoPK id) {
		this.id = id;
	}

	public BigDecimal getDescue() {
		return this.descue;
	}

	public void setDescue(BigDecimal descue) {
		this.descue = descue;
	}

	public Date getFecpag() {
		return this.fecpag;
	}

	public void setFecpag(Date fecpag) {
		this.fecpag = fecpag;
	}

	public BigDecimal getMonpag() {
		return this.monpag;
	}

	public void setMonpag(BigDecimal monpag) {
		this.monpag = monpag;
	}

	public BigDecimal getMonto() {
		return this.monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

}