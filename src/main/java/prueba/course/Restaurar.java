package prueba.course;

import Maestros.course;

public class Restaurar {

	public static void main(String[] args) {
		try {
			course userService = new course();
			userService.restore("3");
			System.out.println("Usuario restaurado correctamente.");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
