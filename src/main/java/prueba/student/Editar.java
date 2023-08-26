package prueba.student;

import Maestros.student;
import Modelos.ModeloStudent;

public class Editar {

	public static void main(String[] args) {
		try {
			ModeloStudent bean = new ModeloStudent(10, "Sofia Giselle","Sanchez","DNI","70000002" , 5);
			student student = new student();
			student.update(bean);
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
