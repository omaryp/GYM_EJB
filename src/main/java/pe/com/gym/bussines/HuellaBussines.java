package pe.com.gym.bussines;

import java.io.ByteArrayInputStream;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gym.dao.HuellaDAOLocal;
import pe.com.gym.dto.ClienteDTO;
import pe.com.gym.entidades.Huella;
import pe.com.gym.entidades.PlantillaHuella;

/**
 * 
 * @author Omar Yarleque
 *
 */
@Stateless
public class HuellaBussines implements HuellaBussinesLocal {
	
	@EJB
	private HuellaDAOLocal huella;
	
	@Override
	public int guardarHuella(ClienteDTO cliente,PlantillaHuella template) {
		byte[] plantilla = template.getPlantilla();
		ByteArrayInputStream huella_bytes = new ByteArrayInputStream(plantilla);
		Integer longitud = plantilla.length;
		Huella hue = new Huella();
		hue.setCodcli(cliente.getCodigoCliente());
		hue.setCorhue(0);
		hue.setDnicli(cliente.getDni());
		hue.setEsthue(0);
		hue.setFecreg(new Date());
		hue.setHuecli(huella_bytes);
		hue.setUsureg("");
		return huella.guardaHuella(hue, longitud);
	}
	
}
