package prueba.student;

import Maestros.student;

public class Eliminar {

	public static void main(String[] args) {
		try {
			student userService = new student();
			userService.delete("11");
			System.out.println("Usuario eliminado correctamente.");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
