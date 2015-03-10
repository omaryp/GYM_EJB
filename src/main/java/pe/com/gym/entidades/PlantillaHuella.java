package pe.com.gym.entidades;

import java.io.Serializable;

public class PlantillaHuella implements Serializable {

	private static final long serialVersionUID = 1L;

	private byte[] plantilla;
	
	public PlantillaHuella(){
		
	}

	public byte[] getPlantilla() {
		return plantilla;
	}

	public void setPlantilla(byte[] plantilla) {
		this.plantilla = plantilla;
	}
	
}
