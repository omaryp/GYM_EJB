package pe.com.gym.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import pe.com.gym.entidades.Perfil;


/**
 * Registro de asistencia
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
}