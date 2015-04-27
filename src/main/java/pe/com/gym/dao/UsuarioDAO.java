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

import pe.com.gym.entidades.Usuario;
import pe.com.gym.entidades.UsuarioPK;

/**
 * Registro de usuarios
 * @author Omar Yarleque
 */

@Stateless
public class UsuarioDAO implements UsuarioDAOLocal {
	
	@Resource(name = "jdbc/__default")
	DataSource ds;
	private final static Logger logger = Logger.getLogger(UsuarioDAO.class.getName());
	
	@Override
	public int registraUsuario(Usuario usu){
		Connection cn = null;
		PreparedStatement ps = null;
		String cadSql="";
		int res=0;
		try {
			cadSql="INSERT INTO TB_USUARIO(CODEMP,CORREL,USUEMP,PASEMP,FECREG,USUREG) VALUES(?,?,?,?,?,?,?)";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setInt(1,usu.getId().getCodemp());
			ps.setInt(2,usu.getId().getCorrel());
			ps.setString(3,usu.getUsuemp());
			ps.setString(4,usu.getPasemp());
			ps.setDate(5,(Date)usu.getFecreg());
			ps.setString(6,usu.getUsureg());
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
	public int actualizaUsuario(Usuario usu){
		Connection cn = null;
		PreparedStatement ps = null;
		String cadSql="";
		int res=0;
		try {
			cadSql="UPDATE TB_USUARIO SET USUEMP=? ,PASEMP=?  WHERE CODEMP=? and CORREL =? ";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setString(1,usu.getUsuemp());
			ps.setString(2,usu.getPasemp());
			ps.setInt(3,usu.getId().getCodemp());
			ps.setInt(4,usu.getId().getCorrel());
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
	public boolean validaUsuario(String usu,String pass){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs ;
		String cadSql="";
		try {
			cadSql="SELECT USUEMP FROM TB_USUARIO WHERE USUEMP = ? and PASEMP = ? and ESTUSR = 0";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setString(1,usu);
			ps.setString(2,pass);
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
	
	@Override
	public Usuario getUsuario(int codemp){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs ;
		String cadSql="";
		UsuarioPK userPk = null;
		Usuario user = null;
		try {
			cadSql="SELECT CODEMP,CORREL,USUEMP,PASEMP,FECREG,USUREG FROM TB_USUARIO WHERE CODEMP = ?";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setInt(1,codemp);
			rs = ps.executeQuery();
			if(rs.next()){
				userPk = new UsuarioPK();
				userPk.setCodemp(rs.getInt(1));
				userPk.setCorrel(rs.getInt(2));
				user = new Usuario();
				user.setId(userPk);
				user.setUsuemp(rs.getString(3));
				user.setPasemp(rs.getString(4));
				user.setFecreg(rs.getDate(5));
				user.setUsureg(rs.getString(6));
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error en la ejecucion",e);
			user = null;
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
		return user;
	}
	
	@Override
	public Usuario getUsuario(String usuario){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs ;
		String cadSql="";
		UsuarioPK userPk = null;
		Usuario user = null;
		try {
			cadSql="SELECT CODEMP,CORREL,USUEMP,PASEMP,FECREG,USUREG FROM TB_USUARIO WHERE USUEMP = ?";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setString(1,usuario);
			rs = ps.executeQuery();
			if(rs.next()){
				userPk = new UsuarioPK();
				userPk.setCodemp(rs.getInt(1));
				userPk.setCorrel(rs.getInt(2));
				user = new Usuario();
				user.setId(userPk);
				user.setUsuemp(rs.getString(3));
				user.setPasemp(rs.getString(4));
				user.setFecreg(rs.getDate(5));
				user.setUsureg(rs.getString(6));
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error en la ejecucion",e);
			user = null;
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
		return user;
	}
	
	@Override
	public int eliminaUsuario(int codEmp){
		Connection cn = null;
		PreparedStatement ps = null;
		String cadSql="";
		int res=0;
		try {
			cadSql="UPDATE TB_USUARIO SET ESTUSR = 1  WHERE CODEMP = ?";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setInt(1,codEmp);
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