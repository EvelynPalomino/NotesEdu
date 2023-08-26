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
import Modelos.ModeloGrade;

public class grade implements CrudServiceSpec<ModeloGrade>, RowMapper<ModeloGrade> {

	private final String SQL_SELECT_ACTIVE = "SELECT * FROM grade WHERE state_grade='A'";
	private final String SQL_SELECT_INACTIVE = "SELECT * FROM grade WHERE state_grade='I'";
	private final String SQL_SELECT_ID = "SELECT * FROM grade WHERE state_grade='A' AND id_grade=?"; 
	private final String SQL_SELECT_LIKE = "SELECT * FROM grade WHERE academic_level LIKE ? ";
	private final String SQL_INSERT = "INSERT INTO grade(grade,section,academic_level,tutor) VALUES(?,?,?,?)";
	private final String SQL_UPDATE = "UPDATE grade SET grade=?, section=?, academic_level=?, tutor=? WHERE id_grade=?";
	private final String SQL_DELETE = "UPDATE grade SET state_grade='I' WHERE id_grade=?";
	private final String SQL_RESTORE = "UPDATE grade SET state_grade='A' WHERE id_grade=?";
	private final String SQL_ELIMINATE = "DELETE FROM grade WHERE id_grade=?";

	@Override
	public List<ModeloGrade> getActive() {
		List<ModeloGrade> lista = new ArrayList<>();
		try (Connection cn = AccesoDB.getConnection();
				PreparedStatement pstm = cn.prepareStatement(SQL_SELECT_ACTIVE);
				ResultSet rs = pstm.executeQuery();) {
			while (rs.next()) {
				ModeloGrade bean = mapRow(rs);
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
	public List<ModeloGrade> getInactive() {
		List<ModeloGrade> lista = new ArrayList<>();
		try (Connection cn = AccesoDB.getConnection();
				PreparedStatement pstm = cn.prepareStatement(SQL_SELECT_INACTIVE);
				ResultSet rs = pstm.executeQuery();) {
			while (rs.next()) {
				ModeloGrade bean = mapRow(rs);
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
	public ModeloGrade getForId(String id) {
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ModeloGrade bean = null;
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
	public List<ModeloGrade> get(ModeloGrade bean) {
		Connection cn = null;
		List<ModeloGrade> lista = new ArrayList<>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ModeloGrade item;
		String academic_level;
		academic_level = "%" + UtilService.setStringVacio(bean.getAcademic_level()) + "%";
		try {
			cn = AccesoDB.getConnection();
			pstm = cn.prepareStatement(SQL_SELECT_LIKE);
			pstm.setString(1, academic_level);
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
	public void insert(ModeloGrade bean) {
		Connection cn = null;
		PreparedStatement pstm = null;
		int filas;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			pstm = cn.prepareStatement(SQL_INSERT);
			pstm.setString(1, bean.getGrade());
			pstm.setString(2, bean.getSection());
			pstm.setString(3, bean.getAcademic_level());
			pstm.setString(4, bean.getTutor());			
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
	public void update(ModeloGrade bean) {
		Connection cn = null;
		PreparedStatement pstm = null;
		int filas;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			pstm = cn.prepareStatement(SQL_UPDATE);
			pstm.setString(1, bean.getGrade());
			pstm.setString(2, bean.getSection());
			pstm.setString(3, bean.getAcademic_level());
			pstm.setString(4, bean.getTutor());
			pstm.setInt(5, bean.getId_grade());
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
	public ModeloGrade mapRow(ResultSet rs) throws SQLException {
		ModeloGrade bean = new ModeloGrade();
		bean.setId_grade(rs.getInt("id_grade"));
		bean.setGrade(rs.getString("grade"));
		bean.setSection(rs.getString("section"));
		bean.setAcademic_level(rs.getString("academic_level"));
		bean.setTutor(rs.getString("tutor"));
		bean.setState_grade(rs.getString("state_grade"));
		return bean;
	}

}
