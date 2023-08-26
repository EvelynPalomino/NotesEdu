package prueba.teacher;

import Maestros.teacher;

public class Eliminar {

	public static void main(String[] args) {
		try {
			teacher userService = new teacher();
			userService.delete("10");
			System.out.println("Usuario eliminado correctamente.");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
