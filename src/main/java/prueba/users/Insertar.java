package prueba.users;

import Maestros.users;
import Modelos.ModeloUsers;

public class Insertar {

	public static void main(String[] args) {
		try {
			ModeloUsers bean = new ModeloUsers("ssssssss", "aaaaaa", "DNI", "33333333", "D",  "AS22@gmail.com", "999994442");
			users users = new users();
			users.insert(bean);
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
