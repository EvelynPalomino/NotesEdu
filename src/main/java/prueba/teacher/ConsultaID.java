package prueba.teacher;

import Maestros.teacher;
import Modelos.ModeloTeacher;

public class ConsultaID {

	public static void main(String[] args) {
		try {
			teacher userService = new teacher();
			ModeloTeacher bean = userService.getForId("2");
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
