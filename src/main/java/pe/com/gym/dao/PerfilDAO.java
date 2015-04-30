package pe.com.gym.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import pe.com.gym.entidades.Perfil;
import pe.com.gym.util.Estado;


/**
 * Perfiles
 * @author Omar Yarleque
 */

@Stateless
public class PerfilDAO implements PerfilDAOLocal {
	
	@Resource(name = "jdbc/__default")
	DataSource ds;
	private final static Logger logger = Logger.getLogger(PerfilDAO.class.getName());
	

	@Override
	public Perfil getPerfil(int codEmp){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs ;
		Perfil perfil = null;
		String cadSql="";
		try {
			cadSql="SELECT A.CODPER,A.NOMPER,A.DESPER,A.USUREG,A.FECREG,A.ESTPER from tb_perfil A inner join tb_empleado_perfil B on A.CODPER = B.CODPER where B.CODEMP = ?";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setInt(1,codEmp);
			rs = ps.executeQuery();
			if(rs.next()){
				perfil = new Perfil();
				perfil.setCodper(rs.getInt(1));
				perfil.setNomper(rs.getString(2));
				perfil.setDesper(rs.getString(3));
				perfil.setUsureg(rs.getString(4));
				perfil.setFecreg(rs.getDate(5));
				perfil.setEstper(rs.getInt(6));
			}else perfil = null;
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error en la ejecucion",e);
			perfil = null;
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
		return perfil;
	}
	
	@Override
	public List<Perfil> getPerfiles(){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs ;
		String cadSql="";
		List<Perfil> perfiles = new ArrayList<Perfil>();
		try {
			cadSql="SELECT CODPER,NOMPER,DESPER,USUREG,FECREG,ESTPER from tb_perfil where ESTPER = 0";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			rs = ps.executeQuery();
			while(rs.next()){
				Perfil perfil = new Perfil();
				perfil.setCodper(rs.getInt(1));
				perfil.setNomper(rs.getString(2));
				perfil.setDesper(rs.getString(3));
				perfil.setUsureg(rs.getString(4));
				perfil.setFecreg(rs.getDate(5));
				perfil.setEstper(rs.getInt(6));
				perfiles.add(perfil);
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error en la ejecucion",e);
			perfiles = null;
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
		return perfiles;
	}
	
	
	@Override
	public int asociarPerfil(int cod_empleado,int perfil) {
		Connection cn = null;
		PreparedStatement ps = null;
		String cadSql="";
		int res=0;
		try {
			cadSql="INSERT INTO tb_empleado_perfil(CODEMP,CODPER) VALUES (?,?)";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setInt(1, cod_empleado);
			ps.setInt(2, perfil);
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
	
	@Override
	public int getCodigoPerfilNvo(){
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		String cadSql="";
		int codigo = 0;
		try {
			cadSql="SELECT MAX(CODPER) FROM tb_perfil";
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
	
	@Override
	public int registraPerfil(Perfil perfil){
		Connection cn = null;
		PreparedStatement ps = null;
		String cadSql="";
		int res=0;
		try {
			cadSql="INSERT INTO tb_perfil(CODPER,NOMPER,DESPER,USUREG,FECREG,ESTPER) VALUES(?,?,?,?,?,?)";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setInt(1,perfil.getCodper());
			ps.setString(2,perfil.getNomper());
			ps.setString(3,perfil.getDesper());
			ps.setString(4,perfil.getUsureg());
			ps.setDate(5,new Date(perfil.getFecreg().getTime()));
			ps.setInt(6,perfil.getEstper());
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
	
	@Override
	public int actualizaPerfil(Perfil perfil){
		Connection cn = null;
		PreparedStatement ps = null;
		String cadSql="";
		int res=0;
		try {
			cadSql="UPDATE tb_perfil SET NOMPER=?, DESPER=?, USUMOD = ?, FECMOD=? WHERE CODPER=? ";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps=cn.prepareStatement(cadSql);
			ps.setString(1,perfil.getNomper());
			ps.setString(2,perfil.getDesper());
			ps.setString(3,perfil.getUsumod());
			ps.setDate(4,(Date)perfil.getFecmod());
			ps.setInt(5,perfil.getCodper());
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
	
	@Override
	public Map<String, Object> listaPerfiles(String valBus,int[] limites){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String cadSql="";
		int total = 0;
		List<Perfil> perfiles = new ArrayList<Perfil>();
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			total = cantidadRegistros(valBus, limites);
			map.put("TOTAL", total);
			//Se obtiene los registros
			cadSql="SELECT CODPER,NOMPER,DESPER FROM tb_perfil WHERE ESTPER = ? and NOMPER like ? ORDER BY CODPER DESC LIMIT ?,? ";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setInt(1, Estado.ACTIVO.getValue());
			ps.setString(2,(valBus==null)?"%":valBus.trim()+"%");
			ps.setInt(3, limites[0]);
			ps.setInt(4, limites[1]);
			rs=ps.executeQuery();
			while(rs.next()){
				Perfil per = new Perfil();
				per.setCodper(rs.getInt("CODPER"));
				per.setNomper(rs.getString("NOMPER"));
				per.setDesper(rs.getString("DESPER"));
				perfiles.add(per);
			}
			map.put("PERFILES", perfiles);
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
	
	public int cantidadRegistros(String valBus,int[] limites){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String cadSql="";
		int total = 0;
		try {
			//Se obtiene total de registros
			cadSql="SELECT COUNT(*) FROM tb_perfil WHERE ESTPER = ? and NOMPER like ? ";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setInt(1, Estado.ACTIVO.getValue());
			ps.setString(2, (valBus==null)?"%":valBus.trim()+"%");
			rs=ps.executeQuery();
			if(rs.next())
				total = rs.getInt(1);
			else
				total = 0;
			
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error en la ejecucion",e);
			total = 0;
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
		return total;
	}
	
}