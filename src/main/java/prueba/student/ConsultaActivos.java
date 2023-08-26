package prueba.student;

import java.util.List;

import Maestros.student;
import Modelos.ModeloStudent;

public class ConsultaActivos {

	public static void main(String[] args) {
		try {
			student userService = new student();
			List<ModeloStudent> lista = userService.getActive();
			for (ModeloStudent student : lista) {
				System.out.println(student);
			}
		} catch (Exception e) {
			System.err.println("Hubo un error");
		}
	}
}
