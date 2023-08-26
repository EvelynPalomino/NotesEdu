package prueba.teacher;

import Maestros.teacher;
import Modelos.ModeloTeacher;

public class Editar {

	public static void main(String[] args) {
		try {
			ModeloTeacher bean = new ModeloTeacher(1002, "Sofia Giselle","Sanchez","Sofi","83799747" );
			teacher teacher = new teacher();
			teacher.update(bean);
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
