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

import pe.com.gym.dto.EmpleadoDTO;
import pe.com.gym.entidades.Empleado;
import pe.com.gym.util.Estado;

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
	
	@Override
	public Long getCodigoEmpleadoNvo(){
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		String cadSql="";
		Long codigo = null;
		try {
			cadSql="SELECT MAX(CODEMP) FROM tb_empleado";
			cn=ds.getConnection();
			st=cn.createStatement();
			rs=st.executeQuery(cadSql);
			if(rs.next())
				codigo = new Long(rs.getLong(1)+1);
			else 
				codigo = null;
			
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error en la ejecucion",e);
			codigo = new Long(e.getErrorCode());
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
	public int actualizarEmpleado(Empleado emp){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuilder cadSql= new StringBuilder();
		int res = 0;
		try {
			cadSql.append("UPDATE tb_empleado ");
			cadSql.append("SET NOMEMP = ?, APEEMP = ?, DNIEMP = ?,");
			cadSql.append("DIREMP = ?, USUMOD = ?,");
			cadSql.append("FECMOD = ?, FECNAC = ?  ");
			cadSql.append("WHERE CODEMP = ?");
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql.toString());
			ps.setString(1,emp.getNomemp());
			ps.setString(2,emp.getApeemp());
			ps.setString(3,emp.getDniemp());
			ps.setString(5,emp.getDiremp());
			ps.setString(6,emp.getUsumod());
			ps.setDate(11,new Date(emp.getFecmod().getTime()));
			ps.setDate(12,new Date(emp.getFecnac().getTime()));
			ps.setLong(13,emp.getCodemp());
			
			res = (ps.executeUpdate()!=0)?0:1;
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error en la ejecucion",e);
			res = e.getErrorCode();
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
		return res;
	}
	
	@Override
	public int darBajaEmpleado(long codEmp){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuilder cadSql= new StringBuilder();
		int res = 0;
		try {
			cadSql.append("UPDATE tb_empleado ");
			cadSql.append("SET ESTEMP = ?,");
			cadSql.append("SET FECCES = ? ");
			cadSql.append("WHERE CODEMP = ?");
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql.toString());
			ps.setInt(1,1);
			ps.setLong(2,codEmp);
			ps.setDate(12,new Date(new java.util.Date().getTime()));
			res = (ps.executeUpdate()!=0)?0:1;
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error en la ejecucion",e);
			res = e.getErrorCode();
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
		return res;
	}
	
	@Override
	public Map<String, Object> listaEmpleados(String valBus,int[] limites){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String cadSql="";
		int total = 0;
		List<EmpleadoDTO> empleados = new ArrayList<EmpleadoDTO>();
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			total = cantidadRegistros(valBus, limites);
			map.put("TOTAL", total);
			//Se obtiene los registros
			cadSql="SELECT CODEMP, NOMEMP, APEEMP, DIREMP, DNIEMP, USUEMP FROM tb_empleado WHERE ESTEMP = ? and APEEMP like ? ORDER BY CODEMP DESC LIMIT ?,? ";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setInt(1, Estado.ACTIVO.getValue());
			ps.setString(2, (valBus==null)?"%":valBus.trim()+"%");
			ps.setInt(3, limites[0]);
			ps.setInt(4, limites[1]);
			rs=ps.executeQuery();
			while(rs.next()){
				EmpleadoDTO emp = new EmpleadoDTO();
				emp.setCodemp(rs.getInt("CODEMP"));
				emp.setNomemp(rs.getString("NOMEMP"));
				emp.setApeemp(rs.getString("APEEMP"));
				emp.setUsuemp(rs.getString("USUEMP"));
				emp.setDniemp(rs.getString("DNIEMP"));
				emp.setDiremp(rs.getString("DIREMP"));
				empleados.add(emp);
			}
			map.put("EMPLEADOS", empleados);
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
			cadSql="SELECT COUNT(*) FROM tb_empleado WHERE ESTEMP = ? and APEEMP like ? ";
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
	
	@Override
	public int guardarEmpleado(Empleado emp) {
		Connection cn = null;
		PreparedStatement ps = null;
		String cadSql="";
		int res=0;
		try {
			cadSql="INSERT INTO tb_empleado(CODEMP,NOMEMP,APEEMP,DNIEMP,DIREMP,USUEMP,ESTEMP,USUREG,FECREG,FECNAC) VALUES (?,?,?,?,?,?,?,?,?,?)";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setLong(1, emp.getCodemp());
			ps.setString(2, emp.getNomemp());
			ps.setString(3, emp.getApeemp());
			ps.setString(4, emp.getDniemp());
			ps.setString(5, emp.getDiremp());
			ps.setString(6, emp.getUsuemp());
			ps.setInt(7, emp.getEstemp());
			ps.setString(8, emp.getUsureg());
			ps.setDate(9,new Date(emp.getFecreg().getTime()));
			ps.setDate(10,new Date(emp.getFecnac().getTime()));
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