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
import Modelos.ModeloTeacher;

public class teacher implements CrudServiceSpec<ModeloTeacher>, RowMapper<ModeloTeacher> {

	private final String SQL_SELECT_ACTIVE = "SELECT * FROM teacher WHERE asset_teacher='A'";
	private final String SQL_SELECT_INACTIVE = "SELECT * FROM teacher WHERE asset_teacher='I'";
	private final String SQL_SELECT_ID = "SELECT * FROM teacher WHERE asset_teacher='A' AND id_teacher=?";
	private final String SQL_SELECT_LIKE = "SELECT * FROM teacher WHERE name_teacher LIKE ? AND last_name LIKE ? AND asset_teacher='A'";
	private final String SQL_INSERT = "INSERT INTO teacher (name_teacher, last_name, user_teacher, password_teacher) VALUES (?,?,?,?)";
	private final String SQL_UPDATE = "UPDATE teacher SET name_teacher=?, last_name=?, user_teacher=?, password_teacher=? WHERE id_teacher=?";
	private final String SQL_DELETE = "UPDATE teacher SET asset_teacher='I' WHERE id_teacher=?";
	private final String SQL_RESTORE = "UPDATE teacher SET asset_teacher='A' WHERE id_teacher=?";
	private final String SQL_ELIMINATE = "DELETE FROM teacher WHERE id_teacher=?";


	@Override
	public List<ModeloTeacher> getActive() {
		List<ModeloTeacher> lista = new ArrayList<>();
		try (Connection cn = AccesoDB.getConnection();
				PreparedStatement pstm = cn.prepareStatement(SQL_SELECT_ACTIVE);
				ResultSet rs = pstm.executeQuery();) {
			while (rs.next()) {
				ModeloTeacher bean = mapRow(rs);
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
	public List<ModeloTeacher> getInactive() {
		List<ModeloTeacher> lista = new ArrayList<>();
		try (Connection cn = AccesoDB.getConnection();
				PreparedStatement pstm = cn.prepareStatement(SQL_SELECT_INACTIVE);
				ResultSet rs = pstm.executeQuery();) {
			while (rs.next()) {
				ModeloTeacher bean = mapRow(rs);
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
	public ModeloTeacher getForId(String id) {
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ModeloTeacher bean = null;
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
	public List<ModeloTeacher> get(ModeloTeacher bean) {
		Connection cn = null;
		List<ModeloTeacher> lista = new ArrayList<>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ModeloTeacher item;
		String name_teacher, last_name;
		name_teacher = "%" + UtilService.setStringVacio(bean.getName_teacher()) + "%";
		last_name = "%" + UtilService.setStringVacio(bean.getLast_name()) + "%";
		try {
			cn = AccesoDB.getConnection();
			pstm = cn.prepareStatement(SQL_SELECT_LIKE);
			pstm.setString(1, name_teacher);
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
	public void insert(ModeloTeacher bean) {
		Connection cn = null;
		PreparedStatement pstm = null;
		int filas;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			pstm = cn.prepareStatement(SQL_INSERT);
			pstm.setString(1, bean.getName_teacher());
			pstm.setString(2, bean.getLast_name());
			pstm.setString(3, bean.getUser_teacher());
			pstm.setString(4, bean.getPassword_teacher());
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
	public void update(ModeloTeacher bean) {
		Connection cn = null;
		PreparedStatement pstm = null;
		int filas;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			pstm = cn.prepareStatement(SQL_UPDATE);
			pstm.setString(1, bean.getName_teacher());
			pstm.setString(2, bean.getLast_name());
			pstm.setString(3, bean.getUser_teacher());
			pstm.setString(4, bean.getPassword_teacher());
			pstm.setInt(5, bean.getId_teacher());
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
	public ModeloTeacher mapRow(ResultSet rs) throws SQLException {
		ModeloTeacher bean = new ModeloTeacher();
		bean.setId_teacher(rs.getInt("id_teacher"));
		bean.setName_teacher(rs.getString("name_teacher"));
		bean.setLast_name(rs.getString("last_name"));
		bean.setUser_teacher(rs.getString("user_teacher"));
		bean.setPassword_teacher(rs.getString("password_teacher"));
		bean.setAsset_teacher(rs.getString("asset_teacher"));
		return bean;
	}

}
