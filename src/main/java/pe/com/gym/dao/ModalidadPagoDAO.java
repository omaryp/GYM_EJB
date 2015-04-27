package pe.com.gym.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import pe.com.gym.entidades.ModalidadPago;

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
	 * Obtiene modalidad de pago
	 * @param codMod numero del tipo <code>long</code>, código de modalidad
	 * @return ModalidadPago retorna la modalidad que tenga la coincidencia con el código pasado como parametro 
	 */
	@Override
	public ModalidadPago getModalidad(long codMod){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String cadSql="";
		ModalidadPago modalidad;
		try {
			cadSql="SELECT CODMOD,NOMMOD,ESTMOD,DIAMOD,DESMOD,USUREG,FECREG,USUMOD,FECMOD TB_MODALIDAD_PAGO WHERE CODMOD = ?";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setLong(1,codMod);
			rs = ps.executeQuery();
			if(rs.next()){
				modalidad = new ModalidadPago();
				modalidad.setCodmod(rs.getLong(1));
				modalidad.setNommod(rs.getString(2));
				modalidad.setEstmod(rs.getInt(3));
				modalidad.setDiamod(rs.getInt(4));
				modalidad.setDesmod(rs.getString(5));
				modalidad.setUsureg(rs.getString(6));
				modalidad.setFecreg(rs.getDate(7));
				modalidad.setUsumod(rs.getString(8));
				modalidad.setFecmod(rs.getDate(9));
			}else
				modalidad = null;
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error en la ejecucion",e);
			modalidad = null;
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
		return modalidad;
	}
	
	/**
	 * Guarda modalidad de pago
	 * @param modalidad Objeto <code>ModaliadPago</code> a guardar
	 * @return int   Si esta todo correcto retorna cero
	 */
	@Override
	public int registraModalidad(ModalidadPago modal){
		Connection cn = null;
		PreparedStatement ps = null;
		String cadSql="";
		int res=0;
		try {
			cadSql="INSERT INTO TB_MODALIDAD_PAGO (CODMOD,NOMMOD,ESTMOD,DIAMOD,DESMOD,USUREG,FECREG) VALUES(?,?,?,?,?,?,?)";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setLong(1, modal.getCodmod());
			ps.setString(2, modal.getNommod());
			ps.setInt(3,modal.getEstmod());
			ps.setInt(4, modal.getDiamod());
			ps.setString(5, modal.getDesmod());
			ps.setString(6, modal.getUsureg());
			ps.setDate(7,new Date(modal.getFecreg().getTime()));
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
	 * Actualiza Modalidad de pago
	 * @param modalidad Objeto <code>ModaliadPago</code> a guardar
	 * @return int   Si esta todo correcto retorna cero
	 */
	@Override
	public int actualizaModalidad(ModalidadPago modal){
		Connection cn = null;
		PreparedStatement ps = null;
		String cadSql="";
		int res=0;
		try {
			cadSql="UPDATE TB_MODALIDAD_PAGO SET NOMMOD = ?,ESTMOD = ?,DIAMOD = ?,DESMOD = ?,USUMOD = ?,FECMOD = ? WHERE CODMOD = ?";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setString(1, modal.getNommod());
			ps.setInt(2,modal.getEstmod());
			ps.setInt(3, modal.getDiamod());
			ps.setString(4, modal.getDesmod());
			ps.setString(5, modal.getUsumod());
			ps.setDate(6, new Date(modal.getFecmod().getTime()));
			ps.setLong(7, modal.getCodmod());
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
	 * 
	 * Obtiene nuevo código de modalidad
	 * @return Long nuevo código.
	 */
	@Override
	public long getCodigoModalidadNva() {
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		String cadSql="";
		long codigo = 0;
		try {
			cadSql="SELECT MAX(CODMOD) FROM TB_MODALIDAD_PAGO";
			cn=ds.getConnection();
			st=cn.createStatement();
			rs=st.executeQuery(cadSql);
			if(rs.next())
				codigo = rs.getLong(1)+1;
			else 
				codigo = 0;
			
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error en la ejecucion",e);
			codigo = e.getErrorCode();
		} finally{
			try {
                if (st!= null) {
                    st.close();
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
		return codigo;
	}
	
	/**
	 * Lista las modalidades de pago de acuerdo a alguna coincidencia y los límites de la paginación
	 * @param String Coincidencia 
	 * @param int[] limites para la paginación
	 * @return Map contiene las coincidencias.
	 */
	@SuppressWarnings("resource")
	@Override
	public Map<String, Object> listaModalidades(String valBus,int[] limites){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String cadSql = "";
		int total = 0;
		List<ModalidadPago> modalidades = new ArrayList<ModalidadPago>();
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			//Se obtiene total de registros
			cadSql="SELECT COUNT(*) FROM TB_MODALIDAD_PAGO WHERE NOMMOD like ? ";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setString(1, valBus+"%");
			rs=ps.executeQuery();
			if(rs.next())
				total = rs.getInt(1);
			else
				total = 0;
			map.put("TOTAL", total);
			
			if(ps!=null){
				ps.close();
				ps = null;
			}
			
			//Se obtiene los clientes por páginas
			cadSql="SELECT CODMOD,NOMMOD,ESTMOD,DIAMOD,DESMOD FROM TB_MODALIDAD_PAGO WHERE NOMMOD like ? ORDER BY CODMOD DESC LIMIT ?,? ";
			ps=cn.prepareStatement(cadSql);
			ps.setString(1, valBus+"%");
			ps.setInt(2, limites[0]);
			ps.setInt(3, limites[1]);
			rs=ps.executeQuery();
			while(rs.next()){
				ModalidadPago modalidad= new ModalidadPago();
				modalidad.setCodmod(rs.getLong(1));
				modalidad.setNommod(rs.getString(2));
				modalidad.setEstmod(rs.getInt(3));
				modalidad.setDiamod(rs.getInt(4));
				modalidad.setDesmod(rs.getString(5));
				modalidades.add(modalidad);
			}
			map.put("MODALIDADES", modalidades);
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error en la ejecucion",e);
			map = null;
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
		return map;
	}
	
	/**
	 * Lista las modalidades activas
	 * @return List lista con la modalidades activas.
	 */
	@Override
	public List<ModalidadPago> listaModalidades(){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String cadSql = "";
		List<ModalidadPago> modalidades = new ArrayList<ModalidadPago>();
		try {
			//Se obtiene los clientes por páginas
			cadSql="SELECT CODMOD,NOMMOD,DIAMOD,DESMOD FROM TB_MODALIDAD_PAGO WHERE ESTMOD = ? ";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setInt(1,0);
			rs=ps.executeQuery();
			while(rs.next()){
				ModalidadPago modalidad= new ModalidadPago();
				modalidad.setCodmod(rs.getLong(1));
				modalidad.setNommod(rs.getString(2));
				modalidad.setDiamod(rs.getInt(3));
				modalidad.setDesmod(rs.getString(4));
				modalidades.add(modalidad);
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error en la ejecucion",e);
			modalidades =  null;
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
		return modalidades;
	}
	
	/**
	 * Dar de baja a la modalidad de pago, sólo se le cambia el estado
	 * @param codMod código de la modalidad tipo<code>long</code>
	 * @return int   Si esta todo correcto retorna cero
	 */
	@Override
	public int cambiaEstadoModalidad(long codMod,int nvoEstado){
		Connection cn = null;
		PreparedStatement ps = null;
		String cadSql="";
		int res=0;
		try {
			cadSql="UPDATE TB_MODALIDAD_PAGO SET ESTMOD = ? WHERE CODMOD = ?";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setInt(1,nvoEstado);
			ps.setLong(2,codMod);
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
}