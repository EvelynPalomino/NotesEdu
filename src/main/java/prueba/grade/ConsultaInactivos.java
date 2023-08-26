package prueba.grade;

import java.util.List;

import Maestros.grade;
import Modelos.ModeloGrade;

public class ConsultaInactivos {

	public static void main(String[] args) {
		try {
			grade userService = new grade();
			List<ModeloGrade> lista = userService.getInactive();
			for (ModeloGrade user : lista) {
				System.out.println(user);
			}
		} catch (Exception e) {
			System.err.println("Hubo un error");
		}
	}
}
