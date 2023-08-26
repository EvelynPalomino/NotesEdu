<div id="layoutSidenav_nav">
	<nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
		<div class="sb-sidenav-menu">
			<div class="nav">
				<div class="sb-sidenav-menu-heading">Maestros</div>
				<!-- Estudiantes -->
				<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
					data-bs-target="#collapseEstudiantes" aria-expanded="false"
					aria-controls="#collapseEstudiantes">
					<div class="sb-nav-link-icon">
						<i class="fa-sharp fa-light fa-book"></i>
					</div> Estudiante
					<div class="sb-sidenav-collapse-arrow">
						<i class="fas fa-angle-down"></i>
					</div>
				</a>
				<div class="collapse" id="collapseEstudiantes"
					aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
					<nav class="sb-sidenav-menu-nested nav">
						<a class="nav-link" href="Estudiante.jsp">Activos</a> <a
							class="nav-link" href="EstudiantesEliminados.jsp">Inactivos</a>
					</nav>
				</div>
				<!-- Usuarios -->
				<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
					data-bs-target="#collapseUsuarios" aria-expanded="false"
					aria-controls="#collapseUsuarios">
					<div class="sb-nav-link-icon">
						<i class="fa-sharp fa-light fa-user-tie"></i>
					</div> Profesores
					<div class="sb-sidenav-collapse-arrow">
						<i class="fas fa-angle-down"></i>
					</div>
				</a>
				<div class="collapse" id="collapseUsuarios"
					aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
					<nav class="sb-sidenav-menu-nested nav">
						<a class="nav-link" href="Teacher.jsp">Activos</a> <a
							class="nav-link" href="TeacherEliminados.jsp">Inactivos</a>
					</nav>
				</div>
				
				<!-- Curso -->
				<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
					data-bs-target="#collapseCursos" aria-expanded="false"
					aria-controls="#collapseCursos">
					<div class="sb-nav-link-icon">
						<i class="fa-sharp fa-light fa-user-tie"></i>
					</div> Cursos y/o Materias
					<div class="sb-sidenav-collapse-arrow">
						<i class="fas fa-angle-down"></i>
					</div>
				</a>
				<div class="collapse" id="collapseCursos"
					aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
					<nav class="sb-sidenav-menu-nested nav">
						<a class="nav-link" href="Course.jsp">Activos</a> <a
							class="nav-link" href="CourseEliminados.jsp">Inactivos</a>
					</nav>
				</div>
				
				<!-- Grado -->
				<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
					data-bs-target="#collapseGrados" aria-expanded="false"
					aria-controls="#collapseGrados">
					<div class="sb-nav-link-icon">
						<i class="fa-sharp fa-light fa-user-tie"></i>
					</div> Grados
					<div class="sb-sidenav-collapse-arrow">
						<i class="fas fa-angle-down"></i>
					</div>
				</a>
				<div class="collapse" id="collapseGrados"
					aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
					<nav class="sb-sidenav-menu-nested nav">
						<a class="nav-link" href="Grade.jsp">Activos</a> <a
							class="nav-link" href="GradeEliminados.jsp">Inactivos</a>
					</nav>
				</div>

				<!-- Transaccionales -->
				<div class="sb-sidenav-menu-heading">Transaccionales</div>
				<!-- Categorias -->
				<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
					data-bs-target="#collapseCategorias" aria-expanded="false"
					aria-controls="#collapseCategorias">
					<div class="sb-nav-link-icon">
						<i class="fa-sharp fa-light fa-folder-open"></i>
					</div> Transacciona #1
					<div class="sb-sidenav-collapse-arrow">
						<i class="fas fa-angle-down"></i>
					</div>
				</a>
				<div class="collapse" id="collapseCategorias"
					aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
					<nav class="sb-sidenav-menu-nested nav">
						<a class="nav-link" href="categorias.jsp">Lista</a> <a
							class="nav-link" href="categoriasEliminados.jsp">Eliminados</a>
					</nav>
				</div>
				<!-- Autores -->
				<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
					data-bs-target="#collapseAutores" aria-expanded="false"
					aria-controls="#collapseAutores">
					<div class="sb-nav-link-icon">
						<i class="fa-sharp fa-light fa-folder-open"></i>
					</div> Transacciona #2
					<div class="sb-sidenav-collapse-arrow">
						<i class="fas fa-angle-down"></i>
					</div>
				</a>
				<div class="collapse" id="collapseAutores"
					aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
					<nav class="sb-sidenav-menu-nested nav">
						<a class="nav-link" href="autores.jsp">Lista</a> <a
							class="nav-link" href="autoresEliminados.jsp">Eliminados</a>
					</nav>
				</div>
			</div>
		</div>
	</nav>
</div>