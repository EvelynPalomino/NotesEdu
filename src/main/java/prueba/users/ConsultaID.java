package prueba.users;

import Maestros.users;
import Modelos.ModeloUsers;

public class ConsultaID {

	public static void main(String[] args) {
		try {
			users categoryService = new users();
			ModeloUsers bean = categoryService.getForId("2");
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
