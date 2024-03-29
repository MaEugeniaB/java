package ar.com.codoacodo.controllers;

import ar.com.codoacodo.dao.iEmpleadoDAO;
import ar.com.codoacodo.dao.implement.EmpleadoDAOMysqlImpl;
import ar.com.codoacodo.domain.Empleado;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/CreateEmpleadoController")
public class CreateEmpleadoController extends HttpServlet {


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//capturo los parametros que viene en el request enviado por el form
		String dni = req.getParameter("dni");//name de input
		String nombre= req.getParameter("nombre");//name de input
		String apellido = req.getParameter("apellido");//name de input
		String departamento = req.getParameter("departamento");//name de input
		
		
		//validaciones!
		List<String> errores = new ArrayList<>();
		if(nombre == null || "".equals(nombre)) {
			errores.add("Nombre vacío");
		}
		if(dni== null || "".equals(dni)) {
			errores.add("dni vacío");
		}
		if(apellido== null || "".equals(apellido)) {
			errores.add("Apellido vacío");
		}
		if(departamento== null || "".equals(departamento)) {
			errores.add("Departamento vacío");
		}
		//agrego las demas validaciones!!!! (uds)
		if(!errores.isEmpty()) {
			req.setAttribute("errors", errores);
			//vuelvo a la jsp con la lista de errores cargadas 
			getServletContext().getRequestDispatcher("/nuevoE.jsp").forward(req, resp);
			return;
		}

		
		
		
		//interface = new class que implementa la interface
		iEmpleadoDAO dao = new EmpleadoDAOMysqlImpl();
		
		// como llego a la base de datos si quiero pedir datos de un empleado?
		
		Empleado em;
       
		em = new Empleado(Long.parseLong(dni),nombre,apellido, Long.parseLong(departamento));
		// si no usamos try catch podemos arriba poner throws Exception
		try {
			 dao.create(em);
			 req.setAttribute("success", List.of("Alta de empleado exitosa"));
		}catch (Exception e) {
			//si falla volver al nuevo.jsp
			e.printStackTrace();
			
		 }
		//ahora redirect!!!!
		getServletContext().getRequestDispatcher("/FindAllEmpleadoController").forward(req, resp);
		
		
		
		
	}

}
