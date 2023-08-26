package prueba.users;

import java.util.List;

import Maestros.users;
import Modelos.ModeloUsers;

public class ConsultaActivos {

	public static void main(String[] args) {
		try {
			users categoryService = new users();
			List<ModeloUsers> lista = categoryService.getActive();
			for (ModeloUsers category : lista) {
				System.out.println(category);
			}
		} catch (Exception e) {
			System.err.println("Hubo un error");
		}
	}
}
