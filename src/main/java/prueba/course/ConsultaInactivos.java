package prueba.course;

import java.util.List;

import Maestros.course;
import Modelos.ModeloCourse;

public class ConsultaInactivos {

	public static void main(String[] args) {
		try {
			course userService = new course();
			List<ModeloCourse> lista = userService.getInactive();
			for (ModeloCourse course : lista) {
				System.out.println(course);
			}
		} catch (Exception e) {
			System.err.println("Hubo un error");
		}
	}
}
