package prueba.users;

import Maestros.users;
import Modelos.ModeloUsers;

public class Editar {

	public static void main(String[] args) {
		try {
			ModeloUsers bean = new ModeloUsers(11, "leonardito", "PRO", "CNE", "333333333", "D", "ASSASAD@gmail.com", "922222123");
			users users = new users();
			users.update(bean);
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
