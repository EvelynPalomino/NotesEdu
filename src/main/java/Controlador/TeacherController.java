package Controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Maestros.teacher;
import Modelos.ModeloTeacher;

@WebServlet({ "/TeacherBuscar", "/TeacherProcesar", "/TeacherActualizar", "/TeacherHistorial" })
public class TeacherController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private teacher service = new teacher();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		switch (path) {
		case "/TeacherBuscar":
			buscar(request, response);
			break;
		case "/TeacherProcesar":
			procesar(request, response);
			break;
		case "/TeacherActualizar":
			actualizar(request, response);
			break;
		case "/TeacherHistorial":
			historial(request, response);
			break;
		}
	}

	private void procesar(HttpServletRequest request, HttpServletResponse response) {
		// Datos
		String accion = request.getParameter("accion");
		ModeloTeacher bean = new ModeloTeacher();
		bean.setId_teacher(Integer.parseInt(request.getParameter("id_teacher")));
		bean.setName_teacher(request.getParameter("name_teacher"));
		bean.setLast_name(request.getParameter("last_name"));
		bean.setUser_teacher(request.getParameter("user_teacher"));
		bean.setPassword_teacher(request.getParameter("password_teacher"));

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
				service.delete(bean.getId_teacher().toString());
				break;
			case ControllerUtil.CRUD_RESTAURAR:
				service.restore(bean.getId_teacher().toString());
				break;
			case ControllerUtil.CRUD_ELIMINATE:
				service.eliminate(bean.getId_teacher().toString());
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
		String names = request.getParameter("name_teacher");
		String last_name = request.getParameter("last_name");
		// Proceso
		ModeloTeacher bean = new ModeloTeacher();
		bean.setName_teacher(names);
		bean.setLast_name(last_name);
		List<ModeloTeacher> lista = service.get(bean);
		// Preparando el JSON
		Gson gson = new Gson();
		String data = gson.toJson(lista);
		// Reporte
		ControllerUtil.responseJson(response, data);
	}

	private void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ModeloTeacher> lista = service.getActive();
		Gson gson = new Gson();
		String data = gson.toJson(lista);
		ControllerUtil.responseJson(response, data);
	}

	private void historial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ModeloTeacher> lista = service.getInactive();
		Gson gson = new Gson();
		String data = gson.toJson(lista);
		ControllerUtil.responseJson(response, data);
	}

}
