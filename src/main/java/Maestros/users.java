package Maestros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import AccesoDB.AccesoDB;
import MaestrosSpec.CrudServiceSpec;
import MaestrosSpec.RowMapper;
import Modelos.ModeloUsers;

public class users implements CrudServiceSpec<ModeloUsers>, RowMapper<ModeloUsers> {

	private final String SQL_SELECT_ACTIVE = "SELECT * FROM users WHERE active='A'";
	private final String SQL_SELECT_INACTIVE = "SELECT * FROM users WHERE active='I'";
	private final String SQL_SELECT_ID = "SELECT * FROM users WHERE active='A' AND id=?";
	private final String SQL_SELECT_LIKE = "SELECT * FROM users WHERE names LIKE ? AND last_name LIKE ? AND active='A'";
	private final String SQL_INSERT = "INSERT INTO users (names, last_name, type_document, number_document, type_user, email, cellphone) VALUES (?,?,?,?,?,?,?)";
	private final String SQL_UPDATE = "UPDATE users SET names=?, last_name=?, type_document=?, number_document=?, type_user=?, email=?, cellphone=? WHERE id=?";
	private final String SQL_DELETE = "UPDATE users SET active='I' WHERE id=?";
	private final String SQL_RESTORE = "UPDATE users SET active='A' WHERE id=?";
	private final String SQL_ELIMINATE = "DELETE FROM users WHERE id=?";


	@Override
	public List<ModeloUsers> getActive() {
		List<ModeloUsers> lista = new ArrayList<>();
		try (Connection cn = AccesoDB.getConnection();
				PreparedStatement pstm = cn.prepareStatement(SQL_SELECT_ACTIVE);
				ResultSet rs = pstm.executeQuery();) {
			while (rs.next()) {
				ModeloUsers bean = mapRow(rs);
				lista.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<ModeloUsers> getInactive() {
		List<ModeloUsers> lista = new ArrayList<>();
		try (Connection cn = AccesoDB.getConnection();
				PreparedStatement pstm = cn.prepareStatement(SQL_SELECT_INACTIVE);
				ResultSet rs = pstm.executeQuery();) {
			while (rs.next()) {
				ModeloUsers bean = mapRow(rs);
				lista.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public ModeloUsers getForId(String id) {
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ModeloUsers bean = null;
		try {
			cn = AccesoDB.getConnection();
			pstm = cn.prepareStatement(SQL_SELECT_ID);
			pstm.setInt(1, Integer.parseInt(id));
			rs = pstm.executeQuery();
			if(rs.next()) {
				bean = mapRow(rs);
			}
			rs.close();
			pstm.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			try {
				cn.close();
			} catch (Exception e2) {
			}
		}
		return bean;
	}

	@Override
	public List<ModeloUsers> get(ModeloUsers bean) {
		Connection cn = null;
		List<ModeloUsers> lista = new ArrayList<>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ModeloUsers item;
		String names, last_name;
		names = "%" + UtilService.setStringVacio(bean.getNames()) + "%";
		last_name = "%" + UtilService.setStringVacio(bean.getLast_name()) + "%";
		try {
			cn = AccesoDB.getConnection();
			pstm = cn.prepareStatement(SQL_SELECT_LIKE);
			pstm.setString(1, names);
			pstm.setString(2, last_name);
			rs = pstm.executeQuery();
			while(rs.next()) {
				item = mapRow(rs);
				lista.add(item);
			}
			rs.close();
			pstm.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			try {
				cn.close();
			} catch (Exception e2) {
			}
		}
		return lista;
	}

	@Override
	public void insert(ModeloUsers bean) {
		Connection cn = null;
		PreparedStatement pstm = null;
		int filas;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			pstm = cn.prepareStatement(SQL_INSERT);
			pstm.setString(1, bean.getNames());
			pstm.setString(2, bean.getLast_name());
			pstm.setString(3, bean.getType_document());
			pstm.setString(4, bean.getNumber_document());
			pstm.setString(5, bean.getType_user());
			pstm.setString(6, bean.getEmail());
			pstm.setString(7, bean.getCellphone());
			filas = pstm.executeUpdate();
			pstm.close();
			if (filas != 1) {
				throw new SQLException("Error, verifique sus datos e intentelo nuevamente.");
			}
			cn.commit();
		} catch (Exception e) {
			try {
				cn.rollback();
				cn.close();
			} catch (Exception e2) {
			}
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
	}


	@Override
	public void update(ModeloUsers bean) {
		Connection cn = null;
		PreparedStatement pstm = null;
		int filas;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			pstm = cn.prepareStatement(SQL_UPDATE);
			pstm.setString(1, bean.getNames());
			pstm.setString(2, bean.getLast_name());
			pstm.setString(3, bean.getType_document());
			pstm.setString(4, bean.getNumber_document());
			pstm.setString(5, bean.getType_user());
			pstm.setString(6, bean.getEmail());
			pstm.setString(7, bean.getCellphone());
			pstm.setInt(8, bean.getId());
			filas = pstm.executeUpdate();
			pstm.close();
			if (filas != 1) {
				throw new SQLException("Error, verifique sus datos e intentelo nuevamente.");
			}
			cn.commit();
		} catch (Exception e) {
			try {
				cn.rollback();
				cn.close();
			} catch (Exception e2) {
			}
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
	}

	@Override
	public void delete(String id) {
		Connection cn = null;
		PreparedStatement pstm = null;
		int filas = 0;
		try {
			// Inicio de Tx
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			pstm = cn.prepareStatement(SQL_DELETE);
			pstm.setInt(1, Integer.parseInt(id));
			filas = pstm.executeUpdate();
			pstm.close();
			if (filas != 1) {
				throw new SQLException("No se pudo eliminar el usuario.");
			}
			cn.commit();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				cn.close();
			} catch (Exception e2) {
			}
		}
	}

	@Override
	public void restore(String id) {
		Connection cn = null;
		PreparedStatement pstm = null;
		int filas = 0;
		try {
			// Inicio de Tx
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			pstm = cn.prepareStatement(SQL_RESTORE);
			pstm.setInt(1, Integer.parseInt(id));
			filas = pstm.executeUpdate();
			pstm.close();
			if (filas != 1) {
				throw new SQLException("No se pudo restaurar el usuario.");
			}
			cn.commit();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				cn.close();
			} catch (Exception e2) {
			}
		}
	}

	@Override
	public void eliminate(String id) {
		Connection cn = null;
		PreparedStatement pstm = null;
		int filas = 0;
		try {
			// Inicio de Tx
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			pstm = cn.prepareStatement(SQL_ELIMINATE);
			pstm.setInt(1, Integer.parseInt(id));
			filas = pstm.executeUpdate();
			pstm.close();
			if (filas != 1) {
				throw new SQLException("No se pudo eliminar el usuario.");
			}
			cn.commit();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				cn.close();
			} catch (Exception e2) {
			}
		}
	}

	///este
	@Override
	public ModeloUsers mapRow(ResultSet rs) throws SQLException {
		ModeloUsers bean = new ModeloUsers();
		bean.setId(rs.getInt("id"));
		bean.setNames(rs.getString("names"));
		bean.setLast_name(rs.getString("last_name"));
		bean.setType_document(rs.getString("type_document"));
		bean.setNumber_document(rs.getString("number_document"));
		bean.setType_user(rs.getString("type_user"));
		bean.setEmail(rs.getString("email"));
		bean.setCellphone(rs.getString("cellphone"));
		bean.setActive(rs.getString("active"));
		return bean;
	}

}
