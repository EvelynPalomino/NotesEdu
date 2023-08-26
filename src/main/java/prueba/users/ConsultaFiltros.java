package prueba.users;

import java.util.List;

import Maestros.users;
import Modelos.ModeloUsers;

public class ConsultaFiltros {

	public static void main(String[] args) {
		try {
			ModeloUsers bean = new ModeloUsers();
			bean.setNames("luisa");
			bean.setLast_name("");
			users categoryService = new users();
			List<ModeloUsers> lista = categoryService.get(bean);
			for (ModeloUsers category : lista) {
				System.out.println(category);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
