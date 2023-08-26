package prueba.course;

import Maestros.course;
import Modelos.ModeloCourse;

public class ConsultaID {

	public static void main(String[] args) {
		try {
			course userService = new course();
			ModeloCourse bean = userService.getForId("2");
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
