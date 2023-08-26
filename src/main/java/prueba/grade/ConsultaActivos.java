package prueba.grade;

import java.util.List;

import Maestros.grade;
import Modelos.ModeloGrade;

public class ConsultaActivos {

	public static void main(String[] args) {
		try {
			grade userService = new grade();
			List<ModeloGrade> lista = userService.getActive();
			for (ModeloGrade student : lista) {
				System.out.println(student);
			}
		} catch (Exception e) {
			System.err.println("Hubo un error");
		}
	}
}
