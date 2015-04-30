package pe.com.gym.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
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

import pe.com.gym.entidades.Menu;
import pe.com.gym.entidades.Perfil;
import pe.com.gym.util.Estado;


/**
 * Menus
 * @author Omar Yarleque
 */

@Stateless
public class MenuDAO implements MenuDAOLocal {
	
	@Resource(name = "jdbc/__default")
	DataSource ds;
	private final static Logger logger = Logger.getLogger(MenuDAO.class.getName());
	
	@Override
	public List<Menu> getMenus(int codEmp){
		Connection cn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		List<Menu> menus = null;
		String cadSql="";
		try {
			cadSql="CALL menu_empleado (?)";
			cn=ds.getConnection();
			cs=cn.prepareCall(cadSql);
			cs.setInt(1,codEmp);
			rs = cs.executeQuery();
			menus = new ArrayList<Menu>();
			while(rs.next()){
				Menu menu = new Menu();
				menu.setCodmen(rs.getInt(1));
				menu.setNommen(rs.getString(2));
				menu.setRutmen(rs.getString(3));
				menu.setDesmen(rs.getString(4));
				menu.setUsureg(rs.getString(5));
				menu.setFecreg(rs.getDate(6));
				menus.add(menu);
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error en la ejecucion",e);
			menus = null;
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
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "No se pudo liberar el recurso");
            }
		}
		return menus;
	}	
	

	@Override
	public List<Menu> getMenus(){
		Connection cn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		List<Menu> menus = null;
		String cadSql="";
		try {
			cadSql="SELECT CODMEN,NOMMEN,RUTMEN,DESMEN,USUREG,FECREG,ESTMEN FROM tb_menu WHERE ESTMEN = ?";
			cn=ds.getConnection();
			cs=cn.prepareCall(cadSql);
			cs.setInt(1,Estado.ACTIVO.getValue());
			rs = cs.executeQuery();
			menus = new ArrayList<Menu>();
			while(rs.next()){
				Menu menu = new Menu();
				menu.setCodmen(rs.getInt(1));
				menu.setNommen(rs.getString(2));
				menu.setRutmen(rs.getString(3));
				menu.setDesmen(rs.getString(4));
				menu.setUsureg(rs.getString(5));
				menu.setFecreg(rs.getDate(6));
				menu.setEstmen(rs.getInt(7));
				menus.add(menu);
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error en la ejecucion",e);
			menus = null;
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
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "No se pudo liberar el recurso");
            }
		}
		return menus;
	}
	
	@Override
	public int asociarPerfil(Perfil per,int menu){
		Connection cn = null;
		PreparedStatement ps = null;
		int res = 0;
		String cadSql="";
		try {
			cadSql="INSERT INTO tb_perfil_menu(CODPER,CODMEN) value(?,?) ";
			cn=ds.getConnection();
			ps=cn.prepareCall(cadSql);
			ps.setInt(1,per.getCodper());
			ps.setInt(2,menu);
			ps.execute();
			res = ps.getUpdateCount()!=0?0:1;
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error en la ejecucion",e);
			res = e.getErrorCode();
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