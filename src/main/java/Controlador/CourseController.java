package Controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Maestros.course;
import Modelos.ModeloCourse;

@WebServlet({ "/CourseBuscar", "/CourseProcesar", "/CourseActualizar", "/CourseHistorial" })
public class CourseController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private course service = new course();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		switch (path) {
		case "/CourseBuscar":
			buscar(request, response);
			break;
		case "/CourseProcesar":
			procesar(request, response);
			break;
		case "/CourseActualizar":
			actualizar(request, response);
			break;
		case "/CourseHistorial":
			historial(request, response);
			break;
		}
	}

	private void procesar(HttpServletRequest request, HttpServletResponse response) {
		// Datos
		String accion = request.getParameter("accion");
		ModeloCourse bean = new ModeloCourse();
		bean.setId_course(Integer.parseInt(request.getParameter("id_course")));
		bean.setName_course(request.getParameter("name_course"));
		bean.setGrade_id(Integer.parseInt(request.getParameter("grade_id")));

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
				service.delete(bean.getId_course().toString());
				break;
			case ControllerUtil.CRUD_RESTAURAR:
				service.restore(bean.getId_course().toString());
				break;
			case ControllerUtil.CRUD_ELIMINATE:
				service.eliminate(bean.getId_course().toString());
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
		String names = request.getParameter("name_course");
		// Proceso
		ModeloCourse bean = new ModeloCourse();
		bean.setName_course(names);
		List<ModeloCourse> lista = service.get(bean);
		// Preparando el JSON
		Gson gson = new Gson();
		String data = gson.toJson(lista);
		// Reporte
		ControllerUtil.responseJson(response, data);
	}

	private void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ModeloCourse> lista = service.getActive();
		Gson gson = new Gson();
		String data = gson.toJson(lista);
		ControllerUtil.responseJson(response, data);
	}

	private void historial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ModeloCourse> lista = service.getInactive();
		Gson gson = new Gson();
		String data = gson.toJson(lista);
		ControllerUtil.responseJson(response, data);
	}

}
