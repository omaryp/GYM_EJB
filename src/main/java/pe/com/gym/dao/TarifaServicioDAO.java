package pe.com.gym.dao;

import java.sql.Connection;
import java.sql.Date;
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

import pe.com.gym.entidades.Servicio;
import pe.com.gym.entidades.TarifaServicio;
import pe.com.gym.entidades.TarifaServicioPK;
import pe.com.gym.util.Estado;

/**
 * Registro de asistencia
 * @author Omar Yarleque
 */

@Stateless
public class TarifaServicioDAO implements TarifaServicioDAOLocal {
	
	@Resource(name = "jdbc/__default")
	DataSource ds;
	private final static Logger logger = Logger.getLogger(TarifaServicioDAO.class.getName());
	
	@Override
	public TarifaServicio getTarifa(TarifaServicioPK id){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs ;
		TarifaServicio tarifa = new TarifaServicio();
		String cadSql="";
		try {
			cadSql="SELECT MONTO,ESTADO,FECREG,USUREG,(Select A.NOMMOD from tb_modalidad_pago A where A.CODMOD = CODMOD) NOMMOD FROM tb_tarifa_servicio WHERE CODSER = ? and CODMOD = ? and CORREL = ?";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setInt(1,id.getCodser());
			ps.setInt(2,id.getCodmod());
			ps.setInt(3,id.getCorrel());
			rs = ps.executeQuery();
			if(rs.next()){
				tarifa.setId(id);
				tarifa.setMonto(rs.getDouble("MONTO"));
				tarifa.setEstado(rs.getInt("ESTADO"));
				tarifa.setFecreg(rs.getDate("FECREG"));
				tarifa.setUsureg(rs.getString("USUREG"));
				tarifa.setDesmon(rs.getString("NOMMOD"));
			}else tarifa = null;
				
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error en la ejecucion",e);
			tarifa = null;
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
		return tarifa;
	}
	
	@Override
	public TarifaServicioPK getCodigoTarifaNva(int codser, int codmod){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String cadSql= "";
		TarifaServicioPK id = null;
		try {
			cadSql = "Select MAX(CORREL) from tb_tarifa_servicio where CODSER = ? and CODMOD = ?";
			cn=ds.getConnection();
			ps=cn.prepareCall(cadSql);
			ps.setInt(1,codser);
			ps.setInt(2,codmod);
			rs = ps.executeQuery();			
			if(rs.next()){
				id = new TarifaServicioPK();
				id.setCodser(codser);
				id.setCodmod(codmod);
				id.setCorrel(rs.getInt(1)+1);
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error en la ejecucion",e);
			id = null;
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
		return id;
	}
	
	@Override
	public int darBajaTarifa(TarifaServicioPK id){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuilder cadSql= new StringBuilder();
		int res = 0;
		try {
			cadSql.append("UPDATE tb_tarifa_servicio ");
			cadSql.append("SET ESTADO = ?,");
			cadSql.append("WHERE CODSER = ? and CODMOD = ? and CORREL = ?");
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql.toString());
			ps.setInt(1, Estado.DESACTIVADO.getValue());
			ps.setInt(2, id.getCodser());
			ps.setInt(3, id.getCodmod());
			ps.setInt(4, id.getCorrel());
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
	public List<TarifaServicio> listaTarifas(Servicio servicio){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String cadSql="";
		List<TarifaServicio> tarifas = new ArrayList<TarifaServicio>();
		try {
			cadSql="SELECT CODSER, CODMOD, CORREL, MONTO, ESTADO, FECREG, USUREG FROM tb_tarifa_servicio WHERE CODSER = ? AND ESTADO = ? ";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setInt(1, servicio.getCodser());
			ps.setInt(2, Estado.ACTIVO.getValue());
			rs=ps.executeQuery();
			while(rs.next()){
				TarifaServicio tarifa = new TarifaServicio();
				TarifaServicioPK id = new TarifaServicioPK();
				id.setCodser(rs.getInt("CODSER"));
				id.setCodmod(rs.getInt("CODMOD"));
				id.setCorrel(rs.getInt("CORREL"));
				tarifa.setId(id);
				tarifa.setMonto(rs.getDouble("MONTO"));
				tarifa.setEstado(rs.getInt("ESTADO"));
				tarifa.setFecreg(rs.getDate("FECREG"));
				tarifa.setUsureg(rs.getString("USUREG"));
				tarifas.add(tarifa);
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error en la ejecucion",e);
			tarifas = null;
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
		return tarifas;
	}
	
	@Override
	public int registraTarifa(TarifaServicio tarifa) {
		Connection cn = null;
		PreparedStatement ps = null;
		String cadSql="";
		int res=0;
		try {
			cadSql="INSERT INTO tb_tarifa_servicio(CODSER,CODMOD,CORREL,MONTO,ESTADO,FECREG,USUREG) VALUES (?,?,?,?,?,?,?)";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setInt(1,tarifa.getId().getCodser());
			ps.setInt(2,tarifa.getId().getCodmod());
			ps.setInt(3,tarifa.getId().getCorrel());
			ps.setDouble(4,tarifa.getMonto());
			ps.setInt(5,tarifa.getEstado());
			ps.setDate(6,new Date(tarifa.getFecreg().getTime()));
			ps.setString(7, tarifa.getUsureg());
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