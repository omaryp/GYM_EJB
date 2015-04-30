package pe.com.gym.dao;


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

import pe.com.gym.entidades.Termino;


/**
 * Registro de asistencia
 * @author Omar Yarleque
 */

@Stateless
public class TerminoDAO implements TerminoDAOLocal {
	
	@Resource(name = "jdbc/__default")
	DataSource ds;
	private final static Logger logger = Logger.getLogger(TerminoDAO.class.getName());
	
	@Override
	public List<Termino> listaTerminos(){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String cadSql="";
		List<Termino> terminos = new ArrayList<Termino>();
		try {
			cadSql="SELECT CODTER,NOMTER,DESCRIP,ESTTER FROM tb_termino WHERE ESTTER = 0";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			rs=ps.executeQuery();
			while(rs.next()){
				Termino termino = new Termino();
				terminos.add(termino);
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error en la ejecucion",e);
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
		return null;
	}
}