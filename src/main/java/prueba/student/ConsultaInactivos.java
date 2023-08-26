package prueba.student;

import java.util.List;

import Maestros.student;
import Modelos.ModeloStudent;

public class ConsultaInactivos {

	public static void main(String[] args) {
		try {
			student userService = new student();
			List<ModeloStudent> lista = userService.getInactive();
			for (ModeloStudent user : lista) {
				System.out.println(user);
			}
		} catch (Exception e) {
			System.err.println("Hubo un error");
		}
	}
}
