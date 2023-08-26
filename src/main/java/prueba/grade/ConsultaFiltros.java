package prueba.grade;

import java.util.List;

import Maestros.grade;
import Modelos.ModeloGrade;

public class ConsultaFiltros {

	public static void main(String[] args) {
		try {
			ModeloGrade bean = new ModeloGrade();
			bean.setAcademic_level("primaria");
			grade userService = new grade();
			List<ModeloGrade> lista = userService.get(bean);
			for (ModeloGrade user : lista) {
				System.out.println(user);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
