package pe.com.gym.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tb_usuario database table.
 * 
 */
@Embeddable
public class UsuarioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int codemp;

	private int correl;

	public UsuarioPK() {
	}

	public int getCodemp() {
		return codemp;
	}

	public void setCodemp(int codemp) {
		this.codemp = codemp;
	}

	public int getCorrel() {
		return correl;
	}

	public void setCorrel(int correl) {
		this.correl = correl;
	}

	
}