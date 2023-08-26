package prueba.student;

import Maestros.student;
import Modelos.ModeloStudent;

public class ConsultaID {

	public static void main(String[] args) {
		try {
			student userService = new student();
			ModeloStudent bean = userService.getForId("2");
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
