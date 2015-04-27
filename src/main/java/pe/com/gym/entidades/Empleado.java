package pe.com.gym.entidades;

import java.io.Serializable;
import java.util.Date;


/**
 * @author Omar Yarleque
 * 
 */
public class Empleado implements Serializable {
	private static final long serialVersionUID = 1L;

	private int codemp;
	private String nomemp;
	private String apeemp;
	private String dniemp;
	private String diremp;
	private String usuemp;
	private Date fecnac;
	private Date fecces;
	private byte estemp;
	private Date fecreg;
	private String usureg;
	private Date fecmod;
	private String usumod;

	public Empleado() {
	}

	public int getCodemp() {
		return this.codemp;
	}

	public void setCodemp(int codemp) {
		this.codemp = codemp;
	}

	public String getApeemp() {
		return this.apeemp;
	}

	public void setApeemp(String apeemp) {
		this.apeemp = apeemp;
	}

	public String getDiremp() {
		return this.diremp;
	}

	public void setDiremp(String diremp) {
		this.diremp = diremp;
	}

	public String getDniemp() {
		return this.dniemp;
	}

	public void setDniemp(String dniemp) {
		this.dniemp = dniemp;
	}

	public byte getEstemp() {
		return this.estemp;
	}

	public void setEstemp(byte estemp) {
		this.estemp = estemp;
	}

	public Date getFecces() {
		return this.fecces;
	}

	public void setFecces(Date fecces) {
		this.fecces = fecces;
	}

	public Date getFecmod() {
		return this.fecmod;
	}

	public void setFecmod(Date fecmod) {
		this.fecmod = fecmod;
	}

	public Date getFecnac() {
		return this.fecnac;
	}

	public void setFecnac(Date fecnac) {
		this.fecnac = fecnac;
	}

	public Date getFecreg() {
		return this.fecreg;
	}

	public void setFecreg(Date fecreg) {
		this.fecreg = fecreg;
	}

	public String getNomemp() {
		return this.nomemp;
	}

	public void setNomemp(String nomemp) {
		this.nomemp = nomemp;
	}

	public String getUsuemp() {
		return this.usuemp;
	}

	public void setUsuemp(String usuemp) {
		this.usuemp = usuemp;
	}

	public String getUsumod() {
		return this.usumod;
	}

	public void setUsumod(String usumod) {
		this.usumod = usumod;
	}

	public String getUsureg() {
		return this.usureg;
	}

	public void setUsureg(String usureg) {
		this.usureg = usureg;
	}

}