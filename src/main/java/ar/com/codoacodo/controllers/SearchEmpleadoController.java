package ar.com.codoacodo.controllers;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.com.codoacodo.dao.iEmpleadoDAO;
import ar.com.codoacodo.dao.implement.EmpleadoDAOMysqlImpl;
import ar.com.codoacodo.domain.Empleado;

@WebServlet("/SearchEmpleadoController")
public class SearchEmpleadoController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//buscar en la db empleados
		//interface = new class que implementa la interface
		 iEmpleadoDAO dao = new EmpleadoDAOMysqlImpl();
		
		//obtengo la clave enviado desde el formulario que esta en navbar.jsp 
		String clave = req.getParameter("claveBusquedaE");
		
		//FALTAN VALIDACIONES!!!
		
		//busco!
		List<Empleado> empleado;
		try {
			empleado = dao.search(clave);
		} catch (Exception e) {
			empleado = List.of();//crea una lista vacia
			e.printStackTrace();
		}
		
		//guardar en el request, los datos que encontre en la busqueda
		//antes de irme a la nueva pagina: guardo en el request los datos que puede necesitar la JSP
		//clave, valor
		req.setAttribute("listadoE", empleado);
		
		//este bloque de codigo lo vamos a usar en todos lados
		getServletContext().getRequestDispatcher("/listadoE.jsp").forward(req, resp);
	}
}