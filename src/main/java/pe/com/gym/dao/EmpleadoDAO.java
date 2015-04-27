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

import pe.com.gym.entidades.Empleado;

/**
 * Registro de asistencia
 * @author Omar Yarleque
 */

@Stateless
public class EmpleadoDAO implements EmpleadoDAOLocal {
	
	@Resource(name = "jdbc/__default")
	DataSource ds;
	private final static Logger logger = Logger.getLogger(EmpleadoDAO.class.getName());
	
	@Override
	public Empleado getEmpleado(int codigoEmpleado){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs ;
		Empleado empleado = new Empleado();
		String cadSql="";
		try {
			cadSql="SELECT CODEMP,NOMEMP,APEEMP,DNIEMP,DIREMP,USUEMP FROM TB_EMPLEADO WHERE CODEMP = ? and ESTEMP = 0";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setInt(1,codigoEmpleado);
			rs = ps.executeQuery();
			if(rs.next()){
				empleado.setCodemp(rs.getInt(1));
				empleado.setNomemp(rs.getString(2));
				empleado.setApeemp(rs.getString(3));
				empleado.setDniemp(rs.getString(4));
				empleado.setDiremp(rs.getString(5));
				empleado.setUsuemp(rs.getString(6));
			}else empleado = null;
				
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error en la ejecucion",e);
			empleado = null;
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
		return empleado;
	}
}