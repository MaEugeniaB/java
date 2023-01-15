<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand p-2 m-2" href="<%=request.getContextPath()%>/index.jsp">INICIO</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
       <li class="nav-item dropdown d-flex w-100 justify-content-end"> 
       
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Listados
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/FindAllEmpleadoController">Listado de Empleados</a>
        </li>
            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/FindAllDepartamentoController">Listado de Departamentos</a>
        </li>
          </ul>
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Cargar Nuevos
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/nuevoE.jsp">Nuevo Empleado</a>
        </li>
            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/nuevo.jsp">Nuevo Departamento</a>
        </li>
          </ul>
        </li>
      </ul>
      <div class="d-flex w-75 justify-content-end"> 
	      <form class="d-flex" action="<%=request.getContextPath()%>/SearchEmpleadoController">
	     		<input  name="claveBusquedaE"  class="form-control me-2" type="search" placeholder="Buscar Nombre Empleado" aria-label="Search">
		        <button class="btn btn-outline-success" type="submit">Buscar</button>
	      </form> 
	       <form class="d-flex" action="<%=request.getContextPath()%>/SearchDepartamentoController">
	     		<input  name="claveBusquedaD"  class="form-control me-2" type="search" placeholder="Buscar Nombre Depto" aria-label="Search">
		        <button class="btn btn-outline-success" type="submit">Buscar</button>
	      </form> 
      </div>
      
    </div>
  </div>
</nav>