package prueba.teacher;

import Maestros.teacher;

public class Restaurar {

	public static void main(String[] args) {
		try {
			teacher userService = new teacher();
			userService.restore("10");
			System.out.println("Usuario restaurado correctamente.");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
