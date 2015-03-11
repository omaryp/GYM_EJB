package pe.com.gym.entidades;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;


/**
 * Entidad Cliente
 * @author Omar Yarleque
 */

public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	private long codcli;

	private String apecli;

	private long cliemp;

	private String dircli;

	private String dnicli;

	private String dnirepl;

	private int estcli;

	private Date fecmod;

	private Date fecnac;

	private Date fecreg;

	private Time hofiru;

	private Time hoinru;

	private String nomcli;

	private String razsoc;

	private String repleg;

	private String ruccli;

	private String rutfot;

	private String telef1;

	private String telef2;

	private String tipper;

	private String usumod;

	private String usureg;

	public Cliente() {
	}

	public long getCodcli() {
		return this.codcli;
	}

	public void setCodcli(long codcli) {
		this.codcli = codcli;
	}

	public String getApecli() {
		return this.apecli;
	}

	public void setApecli(String apecli) {
		this.apecli = apecli;
	}

	public long getCliemp() {
		return this.cliemp;
	}

	public void setCliemp(long cliemp) {
		this.cliemp = cliemp;
	}

	public String getDircli() {
		return this.dircli;
	}

	public void setDircli(String dircli) {
		this.dircli = dircli;
	}

	public String getDnicli() {
		return this.dnicli;
	}

	public void setDnicli(String dnicli) {
		this.dnicli = dnicli;
	}

	public String getDnirepl() {
		return this.dnirepl;
	}

	public void setDnirepl(String dnirepl) {
		this.dnirepl = dnirepl;
	}

	public int getEstcli() {
		return this.estcli;
	}

	public void setEstcli(int estcli) {
		this.estcli = estcli;
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

	public Time getHofiru() {
		return this.hofiru;
	}

	public void setHofiru(Time hofiru) {
		this.hofiru = hofiru;
	}

	public Time getHoinru() {
		return this.hoinru;
	}

	public void setHoinru(Time hoinru) {
		this.hoinru = hoinru;
	}

	public String getNomcli() {
		return this.nomcli;
	}

	public void setNomcli(String nomcli) {
		this.nomcli = nomcli;
	}

	public String getRazsoc() {
		return this.razsoc;
	}

	public void setRazsoc(String razsoc) {
		this.razsoc = razsoc;
	}

	public String getRepleg() {
		return this.repleg;
	}

	public void setRepleg(String repleg) {
		this.repleg = repleg;
	}

	public String getRuccli() {
		return this.ruccli;
	}

	public void setRuccli(String ruccli) {
		this.ruccli = ruccli;
	}

	public String getRutfot() {
		return this.rutfot;
	}

	public void setRutfot(String rutfot) {
		this.rutfot = rutfot;
	}

	public String getTelef1() {
		return this.telef1;
	}

	public void setTelef1(String telef1) {
		this.telef1 = telef1;
	}

	public String getTelef2() {
		return this.telef2;
	}

	public void setTelef2(String telef2) {
		this.telef2 = telef2;
	}

	public String getTipper() {
		return this.tipper;
	}

	public void setTipper(String tipper) {
		this.tipper = tipper;
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