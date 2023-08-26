package prueba.grade;

import Maestros.grade;
import Modelos.ModeloGrade;

public class Editar {

	public static void main(String[] args) {
		try {
			ModeloGrade bean = new ModeloGrade(1, "1","A","primaria","Fabricio Joseli" );
			grade student = new grade();
			student.update(bean);
			System.out.println(bean);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
