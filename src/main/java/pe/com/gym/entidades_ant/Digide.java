package pe.com.gym.entidades_ant;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 
 * @author Omar Yarleque
 * 
 */
public class Digide implements Serializable {
	private static final long serialVersionUID = 1L;

	private DigidePK id;

	private String descor;

	private String deslar;

	private BigDecimal valde1;

	private BigDecimal valde2;

	private BigDecimal valent;

	public Digide() {
	}

	public DigidePK getId() {
		return this.id;
	}

	public void setId(DigidePK id) {
		this.id = id;
	}

	public String getDescor() {
		return this.descor;
	}

	public void setDescor(String descor) {
		this.descor = descor;
	}

	public String getDeslar() {
		return this.deslar;
	}

	public void setDeslar(String deslar) {
		this.deslar = deslar;
	}

	public BigDecimal getValde1() {
		return this.valde1;
	}

	public void setValde1(BigDecimal valde1) {
		this.valde1 = valde1;
	}

	public BigDecimal getValde2() {
		return this.valde2;
	}

	public void setValde2(BigDecimal valde2) {
		this.valde2 = valde2;
	}

	public BigDecimal getValent() {
		return this.valent;
	}

	public void setValent(BigDecimal valent) {
		this.valent = valent;
	}

}