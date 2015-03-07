package pe.com.gym.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import pe.com.gym.dto.ClienteDTO;
import pe.com.gym.entidades_ant.Asistencia;

/**
 * Registro de asistencia
 * @author Omar Yarleque
 */

@Stateless
public class AsistenciaDAO implements AsistenciaDAOLocal {
	
	@Resource(name = "jdbc/__default")
	DataSource ds;
	private final static Logger logger = Logger.getLogger(AsistenciaDAO.class.getName());
	
	/**
	 * Guarda registro de aistencia de los clientes al gym
	 * @param asis      Objeto Asistencia con los datos del cliente a marcar entrada
	 * @return int      Si esta todo correcto retorna cero
	 */
	@Override
	public int registraAsistencia(Asistencia asis){
		Connection cn = null;
		PreparedStatement ps = null;
		String cadSql="";
		int res=0;
		try {
			cadSql="INSERT INTO TB_ASISTENCIA (CODCLI,TIPMAR,FECMAR,HORMAR) VALUES(?,?,?,?)";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setLong(1,asis.getCodcli());
			ps.setInt(2,asis.getTipmar());
			ps.setDate(3,asis.getFecmar());
			ps.setTime(4,asis.getHormar());
			ps.execute();
			res = ps.getUpdateCount()!=0?0:1;
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error en la ejecucion",e);
			res=e.getErrorCode();
		} finally{
			try {
                if (ps != null) {
                    ps.close();
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
	 * Verifica si el cliente tiene registrada una asistencia en el día
	 * @param cli  Objeto <code>ClienteDTO</code> para determinar si hay registrada una asistencia
	 * @param fechaHoy  Objeto <code>Date</code> con la fecha de marcación de hoy.
	 * @return boolean  si hay marcación devuelve true
	 */
	@Override
	public boolean getAsistencia(ClienteDTO cli,Date fechaHoy){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs ;
		String cadSql="";
		try {
			cadSql="SELECT * FROM TB_ASISTENCIA WHERE CODCLI = ? and FECMAR = ?";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setLong(1,cli.getCodigoCliente());
			ps.setDate(2,fechaHoy);
			rs = ps.executeQuery();
			if(rs.next())
				return true;
			return false;
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error en la ejecucion",e);
			return false;
		} finally{
			try {
                if (ps != null) {
                    ps.close();
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
	}
}