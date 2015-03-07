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

import pe.com.gym.dto.ClienteDTO;
import pe.com.gym.entidades_ant.Cliente;

/**
 * Acceso a datos de Clientes
 * @author Omar Yarleque
 */

@Stateless
public class ClienteDAO implements ClienteDAOLocal {
	
	@Resource(name = "jdbc/__default")
	DataSource ds;
	private final static Logger logger = Logger.getLogger(ClienteDAO.class.getName());
	
	/**
	 * Guarda nuevo cliente persona jurídica
	 * @param cli
	 * @return int
	 */
	@Override
	public int guardarClientePJ(Cliente cli) {
		Connection cn = null;
		PreparedStatement ps = null;
		String cadSql="";
		int res=0;
		try {
			cadSql="INSERT INTO TB_CLIENTE(CODCLI, TELCLI, DIRCLI, TIPPER, RAZSOC, RUCCLI, USUREG, REPLEG, DNIREPL,FECREG)  "+
					"VALUES (?,?,?,?,?,?,?,?,?,?)";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setLong(1, cli.getCodigoCliente());
			ps.setString(2, cli.getTelefonoCliente());
			ps.setString(3, cli.getDireccionCliente());
			ps.setString(4, cli.getTipoPersona());
			ps.setString(5, cli.getRazonSocial());
			ps.setString(6, cli.getRucCliente());
			ps.setString(7, cli.getUsuarioRegistro());
			ps.setString(8, cli.getRepLegal());
			ps.setString(9, cli.getDniRepLegal());
			ps.setDate(10,(Date) cli.getFechaRegistro());
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
	 * Guarda nuevo cliente persona natural
	 * @param cli
	 * @return int
	 */
	@Override
	public int guardarClientePN(Cliente cli) {
		Connection cn = null;
		PreparedStatement ps = null;
		String cadSql="";
		int res=0;
		try {
			cadSql="INSERT INTO TB_CLIENTE(CODCLI, NOMCLI, APECLI, DNICLI, TELCLI, DIRCLI, TIPPER, HOINRU, HOFIRU, USUREG, CLIEMP, RUTFOT,FECREG)  "+
					"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setLong(1, cli.getCodigoCliente());
			ps.setString(2, cli.getNombreCliente());
			ps.setString(3, cli.getApellidoCliente());
			ps.setString(4, cli.getDni());
			ps.setString(5, cli.getTelefonoCliente());
			ps.setString(6, cli.getDireccionCliente());
			ps.setString(7, cli.getTipoPersona());
			ps.setTime(8, cli.getHoraInicioRutina());
			ps.setTime(9, cli.getHoraFinRutina());
			ps.setString(10, cli.getUsuarioRegistro());
			ps.setLong(11, cli.getClienteEmpresa());
			ps.setString(12, cli.getRutaFoto());
			ps.setDate(13,(Date) cli.getFechaRegistro());
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
	 * @param sin parametros
	 * Obtiene código para nuevo cliente
	 * @return Long
	 */
	@Override
	public long getCodigoClienteNvo() {
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		String cadSql="";
		Long codigo = null;
		try {
			cadSql="SELECT MAX(CODCLI) FROM TB_CLIENTE";
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
	
	@SuppressWarnings("resource")
	@Override
	public Map<String, Object> listaClientesPN(String valBus,int[] limites){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String cadSql = "";
		int total = 0;
		List<ClienteDTO> clientes = new ArrayList<ClienteDTO>();
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			//Se obtiene total de registros
			cadSql="SELECT COUNT(*) FROM TB_CLIENTE WHERE TIPPER =? and (NOMCLI like ? or APECLI like ?)";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setString(1, "N");
			ps.setString(2, valBus+"%");
			ps.setString(3, valBus+"%");
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
			cadSql="SELECT CODCLI, NOMCLI, APECLI, DNICLI, DIRCLI  FROM TB_CLIENTE WHERE TIPPER=? and (NOMCLI like ? or APECLI like ?) ORDER BY CODCLI DESC LIMIT ?,? ";
			ps=cn.prepareStatement(cadSql);
			ps.setString(1, "N");
			ps.setString(2, valBus+"%");
			ps.setString(3, valBus+"%");
			ps.setInt(4, limites[0]);
			ps.setInt(5, limites[1]);
			rs=ps.executeQuery();
			while(rs.next()){
				ClienteDTO clie = new ClienteDTO();
				clie.setCodigoCliente(rs.getLong(1));
				clie.setNombreCliente(rs.getString(2));
				clie.setApellidoCliente(rs.getString(3));
				clie.setDni(rs.getString(4));
				clie.setDireccionCliente(rs.getString(5));
				clientes.add(clie);
			}
			map.put("CLIENTES", clientes);
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
	
	@SuppressWarnings("resource")
	@Override
	public Map<String, Object> listaClientesPJ(String valBus,int[] limites){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String cadSql="";
		int total = 0;
		List<ClienteDTO> clientes = new ArrayList<ClienteDTO>();
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			//Se obtiene total de registros
			cadSql="SELECT COUNT(*) FROM TB_CLIENTE WHERE TIPPER=? and RAZSOC like ? ";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setString(1, "J");
			ps.setString(2, valBus+"%");
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
			//Se obtiene los registros
			cadSql="SELECT CODCLI, RAZSOC, RUCCLI, DIRCLI FROM TB_CLIENTE WHERE TIPPER = ? and RAZSOC like ? ORDER BY CODCLI DESC LIMIT ?,? ";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setString(1, "J");
			ps.setString(2, valBus+"%");
			ps.setInt(3, limites[0]);
			ps.setInt(4, limites[1]);
			rs=ps.executeQuery();
			while(rs.next()){
				ClienteDTO clie = new ClienteDTO();
				clie.setCodigoCliente(rs.getLong(1));
				clie.setRazonSocial(rs.getString(2));
				clie.setRucCliente(rs.getString(3));
				clie.setDireccionCliente(rs.getString(4));
				clientes.add(clie);
			}
			map.put("CLIENTES", clientes);
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
	
	@Override
	public List<ClienteDTO> listaClientesPJ(){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String cadSql="";
		List<ClienteDTO> clientes = new ArrayList<ClienteDTO>();
		try {
			cadSql="SELECT CODCLI, RAZSOC, RUCCLI, DIRCLI FROM TB_CLIENTE WHERE TIPPER = ?";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setString(1, "J");
			rs=ps.executeQuery();
			while(rs.next()){
				ClienteDTO clie = new ClienteDTO();
				clie.setCodigoCliente(rs.getLong(1));
				clie.setRazonSocial(rs.getString(2));
				clie.setRucCliente(rs.getString(3));
				clie.setDireccionCliente(rs.getString(4));
				clientes.add(clie);
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error en la ejecucion",e);
			clientes = null;
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
		return clientes;
	}
	
	@Override
	public Cliente obtenerClientePN(long codcli){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuilder cadSql= new StringBuilder();
		Cliente cli = null;
		try {
			cadSql.append("SELECT ");
			cadSql.append("CODCLI,NOMCLI,APECLI,DNICLI,");
			cadSql.append("TELCLI,DIRCLI,TIPPER,");
			cadSql.append("HOINRU,HOFIRU,CLIEMP,RUTFOT ");
			cadSql.append(" FROM TB_CLIENTE WHERE CODCLI = ?");
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql.toString());
			ps.setLong(1, codcli);
			rs=ps.executeQuery();
			if (rs.next()){
				cli = new Cliente();
				cli.setCodigoCliente(rs.getLong(1));
				cli.setNombreCliente(rs.getString(2));
				cli.setApellidoCliente(rs.getString(3));
				cli.setDni(rs.getString(4));
				cli.setTelefonoCliente(rs.getString(5));
				cli.setDireccionCliente(rs.getString(6));
				cli.setTipoPersona(rs.getString(7));
				cli.setHoraInicioRutina(rs.getTime(8));
				cli.setHoraFinRutina(rs.getTime(9));
				cli.setClienteEmpresa(rs.getLong(10));
				cli.setRutaFoto(rs.getString(11));
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error en la ejecucion",e);
			cli = null;
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
		return cli;
	}
	
	@Override
	public Cliente obtenerClientePJ(long codcli){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuilder cadSql= new StringBuilder();
		Cliente cli = null;
		try {
			cadSql.append("SELECT ");
			cadSql.append("CODCLI,TELCLI,DIRCLI,TIPPER,");
			cadSql.append("RAZSOC,RUCCLI,REPLEG,DNIREPL");
			cadSql.append(" FROM TB_CLIENTE WHERE CODCLI = ?");
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql.toString());
			ps.setLong(1, codcli);
			rs=ps.executeQuery();
			
			if (rs.next()){
				cli = new Cliente();
				cli.setCodigoCliente(rs.getLong(1));
				cli.setTelefonoCliente(rs.getString(2));
				cli.setDireccionCliente(rs.getString(3));
				cli.setTipoPersona(rs.getString(4));
				cli.setRazonSocial(rs.getString(5));
				cli.setRucCliente(rs.getString(6));
				cli.setRepLegal(rs.getString(7));
				cli.setDniRepLegal(rs.getString(8));
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error en la ejecucion",e);
			cli = null;
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
		return cli;
	}
	
	@Override
	public int actualizarClientePN(Cliente cli){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuilder cadSql= new StringBuilder();
		int res = 0;
		try {
			cadSql.append("UPDATE TB_CLIENTE ");
			cadSql.append("SET NOMCLI = ?, APECLI = ?, DNICLI = ?,");
			cadSql.append("TELCLI = ?, DIRCLI = ?, TIPPER = ?,");
			cadSql.append("HOINRU = ?, HOFIRU = ?, CLIEMP = ?, RUTFOT = ?, ");
			cadSql.append("USUMOD = ?, FECMOD = ? ");
			cadSql.append("WHERE CODCLI = ?");
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql.toString());
			ps.setString(1,cli.getNombreCliente());
			ps.setString(2,cli.getApellidoCliente());
			ps.setString(3,cli.getDni());
			ps.setString(4,cli.getTelefonoCliente());
			ps.setString(5,cli.getDireccionCliente());
			ps.setString(6,cli.getTipoPersona());
			ps.setTime(7,cli.getHoraInicioRutina());
			ps.setTime(8,cli.getHoraFinRutina());
			ps.setLong(9,cli.getClienteEmpresa());
			ps.setString(10,cli.getRutaFoto());
			ps.setString(11,cli.getUsuarioModificacion());
			ps.setDate(12,(Date)cli.getFechaModificacion());
			ps.setLong(13,cli.getCodigoCliente());
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
	public int actualizarClientePJ(Cliente cli){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuilder cadSql= new StringBuilder();
		int res = 0;
		try {
			cadSql.append("UPDATE TB_CLIENTE ");
			cadSql.append("TELCLI = ?, DIRCLI = ?, RAZSOC = ?, RUCCLI = ?,");
			cadSql.append("REPLEG = ?, DNIREPL = ?,USUMOD = ?, FECMOD = ?  ");
			cadSql.append("WHERE CODCLI = ?");
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql.toString());
			ps.setString(1,cli.getTelefonoCliente());
			ps.setString(2,cli.getDireccionCliente());
			ps.setString(3,cli.getRazonSocial());
			ps.setString(4,cli.getRucCliente());
			ps.setString(5,cli.getRepLegal());
			ps.setString(6,cli.getDniRepLegal());
			ps.setString(7,cli.getUsuarioModificacion());
			ps.setDate(8,(Date)cli.getFechaModificacion());
			ps.setLong(9,cli.getCodigoCliente());
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
	public int eliminarCliente(long codCli){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuilder cadSql= new StringBuilder();
		int res = 0;
		try {
			cadSql.append("UPDATE TB_CLIENTE ");
			cadSql.append("SET ESTCLI = ? ");
			cadSql.append("WHERE CODCLI = ?");
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql.toString());
			ps.setInt(1,1);
			ps.setLong(2,codCli);
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
	public ClienteDTO obtenerClienteDNI(String dni) {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuilder cadSql= new StringBuilder();
		ClienteDTO cli = null;
		try {
			cadSql.append("SELECT ");
			cadSql.append("CODCLI,NOMCLI,APECLI,DNICLI,DIRCLI ");
			cadSql.append("FROM TB_CLIENTE WHERE DNICLI = ?");
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql.toString());
			ps.setString(1, dni);
			rs=ps.executeQuery();
			if (rs.next()){
				cli = new ClienteDTO();
				cli.setCodigoCliente(rs.getLong(1));
				cli.setNombreCliente(rs.getString(2));
				cli.setApellidoCliente(rs.getString(3));
				cli.setDni(rs.getString(4));
				cli.setDireccionCliente(rs.getString(5));
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error en la ejecucion",e);
			cli = null;
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
		return cli;
	}
	
}