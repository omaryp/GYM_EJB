package pe.com.gym.dto;

import java.io.Serializable;


/**
 * @author Omar Yarleque
 * 
 */
public class EmpleadoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int codemp;
	private String nomemp;
	private String apeemp;
	private String dniemp;
	private String usuemp;
	private String diremp;

	public EmpleadoDTO() {
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

}