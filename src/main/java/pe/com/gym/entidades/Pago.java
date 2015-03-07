package pe.com.gym.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 *
 * @author Omar Yarleque
 */

public class Pago implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long corpag;

	private long codcli;

	private double descue;

	private Date fecpag;
	
	private Date fecven;

	private BigDecimal monpag;

	private BigDecimal monto;

	private String usureg;

	public Pago() {
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

	public double getDescue() {
		return this.descue;
	}

	public void setDescue(double descue) {
		this.descue = descue;
	}

	public Date getFecpag() {
		return this.fecpag;
	}

	public void setFecpag(Date fecpag) {
		this.fecpag = fecpag;
	}

	public Date getFecven() {
		return this.fecven;
	}

	public void setFecven(Date fecven) {
		this.fecven = fecven;
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

	public String getUsureg() {
		return this.usureg;
	}

	public void setUsureg(String usureg) {
		this.usureg = usureg;
	}

}