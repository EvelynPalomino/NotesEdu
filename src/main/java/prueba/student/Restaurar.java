package prueba.student;

import Maestros.student;

public class Restaurar {

	public static void main(String[] args) {
		try {
			student userService = new student();
			userService.restore("11");
			System.out.println("Usuario restaurado correctamente.");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
