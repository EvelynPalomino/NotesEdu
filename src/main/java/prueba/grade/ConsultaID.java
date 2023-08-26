package prueba.grade;

import Maestros.grade;
import Modelos.ModeloGrade;

public class ConsultaID {

	public static void main(String[] args) {
		try {
			grade userService = new grade();
			ModeloGrade bean = userService.getForId("2");
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
