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
import pe.com.gym.entidades.Cliente;

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
			cadSql="INSERT INTO TB_CLIENTE(CODCLI, TELEF1, DIRCLI, TIPPER, RAZSOC, RUCCLI, USUREG, REPLEG, DNIREPL,FECREG)  "+
					"VALUES (?,?,?,?,?,?,?,?,?,?)";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setLong(1, cli.getCodcli());
			ps.setString(2, cli.getTelef1());
			ps.setString(3, cli.getDircli());
			ps.setString(4, cli.getTipper());
			ps.setString(5, cli.getRazsoc());
			ps.setString(6, cli.getRuccli());
			ps.setString(7, cli.getUsureg());
			ps.setString(8, cli.getRepleg());
			ps.setString(9, cli.getDnirepl());
			ps.setDate(10,(Date) cli.getFecreg());
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
			cadSql="INSERT INTO TB_CLIENTE(CODCLI, NOMCLI, APECLI, DNICLI, TELEF1, TELEF2, DIRCLI, TIPPER, USUREG, CLIEMP, RUTFOT,FECREG,FECNAC)  "+
					"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setLong(1, cli.getCodcli());
			ps.setString(2, cli.getNomcli());
			ps.setString(3, cli.getApecli());
			ps.setString(4, cli.getDnicli());
			ps.setString(5, cli.getTelef1());
			ps.setString(6, cli.getTelef2());
			ps.setString(7, cli.getDircli());
			ps.setString(8, cli.getTipper());
			ps.setString(9, cli.getUsureg());
			ps.setLong(10, cli.getCliemp());
			ps.setString(11, cli.getRutfot());
			ps.setDate(12,new Date(cli.getFecreg().getTime()));
			ps.setDate(13,new Date(cli.getFecnac().getTime()));
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
			cadSql="SELECT COUNT(*) FROM TB_CLIENTE WHERE TIPPER ='N' and (NOMCLI like ? or APECLI like ?)";
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql);
			ps.setString(1, valBus+"%");
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
			cadSql="SELECT COUNT(*) FROM TB_CLIENTE WHERE TIPPER='J' and RAZSOC like ? ";
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
			cadSql.append("TELEF1,TELEF2,DIRCLI,TIPPER,");
			cadSql.append("CLIEMP,RUTFOT,FECNAC ");
			cadSql.append(" FROM TB_CLIENTE WHERE CODCLI = ?");
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql.toString());
			ps.setLong(1, codcli);
			rs=ps.executeQuery();
			if (rs.next()){
				cli = new Cliente();
				cli.setCodcli(rs.getLong(1));
				cli.setNomcli(rs.getString(2));
				cli.setApecli(rs.getString(3));
				cli.setDnicli(rs.getString(4));
				cli.setTelef1(rs.getString(5));
				cli.setTelef2(rs.getString(6));
				cli.setDircli(rs.getString(7));
				cli.setTipper(rs.getString(8));
				cli.setCliemp(rs.getLong(9));
				cli.setRutfot(rs.getString(10));
				cli.setFecnac(rs.getDate(11));
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
	public ClienteDTO obtenerCliente(long codcli){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuilder cadSql= new StringBuilder();
		ClienteDTO cli = null;
		try {
			cadSql.append("SELECT ");
			cadSql.append("CODCLI,NOMCLI,APECLI,DNICLI,DIRCLI ");
			cadSql.append(" FROM TB_CLIENTE WHERE CODCLI = ?");
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql.toString());
			ps.setLong(1, codcli);
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
	
	@Override
	public Cliente obtenerClientePJ(long codcli){
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuilder cadSql= new StringBuilder();
		Cliente cli = null;
		try {
			cadSql.append("SELECT ");
			cadSql.append("CODCLI,TELEF1,DIRCLI,TIPPER,");
			cadSql.append("RAZSOC,RUCCLI,REPLEG,DNIREPL");
			cadSql.append(" FROM TB_CLIENTE WHERE CODCLI = ?");
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql.toString());
			ps.setLong(1, codcli);
			rs=ps.executeQuery();
			
			if (rs.next()){
				cli = new Cliente();
				cli.setCodcli(rs.getLong(1));
				cli.setTelef1(rs.getString(2));
				cli.setDircli(rs.getString(3));
				cli.setTipper(rs.getString(4));
				cli.setRazsoc(rs.getString(5));
				cli.setRuccli(rs.getString(6));
				cli.setRepleg(rs.getString(7));
				cli.setDnirepl(rs.getString(8));
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
			cadSql.append("TELEF1 = ?, TELEF2 = ?, DIRCLI = ?, TIPPER = ?,");
			cadSql.append("CLIEMP = ?, RUTFOT = ?, ");
			cadSql.append("USUMOD = ?, FECMOD = ?, FECNAC = ? ");
			cadSql.append("WHERE CODCLI = ?");
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql.toString());
			ps.setString(1,cli.getNomcli());
			ps.setString(2,cli.getApecli());
			ps.setString(3,cli.getDnicli());
			ps.setString(4,cli.getTelef1());
			ps.setString(5,cli.getTelef2());
			ps.setString(6,cli.getDircli());
			ps.setString(7,cli.getTipper());
			ps.setLong(8,cli.getCliemp());
			ps.setString(9,cli.getRutfot());
			ps.setString(10,cli.getUsumod());
			ps.setDate(11,new Date(cli.getFecmod().getTime()));
			ps.setDate(12,new Date(cli.getFecnac().getTime()));
			ps.setLong(13,cli.getCodcli());
			
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
			cadSql.append("TELEF1 = ?, DIRCLI = ?, RAZSOC = ?, RUCCLI = ?,");
			cadSql.append("REPLEG = ?, DNIREPL = ?,USUMOD = ?, FECMOD = ?  ");
			cadSql.append("WHERE CODCLI = ?");
			cn=ds.getConnection();
			ps=cn.prepareStatement(cadSql.toString());
			ps.setString(1,cli.getTelef1());
			ps.setString(2,cli.getDircli());
			ps.setString(3,cli.getRazsoc());
			ps.setString(4,cli.getRuccli());
			ps.setString(5,cli.getRepleg());
			ps.setString(6,cli.getDnirepl());
			ps.setString(7,cli.getUsumod());
			ps.setDate(8,(Date)cli.getFecmod());
			ps.setLong(9,cli.getCodcli());
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
