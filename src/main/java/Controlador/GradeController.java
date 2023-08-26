package Controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Maestros.grade;
import Modelos.ModeloGrade;

@WebServlet({ "/GradeBuscar", "/GradeProcesar", "/GradeActualizar", "/GradeHistorial" })
public class GradeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private grade service = new grade();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		switch (path) {
		case "/GradeBuscar":
			buscar(request, response);
			break;
		case "/GradeProcesar":
			procesar(request, response);
			break;
		case "/GradeActualizar":
			actualizar(request, response);
			break;
		case "/GradeHistorial":
			historial(request, response);
			break;
		}
	}

	private void procesar(HttpServletRequest request, HttpServletResponse response) {
		// Datos
		String accion = request.getParameter("accion");
		ModeloGrade bean = new ModeloGrade();
		bean.setId_grade(Integer.parseInt(request.getParameter("id_grade")));
		bean.setGrade(request.getParameter("grade"));
		bean.setSection(request.getParameter("section"));
		bean.setAcademic_level(request.getParameter("academic_level"));
		bean.setTutor(request.getParameter("tutor"));
		// Proceso
		try {
			switch (accion) {
			case ControllerUtil.CRUD_NUEVO:
				service.insert(bean);
				break;
			case ControllerUtil.CRUD_EDITAR:
				service.update(bean);
				break;
			case ControllerUtil.CRUD_ELIMINAR:
				service.delete(bean.getId_grade().toString());
				break;
			case ControllerUtil.CRUD_RESTAURAR:
				service.restore(bean.getId_grade().toString());
				break;
			case ControllerUtil.CRUD_ELIMINATE:
				service.eliminate(bean.getId_grade().toString());
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + accion);
			}
			ControllerUtil.responseJson(response, "Proceso ok.");
		} catch (Exception e) {
			ControllerUtil.responseJson(response, e.getMessage());
		}
	}

	private void buscar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Datos
		String academic_level = request.getParameter("academic_level");
		// Proceso
		ModeloGrade bean = new ModeloGrade();
		bean.setAcademic_level(academic_level);
		List<ModeloGrade> lista = service.get(bean);
		// Preparando el JSON
		Gson gson = new Gson();
		String data = gson.toJson(lista);
		// Reporte
		ControllerUtil.responseJson(response, data);
	}

	private void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ModeloGrade> lista = service.getActive();
		Gson gson = new Gson();
		String data = gson.toJson(lista);
		ControllerUtil.responseJson(response, data);
	}

	private void historial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ModeloGrade> lista = service.getInactive();
		Gson gson = new Gson();
		String data = gson.toJson(lista);
		ControllerUtil.responseJson(response, data);
	}

}
