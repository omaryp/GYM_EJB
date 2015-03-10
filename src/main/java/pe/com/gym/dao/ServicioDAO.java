package pe.com.gym.dao;

import java.sql.Connection;
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

import pe.com.gym.entidades.Servicio;

/**
 * Administración de las modaliades de pago
 * @author Omar Yarleque
 */

@Stateless
public class ServicioDAO implements ServicioDAOLocal {
	
	@Resource(name = "jdbc/__default")
	DataSource ds;
	private final static Logger logger = Logger.getLogger(ServicioDAO.class.getName());
	
	/**
	 * Obtiene servicio
	 * @param codSer numero del tipo <code>long</code>, código de servicio
	 * @return Servicio retorna el servicio que tenga la coincidencia con el código pasado como parametro 
	 */
	@Override
	public Servicio getServicio(int codSer){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String cadSql="";
		Servicio servicio;
		try {
			cadSql="SELECT CODSER,NOMSER,DESER,ESTSER,MONSER,USUREG,FECREG,USUMOD,FECMOD TB_SERVICIOS WHERE CODSER = ?";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setLong(1,codSer);
			rs = ps.executeQuery();
			if(rs.next()){
				servicio = new Servicio();
				servicio.setCodser(rs.getInt(1));
				servicio.setNomser(rs.getString(2));
				servicio.setDesser(rs.getString(3));
				servicio.setEstser(rs.getInt(4));
				servicio.setMonser(rs.getDouble(5));
				servicio.setUsureg(rs.getString(5));
				servicio.setFecreg(rs.getDate(6));
				servicio.setUsumod(rs.getString(7));
				servicio.setFecmod(rs.getDate(8));
			}else
				servicio = null;
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error en la ejecucion",e);
			servicio = null;
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
		return servicio;
	}
	
	/**
	 * Guarda servicio
	 * @param servic Objeto <code>Servicio</code> a guardar
	 * @return int   Si esta todo correcto retorna cero
	 */
	@Override
	public int registraServicio(Servicio servic){
		Connection cn = null;
		PreparedStatement ps = null;
		String cadSql="";
		int res=0;
		try {
			cadSql="INSERT INTO TB_SERVICIOS (CODSER,NOMSER,DESER,ESTSER,MONSER,USUREG,FECREG) VALUES(?,?,?,?,?,?,?)";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setLong(1, servic.getCodser());
			ps.setString(2, servic.getNomser());
			ps.setString(3, servic.getDesser());
			ps.setInt(4,servic.getEstser());
			ps.setDouble(5, servic.getMonser());
			ps.setString(6, servic.getUsureg());
			ps.setDate(7, servic.getFecreg());
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
	 * Actualiza Servicio
	 * @param servic Objeto <code>Servicio</code> a guardar
	 * @return int   Si esta todo correcto retorna cero
	 */
	@Override
	public int actualizaServicio(Servicio servic){
		Connection cn = null;
		PreparedStatement ps = null;
		String cadSql="";
		int res=0;
		try {
			cadSql="UPDATE TB_SERVICIOS SET NOMSER = ?,ESTSER = ?,MONSER = ?,USUMOD = ?,FECMOD = ? WHERE CODSER = ?";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setString(1, servic.getNomser());
			ps.setInt(2,servic.getEstser());
			ps.setDouble(3, servic.getMonser());
			ps.setString(4, servic.getUsumod());
			ps.setDate(5, servic.getFecmod());
			ps.setLong(6, servic.getCodser());
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
	 * Obtiene nuevo código de servicio
	 * @return Long nuevo código.
	 */
	@Override
	public int getCodigoServicioNvo() {
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		String cadSql="";
		int codigo = 0;
		try {
			cadSql="SELECT MAX(CODSER) FROM TB_SERVICIOS";
			cn=ds.getConnection();
			st=cn.createStatement();
			rs=st.executeQuery(cadSql);
			if(rs.next())
				codigo = rs.getInt(1)+1;
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
	 * Lista los servicios de acuerdo a alguna coincidencia y los límites de la paginación
	 * @param String Coincidencia 
	 * @param int[] limites para la paginación
	 * @return Map contiene las coincidencias.
	 */
	@SuppressWarnings("resource")
	@Override
	public Map<String, Object> listaServicios(String valBus,int[] limites){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String cadSql = "";
		int total = 0;
		List<Servicio> servicios = new ArrayList<Servicio>();
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			//Se obtiene total de registros
			cadSql="SELECT COUNT(*) FROM TB_SERVICIOS WHERE NOMSER like ? ";
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
			cadSql="SELECT CODSER,NOMSER,ESTSER,MONSER FROM TB_SERVICIOS WHERE NOMSER like ?) ORDER BY CODSER DESC LIMIT ?,? ";
			ps=cn.prepareStatement(cadSql);
			ps.setString(1, valBus+"%");
			ps.setInt(2, limites[0]);
			ps.setInt(3, limites[1]);
			rs=ps.executeQuery();
			while(rs.next()){
				Servicio servicio= new Servicio();
				servicio.setCodser(rs.getInt(1));
				servicio.setNomser(rs.getString(2));
				servicio.setEstser(rs.getInt(3));
				servicio.setMonser(rs.getDouble(4));
				servicios.add(servicio);
			}
			map.put("SERVICIOS", servicios);
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
	 * Lista las servicios activos
	 * @return List lista con servicios activos.
	 */
	@Override
	public List<Servicio> listaServicios(){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String cadSql = "";
		List<Servicio> servicios = new ArrayList<Servicio>();
		try {
			//Se obtiene los clientes por páginas
			cadSql="SELECT CODSER,NOMSER,MONSER FROM TB_SERVICIOS WHERE ESTSER = ? ";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setInt(1,1);
			rs=ps.executeQuery();
			while(rs.next()){
				Servicio servicio= new Servicio();
				servicio.setCodser(rs.getInt(1));
				servicio.setNomser(rs.getString(2));
				servicio.setMonser(rs.getDouble(3));
				servicios.add(servicio);
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error en la ejecucion",e);
			servicios =  null;
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
		return servicios;
	}
	
	/**
	 * Dar de baja al servicio, sólo se le cambia el estado
	 * @param codMod código del servicio tipo<code>long</code>
	 * @return int   Si esta todo correcto retorna cero
	 */
	@Override
	public int cambiaEstadoServicio(long codMod,int nvoEstado){
		Connection cn = null;
		PreparedStatement ps = null;
		String cadSql="";
		int res=0;
		try {
			cadSql="UPDATE TB_SERVICIOS SET ESTSER = ? WHERE CODSER = ?";
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