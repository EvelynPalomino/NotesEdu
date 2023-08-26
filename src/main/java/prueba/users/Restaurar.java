package prueba.users;

import Maestros.users;

public class Restaurar {

	public static void main(String[] args) {
		try {
			users categoryService = new users();
			categoryService.restore("11");
			System.out.println("Usuario restaurado correctamente.");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
