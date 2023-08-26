package prueba.course;

import java.util.List;

import Maestros.course;
import Modelos.ModeloCourse;

public class ConsultaFiltros {

	public static void main(String[] args) {
		try {
			ModeloCourse bean = new ModeloCourse();
			bean.setName_course("Tutoria");
			course userService = new course();
			List<ModeloCourse> lista = userService.get(bean);
			for (ModeloCourse course : lista) {
				System.out.println(course);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
