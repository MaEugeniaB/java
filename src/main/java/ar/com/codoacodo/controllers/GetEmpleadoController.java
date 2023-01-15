package ar.com.codoacodo.controllers;

import ar.com.codoacodo.dao.iEmpleadoDAO;
import ar.com.codoacodo.dao.implement.EmpleadoDAOMysqlImpl;
import ar.com.codoacodo.domain.Empleado;

public class GetEmpleadoController  {
	
	public static void main(String[] args) {
		//interface = new class que implementa la interface
		iEmpleadoDAO dao = new EmpleadoDAOMysqlImpl();
		
		// como llego a la base de datos si quiero pedir datos de un empleado?
		Long dni = 32369854l;
		Empleado em;
		// si no usamos try catch podemos arriba poner throws Exception
		try {
		em = dao.getById(dni);
		}
		catch(Exception e) {
			//e.printStackTrace();
			em= null;
		}
		if (em != null) {
			System.out.println(em);
		}else {
			System.err.println("No existe el dni:"+ dni);
		}
		
	}

}
