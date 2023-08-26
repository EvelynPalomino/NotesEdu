package prueba.teacher;

import java.util.List;

import Maestros.teacher;
import Modelos.ModeloTeacher;

public class ConsultaFiltros {

	public static void main(String[] args) {
		try {
			ModeloTeacher bean = new ModeloTeacher();
			bean.setName_teacher("luis");
			bean.setLast_name("");
			teacher userService = new teacher();
			List<ModeloTeacher> lista = userService.get(bean);
			for (ModeloTeacher user : lista) {
				System.out.println(user);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
