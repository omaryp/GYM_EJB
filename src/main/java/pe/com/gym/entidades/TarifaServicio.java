package pe.com.gym.entidades;

import java.io.Serializable;
import java.util.Date;

public class TarifaServicio implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private TarifaServicioPK id;
	private String desmod;
	private double monto;
	private int estado;
	private Date fecreg;
	private String usureg;
	
	public TarifaServicio(){}
	
	public TarifaServicioPK getId() {
		return id;
	}

	public void setId(TarifaServicioPK id) {
		this.id = id;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Date getFecreg() {
		return fecreg;
	}

	public void setFecreg(Date fecreg) {
		this.fecreg = fecreg;
	}

	public String getUsureg() {
		return usureg;
	}

	public void setUsureg(String usureg) {
		this.usureg = usureg;
	}

	public String getDesmod() {
		return desmod;
	}

	public void setDesmon(String desmod) {
		this.desmod = desmod;
	}
		
}
