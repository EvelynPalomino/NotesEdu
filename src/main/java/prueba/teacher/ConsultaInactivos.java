package prueba.teacher;

import java.util.List;

import Maestros.teacher;
import Modelos.ModeloTeacher;

public class ConsultaInactivos {

	public static void main(String[] args) {
		try {
			teacher userService = new teacher();
			List<ModeloTeacher> lista = userService.getInactive();
			for (ModeloTeacher teacher : lista) {
				System.out.println(teacher);
			}
		} catch (Exception e) {
			System.err.println("Hubo un error");
		}
	}
}
