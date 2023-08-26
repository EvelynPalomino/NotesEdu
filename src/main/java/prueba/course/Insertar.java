package prueba.course;

import Maestros.course;
import Modelos.ModeloCourse;

public class Insertar {

	public static void main(String[] args) {
		try {
			ModeloCourse bean = new ModeloCourse("Ciencias Sociales",5);
			course course = new course();
			course.insert(bean);
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
