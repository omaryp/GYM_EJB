package pe.com.gym.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import pe.com.gym.entidades.Huella;

/**
 * Acceso a datos de Clientes
 * @author Omar Yarleque
 */

@Stateless
public class HuellaDAO implements HuellaDAOLocal {
	
	@Resource(name = "jdbc/__default")
	DataSource ds;
	private final static Logger logger = Logger.getLogger(HuellaDAO.class.getName());
	
	/**
	 * Guarda huella del cliente
	 * @param hue       Objeto huella contiene todas las carateristicas de la huella
	 * @param tamhuella cantidad de bytes de la huella
	 * @return int      Si esta todo correcto retorna cero
	 */
	@Override
	public int guardaHuella(Huella hue,Integer tamhuella){
		Connection cn = null;
		CallableStatement cs = null;
		String cadSql="";
		int res=0;
		try {
			cadSql="{call ingresa_huella(?,?,?,?,?,?)}";
			cn=ds.getConnection();
			cs=cn.prepareCall(cadSql);
			cs.setLong(1, hue.getCodcli());
			cs.setString(2, hue.getDnicli());
			cs.setDate(3,new Date(hue.getFecreg().getTime()));
			cs.setBinaryStream(4, hue.getHuecli(),tamhuella);
			cs.setInt(5, 0);
			cs.setString(6, hue.getUsureg());
			cs.execute();
			res = cs.getUpdateCount()!=0?0:1;
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error en la ejecucion",e);
			res=e.getErrorCode();
		} finally{
			try {
                if (cs != null) {
                    cs.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "No se pudo liberar el recurso");
            }
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "No se pudo liberar el recurso");
            }
		}
		return res;
	}
	
	/**
	 * Obtiene las huellas del cliente
	 * @param dni       Documento de identidad del cliente
	 * @return List<byte[]>  lista que contiene las huellas del cliente
	 */
	@Override
	public List<byte[]> cargarHuellas(String dni){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String cadSql="";
		List<byte[]> huellas = new ArrayList<byte[]>();
		try {
			cadSql="Select HUECLI from TB_HUELLA where DNICLI = ?";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setString(1, dni);
			rs = ps.executeQuery();
			while(rs.next())
				huellas.add(rs.getBytes(1));
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error en la ejecucion",e);
			huellas = null;
		} finally{
			try {
                if (ps!= null) {
                    ps.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "No se pudo liberar el recurso");
            }
			try {
                if (rs!= null) {
                    rs.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "No se pudo liberar el recurso");
            }
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "No se pudo liberar el recurso");
            }
		}
		return huellas;
	}
}