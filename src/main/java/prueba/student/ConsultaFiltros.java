package prueba.student;

import java.util.List;

import Maestros.student;
import Modelos.ModeloStudent;

public class ConsultaFiltros {

	public static void main(String[] args) {
		try {
			ModeloStudent bean = new ModeloStudent();
			bean.setNames_student("Carmen");
			bean.setLast_namestudent("");
			student userService = new student();
			List<ModeloStudent> lista = userService.get(bean);
			for (ModeloStudent user : lista) {
				System.out.println(user);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
