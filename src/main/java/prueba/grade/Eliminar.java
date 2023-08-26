package prueba.grade;

import Maestros.grade;

public class Eliminar {

	public static void main(String[] args) {
		try {
			grade userService = new grade();
			userService.delete("4");
			System.out.println("Usuario eliminado correctamente.");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
