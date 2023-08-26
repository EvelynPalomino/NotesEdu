package Controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Maestros.users;
import Modelos.ModeloUsers;

@WebServlet({ "/UsersBuscar", "/UsersProcesar", "/UsersActualizar", "/UsersHistorial" })
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private users service = new users();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		switch (path) {
		case "/UsersBuscar":
			buscar(request, response);
			break;
		case "/UsersProcesar":
			procesar(request, response);
			break;
		case "/UsersActualizar":
			actualizar(request, response);
			break;
		case "/UsersHistorial":
			historial(request, response);
			break;
		}
	}

	private void procesar(HttpServletRequest request, HttpServletResponse response) {
		// Datos
		String accion = request.getParameter("accion");
		ModeloUsers bean = new ModeloUsers();
		bean.setId(Integer.parseInt(request.getParameter("id")));
		bean.setNames(request.getParameter("names"));
		bean.setLast_name(request.getParameter("last_name"));
		bean.setType_document(request.getParameter("type_document"));
		bean.setNumber_document(request.getParameter("number_document"));
		bean.setType_user(request.getParameter("type_user"));
		bean.setEmail(request.getParameter("email"));
		bean.setCellphone(request.getParameter("cellphone"));
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
				service.delete(bean.getId().toString());
				break;
			case ControllerUtil.CRUD_RESTAURAR:
				service.restore(bean.getId().toString());
				break;
			case ControllerUtil.CRUD_ELIMINATE:
				service.eliminate(bean.getId().toString());
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + accion);
			}
			ControllerUtil.responseJson(response, "Proceso ok.");
		} catch (Exception e) {
			ControllerUtil.responseJson(response, e.getMessage());
		}
	}

	private void buscar(HttpServletRequest request, HttpServletResponse response) {
		// Datos
		String names = request.getParameter("names");
		String last_name = request.getParameter("last_name");
		// Proceso
		ModeloUsers bean = new ModeloUsers();
		bean.setNames(names);
		bean.setLast_name(last_name);
		List<ModeloUsers> lista = service.get(bean);
		// Preparando el JSON
		Gson gson = new Gson();
		String data = gson.toJson(lista);
		// Reporte
		ControllerUtil.responseJson(response, data);
	}

	private void historial(HttpServletRequest request, HttpServletResponse response) {
		List<ModeloUsers> lista = service.getInactive();
		Gson gson = new Gson();
		String data = gson.toJson(lista);
		ControllerUtil.responseJson(response, data);
	}

	private void actualizar(HttpServletRequest request, HttpServletResponse response) {
		List<ModeloUsers> lista = service.getActive();
		Gson gson = new Gson();
		String data = gson.toJson(lista);
		ControllerUtil.responseJson(response, data);
	}

}
