package pe.com.gym.entidades;

import java.io.Serializable;

/**
 * Entidad Digide
 * 
 * @author Omar Yarleque
 */

public class Digide implements Serializable {
	private static final long serialVersionUID = 1L;

	private long digide;

	private String codtab;

	private String descor;

	private String deslar;

	private double valde1;

	private double valde2;

	private int valent;

	public Digide() {
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

	public double getValde1() {
		return this.valde1;
	}

	public void setValde1(double valde1) {
		this.valde1 = valde1;
	}

	public double getValde2() {
		return this.valde2;
	}

	public void setValde2(double valde2) {
		this.valde2 = valde2;
	}

	public int getValent() {
		return this.valent;
	}

	public void setValent(int valent) {
		this.valent = valent;
	}

}