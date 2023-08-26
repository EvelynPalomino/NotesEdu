package prueba.course;

import Maestros.course;

public class Eliminar {

	public static void main(String[] args) {
		try {
			course userService = new course();
			userService.delete("3");
			System.out.println("Usuario eliminado correctamente.");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
