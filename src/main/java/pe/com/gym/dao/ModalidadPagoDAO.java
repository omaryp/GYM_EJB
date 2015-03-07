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
import pe.com.gym.entidades_ant.ModalidadPago;

/**
 * Administración de las modaliades de pago
 * @author Omar Yarleque
 */

@Stateless
public class ModalidadPagoDAO implements ModalidadPagoDAOLocal {
	
	@Resource(name = "jdbc/__default")
	DataSource ds;
	private final static Logger logger = Logger.getLogger(ModalidadPagoDAO.class.getName());
	
	/**
	 * Guarda modalidad de pago
	 * @param modalidad Objeto <code>ModaliadPago</code> a guardar
	 * @return int      Si esta todo correcto retorna cero
	 */
	public int registraModalidad(ModalidadPago modal){
		Connection cn = null;
		PreparedStatement ps = null;
		String cadSql="";
		int res=0;
		try {
			cadSql="INSERT INTO TB_MODALIDA_PAGO (CODMOD,DESMOD,ESTMOD,MONCUO,DIAMOD) VALUES(?,?,?,?)";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);

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
	 * @return Asistencia  Objeto asistencia si no existe retorna null
	 */
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