package prueba.users;

import java.util.List;

import Maestros.users;
import Modelos.ModeloUsers;

public class ConsultaInactivos {

	public static void main(String[] args) {
		try {
			users categoryService = new users();
			List<ModeloUsers> lista = categoryService.getInactive();
			for (ModeloUsers category : lista) {
				System.out.println(category);
			}
		} catch (Exception e) {
			System.err.println("Hubo un error");
		}
	}
}
