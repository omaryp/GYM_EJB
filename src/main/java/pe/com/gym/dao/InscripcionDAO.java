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

import pe.com.gym.dto.InscripcionDTO;
import pe.com.gym.entidades.Inscripcion;
import pe.com.gym.entidades.InscripcionPk;


/**
 * Administración de inscripciones de clientes (Matricula)
 * @author Omar Yarleque
 */

@Stateless
public class InscripcionDAO implements InscripcionDAOLocal {
	
	@Resource(name = "jdbc/__default")
	DataSource ds;
	private final static Logger logger = Logger.getLogger(InscripcionDAO.class.getName());
	
	/**
	 * Obtiene inscripcion de un cliente
	 * @param id Objeto del tipo <code>InscripcionPk</code>, codigo de inscripcion
	 * @return Inscripcion retorna la inscripcion del cliente
	 */
	@Override
	public Inscripcion getIncripcion(InscripcionPk id){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String cadSql="";
		Inscripcion inscripcion;
		try {
			cadSql="SELECT  NOMCLI, NOMMOD, NOMSER, HOINRU, HOFIRU, ESTINS, FECREG, USUREG, FECMOD, USUMOD TB_INSCRIPCION WHERE CODCLI = ? and CODMOD = ? and CODSER = ?  and CORREL = ?";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setLong(1,id.getCodcli());
			ps.setInt(2,id.getCodmod());
			ps.setInt(3,id.getCodser());
			ps.setInt(4,id.getCorrel());
			rs = ps.executeQuery();
			if(rs.next()){
				inscripcion = new Inscripcion();
				inscripcion.setId(id);
				inscripcion.setNomcli(rs.getString(1));
				inscripcion.setNommod(rs.getString(2));
				inscripcion.setNomser(rs.getString(3));
				inscripcion.setHoinru(rs.getTime(4));
				inscripcion.setHofiru(rs.getTime(5));
				inscripcion.setEstins(rs.getInt(6));
				inscripcion.setFecreg(rs.getDate(7));
				inscripcion.setUsureg(rs.getString(8));
				inscripcion.setFecmod(rs.getDate(9));
				inscripcion.setUsumod(rs.getString(10));
			}else
				inscripcion = null;
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error en la ejecucion",e);
			inscripcion = null;
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
		return inscripcion;
	}
	
	/**
	 * Guarda inscripcion del cliente
	 * @param ins  Objeto <code>Inscripcion</code> a guardar
	 * @return int   Si esta todo correcto retorna cero
	 */
	@Override
	public int registraInscripcion(Inscripcion ins){
		Connection cn = null;
		PreparedStatement ps = null;
		String cadSql="";
		int res=0;
		try {
			cadSql="INSERT INTO TB_INSCRIPCION (CODCLI,CODMOD,CODSER,CORREL,NOMCLI, NOMMOD, NOMSER, HOINRU, HOFIRU, ESTINS, FECREG, USUREG) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setLong(1,ins.getId().getCodcli());
			ps.setInt(2,ins.getId().getCodmod());
			ps.setInt(3,ins.getId().getCodser());
			ps.setInt(4,ins.getId().getCorrel());
			ps.setString(5, ins.getNomcli());
			ps.setString(6, ins.getNommod());
			ps.setString(7, ins.getNomser());
			ps.setTime(8,ins.getHoinru());
			ps.setTime(9,ins.getHofiru());
			ps.setInt(10,ins.getEstins());
			ps.setDate(11,(Date)ins.getFecreg());
			ps.setString(12, ins.getUsureg());
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
	 * Actualiza Inscripcion 
	 * @param ins Objeto <code>Inscripcion</code> a guardar
	 * @return int   Si esta todo correcto retorna cero
	 */
	@Override
	public int actualizaInscripcion(Inscripcion ins){
		Connection cn = null;
		PreparedStatement ps = null;
		String cadSql="";
		int res=0;
		try {
			cadSql="UPDATE TB_INSCRIPCION SET CODMOD=?,CODSER=?,CORREL=?, NOMMOD=?, NOMSER=?, HOINRU=?, HOFIRU=?, ESTINS=?, FECMOD=?, USUMOD=? WHERE CODCLI = ? and CODMOD = ? and CODSER=? and CORREL = ?";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setInt(1,ins.getId().getCodmod());
			ps.setInt(2,ins.getId().getCodser());
			ps.setInt(3,ins.getId().getCorrel());
			ps.setString(4, ins.getNommod());
			ps.setString(5, ins.getNomser());
			ps.setTime(6,ins.getHoinru());
			ps.setTime(7,ins.getHofiru());
			ps.setInt(8,ins.getEstins());
			ps.setDate(9,(Date)ins.getFecmod());
			ps.setString(10, ins.getUsumod());
			ps.setLong(11,ins.getId().getCodcli());
			ps.setInt(12,ins.getId().getCodmod());
			ps.setInt(13,ins.getId().getCodser());
			ps.setInt(14,ins.getId().getCorrel());
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
	 * Obtiene nuevo correlativo de inscripcion
	 * @return int nuevo correlativo
	 */
	@Override
	public int getCorrelativoIncripcion() {
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		String cadSql="";
		int codigo = 0;
		try {
			cadSql="SELECT MAX(CORREL) FROM TB_INSCRIPCION";
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
	 * Lista inscripciones de los clientes
	 * @param String Coincidencia 
	 * @param int[] limites para la paginación
	 * @return Map contiene las coincidencias.
	 */
	@SuppressWarnings("resource")
	@Override
	public Map<String, Object> listaInscripciones(String valBus,int[] limites){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String cadSql = "";
		int total = 0;
		List<InscripcionDTO> inscripciones = new ArrayList<InscripcionDTO>();
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			//Se obtiene total de registros
			cadSql="SELECT COUNT(*) FROM TB_INSCRIPCIONES WHERE NOMCLI like ? ";
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
			cadSql="SELECT CODCLI,CODMOD,CODSER,CORREL,NOMCLI,NOMMOD,NOMSER,ESTINS FROM TB_INSCRIPCION WHERE NOMCLI like ?) ORDER BY FECREG DESC LIMIT ?,? ";
			ps=cn.prepareStatement(cadSql);
			ps.setString(1, valBus+"%");
			ps.setInt(2, limites[0]);
			ps.setInt(3, limites[1]);
			rs=ps.executeQuery();
			while(rs.next()){
				InscripcionPk id = new InscripcionPk();
				InscripcionDTO inscripcion= new InscripcionDTO();
				id.setCodcli(rs.getLong(1));
				id.setCodmod(rs.getInt(2));
				id.setCodser(rs.getInt(3));
				id.setCorrel(rs.getInt(4));
				inscripcion.setId(id);
				inscripcion.setNomcli(rs.getString(5));
				inscripcion.setNommod(rs.getString(6));
				inscripcion.setNomser(rs.getString(7));
				inscripcion.setEstins(rs.getInt(8));
				inscripciones.add(inscripcion);
			}
			map.put("INSCRIPCIONES", inscripciones);
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
	 * Dar de baja a una inscripcion
	 * @param id  Objeto del tipo<code>InscripcionPK</code>
	 * @return int   Si esta todo correcto retorna cero
	 */
	@Override
	public int cancelarInscripcion(InscripcionPk id,int nvoEstado){
		Connection cn = null;
		PreparedStatement ps = null;
		String cadSql="";
		int res=0;
		try {
			cadSql="UPDATE TB_INSCRIPCION SET ESTINS = ? WHERE CODCLI = ? and CODMOD = ? and CODSER = ? and CORREL = ?";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setInt(1,nvoEstado);
			ps.setLong(2,id.getCodcli());
			ps.setLong(3,id.getCodmod());
			ps.setLong(4,id.getCodser());
			ps.setLong(5,id.getCorrel());
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