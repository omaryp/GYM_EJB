package pe.com.gym.dto;

import java.io.Serializable;

import pe.com.gym.entidades.InscripcionPk;

/**
 * 
 * @author Omar Yarleque
 */
public class InscripcionDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private InscripcionPk id; 
	
	private int estins;

	private String nomcli;

	private String nommod;

	private String nomser;

	private String usumod;

	public InscripcionDTO() {
	}
	
	public int getEstins() {
		return estins;
	}

	public void setEstins(int estins) {
		this.estins = estins;
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

	public InscripcionPk getId() {
		return id;
	}

	public void setId(InscripcionPk id) {
		this.id = id;
	}	
}