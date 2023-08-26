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
import Modelos.ModeloStudent;

public class student implements CrudServiceSpec<ModeloStudent>, RowMapper<ModeloStudent> {

	private final String SQL_SELECT_ACTIVE = "SELECT * FROM student WHERE asset_student='A'";
	private final String SQL_SELECT_INACTIVE = "SELECT * FROM student WHERE asset_student='I'";
	private final String SQL_SELECT_ID = "SELECT * FROM student WHERE asset_student='A' AND id_student=?";
	private final String SQL_SELECT_LIKE = "SELECT * FROM student WHERE names_student LIKE ? AND last_namestudent LIKE ? AND asset_student='A'";
	private final String SQL_INSERT = "INSERT INTO STUDENT(names_student,last_namestudent,type_document,number_document,grade_id) VALUES(?,?,?,?,?)";
	private final String SQL_UPDATE = "UPDATE student SET names_student=?, last_namestudent=?, type_document=?, number_document=?, grade_id=? WHERE id_student=?";
	private final String SQL_DELETE = "UPDATE student SET asset_student='I' WHERE id_student=?";
	private final String SQL_RESTORE = "UPDATE student SET asset_student='A' WHERE id_student=?";
	private final String SQL_ELIMINATE = "DELETE FROM student WHERE id_student=?";

	@Override
	public List<ModeloStudent> getActive() {
		List<ModeloStudent> lista = new ArrayList<>();
		try (Connection cn = AccesoDB.getConnection();
				PreparedStatement pstm = cn.prepareStatement(SQL_SELECT_ACTIVE);
				ResultSet rs = pstm.executeQuery();) {
			while (rs.next()) {
				ModeloStudent bean = mapRow(rs);
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
	public List<ModeloStudent> getInactive() {
		List<ModeloStudent> lista = new ArrayList<>();
		try (Connection cn = AccesoDB.getConnection();
				PreparedStatement pstm = cn.prepareStatement(SQL_SELECT_INACTIVE);
				ResultSet rs = pstm.executeQuery();) {
			while (rs.next()) {
				ModeloStudent bean = mapRow(rs);
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
	public ModeloStudent getForId(String id) {
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ModeloStudent bean = null;
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
	public List<ModeloStudent> get(ModeloStudent bean) {
		Connection cn = null;
		List<ModeloStudent> lista = new ArrayList<>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ModeloStudent item;
		String names_student, last_namestudent;
		names_student = "%" + UtilService.setStringVacio(bean.getNames_student()) + "%";
		last_namestudent = "%" + UtilService.setStringVacio(bean.getLast_namestudent()) + "%";
		try {
			cn = AccesoDB.getConnection();
			pstm = cn.prepareStatement(SQL_SELECT_LIKE);
			pstm.setString(1, names_student);
			pstm.setString(2, last_namestudent);
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
	public void insert(ModeloStudent bean) {
		Connection cn = null;
		PreparedStatement pstm = null;
		int filas;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			pstm = cn.prepareStatement(SQL_INSERT);
			pstm.setString(1, bean.getNames_student());
			pstm.setString(2, bean.getLast_namestudent());
			pstm.setString(3, bean.getType_document());
			pstm.setString(4, bean.getNumber_document());
			pstm.setInt(5, bean.getGrade_id());
			
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
	public void update(ModeloStudent bean) {
		Connection cn = null;
		PreparedStatement pstm = null;
		int filas;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			pstm = cn.prepareStatement(SQL_UPDATE);
			pstm.setString(1, bean.getNames_student());
			pstm.setString(2, bean.getLast_namestudent());
			pstm.setString(3, bean.getType_document());
			pstm.setString(4, bean.getNumber_document());
			pstm.setInt(5, bean.getGrade_id());
			pstm.setInt(6, bean.getId_student());
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

	@Override
	public ModeloStudent mapRow(ResultSet rs) throws SQLException {
		ModeloStudent bean = new ModeloStudent();
		bean.setId_student(rs.getInt("id_student"));
		bean.setNames_student(rs.getString("names_student"));
		bean.setLast_namestudent(rs.getString("last_namestudent"));
		bean.setType_document(rs.getString("type_document"));
		bean.setNumber_document(rs.getString("number_document"));
		bean.setAsset_student(rs.getString("asset_student"));
		bean.setGrade_id(rs.getInt("grade_id"));
		return bean;
	}

}
