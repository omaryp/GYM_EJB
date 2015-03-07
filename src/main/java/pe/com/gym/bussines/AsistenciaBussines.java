package pe.com.gym.bussines;


import java.sql.Time;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gym.dao.AsistenciaDAOLocal;
import pe.com.gym.dao.HuellaDAOLocal;
import pe.com.gym.dto.ClienteDTO;
import pe.com.gym.entidades_ant.Asistencia;
import pe.com.gym.entidades_ant.PlantillaHuella;

import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPFeatureSetFactory;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;


/**
 * 
 * @author Omar Yarleque
 *
 */
@Stateless
public class AsistenciaBussines implements AsistenciaBussinesLocal {
	
	@EJB
	private HuellaDAOLocal huella;
	@EJB
	private AsistenciaDAOLocal reg_asis;
	
	private final static Logger logger = Logger.getLogger(AsistenciaBussines.class.getName());
		
	@Override
	public int marcarAsistencia(ClienteDTO cliente, PlantillaHuella verifi){
		logger.log(Level.INFO,"marcar Asistencia");
		Asistencia asistencia = new Asistencia();
		asistencia.setCodcli(cliente.getCodigoCliente());
		java.util.Date fechahoy = new java.util.Date();
		asistencia.setFecmar(new Date(fechahoy.getTime()));
		asistencia.setHormar(new Time(fechahoy.getTime()));
		int res = 0;
		if(verificarHuella(cliente, verifi)){
			if(yaMarco(cliente)){
				asistencia.setTipmar(2);
				res = 1;
			}
			else
				asistencia.setTipmar(1);
			reg_asis.registraAsistencia(asistencia);	
		}
		else 
			res = 2;
		return res;
	}
	
	public boolean verificarHuella(ClienteDTO cliente,PlantillaHuella verifi){
		try {
			DPFPVerification Verificador = DPFPGlobal.getVerificationFactory().createVerification();
			DPFPFeatureSetFactory fabrica = DPFPGlobal.getFeatureSetFactory();
			DPFPFeatureSet featuresverificacion = fabrica.createFeatureSet(verifi.getPlantilla());
			List<byte[]>huellas = huella.cargarHuellas(cliente.getDni());
			int cont = 0;
			for (byte[] dh : huellas) {
				DPFPTemplate referenceTemplate = DPFPGlobal.getTemplateFactory().createTemplate(dh);
				DPFPVerificationResult result = Verificador.verify(featuresverificacion, referenceTemplate);
				if(result.isVerified())
					cont++;
			}
			if(cont!=0) return true;
			return false;
		} catch (Exception e) {
			logger.log(Level.SEVERE,"Error al verificar la huella ",e);
			return false;
		}
	}
	
	public boolean yaMarco(ClienteDTO cliente){
		logger.log(Level.INFO,"ya Marco");
		java.util.Date utilDate = new java.util.Date();
	    java.sql.Date fechaHoy = new java.sql.Date(utilDate.getTime());
	    return  reg_asis.getAsistencia(cliente, fechaHoy);
	}
	
}
