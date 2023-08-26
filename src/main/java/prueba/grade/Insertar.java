package prueba.grade;

import Maestros.grade;
import Modelos.ModeloGrade;

public class Insertar {

	public static void main(String[] args) {
		try {
			ModeloGrade bean = new ModeloGrade("1", "A", "secundaria", "Jesus Alejandro Torres");
			grade student = new grade();
			student.insert(bean);
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
