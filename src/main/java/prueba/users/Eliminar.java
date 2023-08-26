package prueba.users;

import Maestros.users;

public class Eliminar {

	public static void main(String[] args) {
		try {
			users categoryService = new users();
			categoryService.delete("11");
			System.out.println("Usuario eliminado correctamente.");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
