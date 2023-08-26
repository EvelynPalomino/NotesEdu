package prueba.course;

import Maestros.course;
import Modelos.ModeloCourse;

public class Editar {

	public static void main(String[] args) {
		try {
			ModeloCourse bean = new ModeloCourse(2, "Comu", 1 );
			course course = new course();
			course.update(bean);
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
