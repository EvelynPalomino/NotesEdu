package prueba.teacher;

import Maestros.teacher;
import Modelos.ModeloTeacher;

public class Insertar {

	public static void main(String[] args) {
		try {
			ModeloTeacher bean = new ModeloTeacher("juaquin", "bot", "Juaquin", "22222212");
			teacher teacher = new teacher();
			teacher.insert(bean);
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
