package pe.com.gym.entidades;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * 
 * @author Omar Yarleque
 */
public class Inscripcion implements Serializable {
	private static final long serialVersionUID = 1L;

	private InscripcionPk id; 

	private int estins;

	private Date fecmod;

	private Date fecreg;

	private String nomcli;

	private String nommod;

	private String nomser;

	private String usumod;

	private String usureg;
	
	private Time hoinru;
	
	private Time hofiru;

	public Inscripcion() {
	}
	
	public int getEstins() {
		return estins;
	}

	public void setEstins(int estins) {
		this.estins = estins;
	}

	public Date getFecmod() {
		return this.fecmod;
	}

	public void setFecmod(Date fecmod) {
		this.fecmod = fecmod;
	}

	public Date getFecreg() {
		return this.fecreg;
	}

	public void setFecreg(Date fecreg) {
		this.fecreg = fecreg;
	}

	public String getNomcli() {
		return this.nomcli;
	}

	public void setNomcli(String nomcli) {
		this.nomcli = nomcli;
	}

	public String getNommod() {
		return this.nommod;
	}

	public void setNommod(String nommod) {
		this.nommod = nommod;
	}

	public String getNomser() {
		return this.nomser;
	}

	public void setNomser(String nomser) {
		this.nomser = nomser;
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

	public Time getHoinru() {
		return hoinru;
	}

	public void setHoinru(Time hoinru) {
		this.hoinru = hoinru;
	}

	public Time getHofiru() {
		return hofiru;
	}

	public void setHofiru(Time hofiru) {
		this.hofiru = hofiru;
	}

	public InscripcionPk getId() {
		return id;
	}

	public void setId(InscripcionPk id) {
		this.id = id;
	}	
}