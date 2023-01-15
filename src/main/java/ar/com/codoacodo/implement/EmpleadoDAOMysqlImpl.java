package ar.com.codoacodo.implement;

import ar.com.codoacodo.dao.iEmpleadoDAO;
import ar.com.codoacodo.db.AdministradorDeConexiones;
import ar.com.codoacodo.domain.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

// la interface List tiene una serie de metodos que son 
// add(elemento)
//remove(elemento)
// me permite trabajar como si fueran vectores dinamicamente como en js, dependiendo de la memoria



// en la clase que creamos, heredamos de una interface, la interfaceDAO que tiene los
//metodos para interactuar con la base
public class EmpleadoDAOMysqlImpl implements iEmpleadoDAO {

	@Override
	public Empleado getById(Long dni)  throws Exception{
		//-1 necesito la conection a la base
		Connection connection = AdministradorDeConexiones.getConnection();
		//2 - arma el statement
		String sql = "select * from empleados where dni = " +dni;
	   
		Statement statement  = connection.createStatement();
		
		//3 - obtengo el resulSet
		ResultSet resultset = statement.executeQuery(sql);
		// El resultset devuelve un registro de una tabla 
		
	     // primero verifico si hay datos 
		
		if (resultset.next()){
			// obtengo el dato del campo id
			Long dniBd = resultset.getLong("dni");
			String nombreBd = resultset.getString("nombre");
			String apellidoBd = resultset.getString("apellido");
			Long deptoBd = resultset.getLong("departamento");
			return new Empleado(dniBd,nombreBd,apellidoBd,deptoBd);
			
		}
		cerrar(connection);
		return null; // si no hay resultset entonces no devuelve nada
	}

	@Override
	public List<Empleado> findAll() throws Exception {
		//-1 necesito la conection a la base
		Connection connection = AdministradorDeConexiones.getConnection();
		//2 - arma el statement
	    String sql = "select * from empleados";
			   
		Statement statement  = connection.createStatement();
				
		//3 - obtengo el resulSet
		ResultSet resultset = statement.executeQuery(sql);
		// El resultset devuelve un registro de una tabla 
				
	     // primero verifico si hay datos 
		    // creo una lista de departamentos
			List<Empleado> empleados = new ArrayList<Empleado>();	
			
			// mientras encontremos resultados de la base 
			while (resultset.next()){
			// obtengo el dato del campo dni
			Long dniBd = resultset.getLong("dni");
			String nombreBd = resultset.getString("nombre");
			String apellidoBd = resultset.getString("apellido");
			Long deptoBd = resultset.getLong("departamento");
			// creamos un departamento y lo agregamos a la lista 
			Empleado em = new Empleado(dniBd,nombreBd,apellidoBd,deptoBd);
			empleados.add(em);
					
			}
			cerrar(connection);
		   // devolvemos departamentos		
		   return empleados; //
	}

	@Override
	public void delete(Long dni) throws Exception {
	   //-1 necesito la conection a la base
		Connection connection = AdministradorDeConexiones.getConnection();
		//2 - arma el statement
	     String sql = "DELETE FROM empleados WHERE DNI=" + dni;
	 	 Statement statement  = connection.createStatement();
	 	//3 -devuelve un entero devuelve 1 o 0, pero no hace falta confirmar para este caso 
		  statement.executeUpdate(sql);
		  cerrar(connection);
		 
	}

	@Override
	public void update(Empleado empleado) throws Exception {
		// creo un Departamento con los datos modificados del departemento 
		  
		//-1 necesito la conection a la base
		Connection connection = AdministradorDeConexiones.getConnection();
		//2 - arma el statement
		 String sql = "update empleados set nombre = ?, apellido = ?, departamento = ? where dni= ?"  ;
		 PreparedStatement statement  = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	     statement.setString(1,empleado.getNombre());
	     statement.setString(2,empleado.getApellido());
		 statement.setLong(3,empleado.getDepto());
		 statement.setLong(4,empleado.getDni());
		//3 -devuelve un entero devuelve 1 o 0, pero no hace falta confirmar para este caso 
		 statement.execute();
		
		 cerrar(connection);
	}

	@Override
	public void create(Empleado newEmpleado) throws Exception {
		
        
		//-1 necesito la conection a la base
		Connection connection = AdministradorDeConexiones.getConnection();
		//2 - arma el statement
		 String sql = "insert into empleados (dni, nombre, apellido, departamento) values (?,?,?,?)" ;
		 PreparedStatement statement  = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		 statement.setLong(1,newEmpleado.getDni());
		 statement.setString(2,newEmpleado.getNombre());
		 statement.setString(3,newEmpleado.getApellido());
		 statement.setLong(4,newEmpleado.getDepto());
		//3 -devuelve un entero devuelve 1 o 0, pero no hace falta confirmar para este caso 
		 statement.execute();
		 
		 ResultSet res = statement.getGeneratedKeys(); // RETORNA LA KEY QUE SE GENERO
		 if (res.next()) {
			 System.out.println("Se creo el empleado correctamente");
		 }
		 cerrar(connection);
	}
	@Override
	public List<Empleado> search(String clave) throws Exception {
		// 1 - necesito la Connection
		Connection connection = AdministradorDeConexiones.getConnection();

		// 2 - arma el statement
		String sql = "SELECT * FROM empleados WHERE NOMBRE LIKE ?";
		PreparedStatement statement = connection.prepareStatement(sql);

		//setear el valor que va en remplazo del ?
		statement.setString(1, "%" + clave + "%");
		
		// 3 - resultset
		ResultSet resultSet = statement.executeQuery();

		// Interface i = new ClaseQueImplementaLaInterface();
		List<Empleado> empleado = new ArrayList<Empleado>();

		// verifico si hay datos
		while (resultSet.next()) {
			empleado.add(this.crearEmpleado(resultSet));
		}
		
		cerrar(connection);
		
		return empleado;
	}
	
	private void cerrar(Connection con) throws Exception{
		con.close();
	}
		
	private Empleado crearEmpleado(ResultSet resultSet) throws Exception {
		// obtengo el dato del campo id
		Long dniDb = resultSet.getLong("dni");
		String nombre = resultSet.getString("nombre");
		String apellido = resultSet.getString("apellido");
		Long departamento = resultSet.getLong("departamento");
		

		return new Empleado(dniDb, nombre, apellido, departamento);
	}
	
	// implementar el nuevo metodo que busca por el nombre y que devuelve una lista de departamentos

}
