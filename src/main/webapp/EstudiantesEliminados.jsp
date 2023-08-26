<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<link rel="icon" type="image/x-icon" href="Image/Insignia-SRC.png">
<title>Estudiantes</title>
<link href="css/styles.css" rel="stylesheet" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
	crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
	<jsp:include page="navbar.jsp"></jsp:include>
	<div id="layoutSidenav">
		<jsp:include page="BarraLateral.jsp"></jsp:include>
		<div id="layoutSidenav_content">
			<main>
				<div class="container-fluid px-4">
					<h1 class="mt-4">Estudiantes Inactivas</h1>
					<div class="card-body">
						<form method="post" action="#">
							<div class="mb-2 row">
								<div class="col-sm d-none">
									<button type="button" class="btn d-none" id="btnActualizar"
										name="btnActualizar">Actualizar</button>
								</div>
							</div>
						</form>
					</div>
					<div class="card mb-4" id="divResultado">
						<div class="card-header">
							<i class="fa-sharp fa-light fa-book-open-reader"></i> Registros
						</div>
						<div class="card-body">
							<table class="table caption-top">
								<thead>
									<tr>
										<th scope="col">#</th>
										<th scope="col">Nombre</th>
										<th scope="col">Apellido</th>
										<th scope="col">Tipo Documento</th>
										<th scope="col">Nº Documento</th>
										<th scope="col">Grado</th>
										<th scope="col">Acción</th>
									</tr>
								</thead>
								<tbody id="detalleTabla">
								</tbody>
							</table>
						</div>
					</div>
					<div class="card" id="divRegistro" style="display: none;">
						<div class="card-header">
							<i class="fa-solid fa-list"></i> Formulario
						</div>
						<div class="card-body">
							<form class="row g-3 needs-validation" novalidate>
								<input type="hidden" id="accion" name="accion">
								<div class="col-md-4 d-none">
									<label for="frmId_student" class="form-label">ID</label> <input
										type="text" class="form-control" id="frmId_student" required>
								</div>
								<div class="col-md-4">
									<label for="frmNames_student" class="form-label">Nombre</label> <input
										type="text" class="form-control" id="frmNames_student" required>
									<div class="valid-feedback">¡Se ve bien!</div>
									<div class="invalid-feedback">Por favor, coloque algo
										válido.</div>
								</div>
								<div class="col-md-4">
									<label for="frmLast_namestudent" class="form-label">Apellido</label> <input
										type="text" class="form-control" id="frmLast_namestudent" required>
									<div class="valid-feedback">¡Se ve bien!</div>
									<div class="invalid-feedback">Por favor, coloque algo
										válido.</div>
								</div>
								<div class="col-md-3">
									<label for="frmType_document" class="form-label">Tipo
										Documento</label> <select class="form-select" id="frmType_document"
										required>
										<option selected disabled value="">Elige...</option>
										<option value="DNI">DNI</option>
										<option value="CNE">CNE</option>
									</select>
									<div class="invalid-feedback">Seleccione un tipo de
										documento.</div>
								</div>
								<div class="col-md-3">
									<label for="frmNumber_document" class="form-label">Nº
										Documento</label> <input type="number" class="form-control"
										id="frmNumber_document" required>
									<div class="valid-feedback">¡Se ve bien!</div>
									<div class="invalid-feedback">Por favor, coloque algo
										válido.</div>
								</div>
								<div class="row mb-3" id='grupo_grado'>
									<label for="frmGrade_id" class="col-sm-2 col-form-label">Grado</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" maxlength="1"
											id="frmGrade_id" name="grado">
										<div class="valid-feedback">¡El grado es correcto!</div>
										<div class="invalid-feedback">El campo Grado solo admite
											número del 1° al 5°.</div>
									</div>
								</div>
								
								<div class="col-12">
									<button class="btn btn-primary" id="btnProcesar" type="submit">Enviar
										formulario</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</main>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="js/scripts.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script>
	
	// Constantes del CRUD
	const ACCION_RESTAURAR = "RESTAURAR";
	const ACCION_ELIMINAR = "ELIMINATE";

	// Arreglo de registros
	let arreglo = [];
	
	// Acceder a los controles
	let btnProcesar = document.getElementById("btnProcesar");
	let btnActualizar = document.getElementById("btnActualizar");
	
	// Campos del formulario
	let accion = document.getElementById('accion');
	let frmId_student = document.getElementById('frmId_student');
	let frmNames_student = document.getElementById('frmNames_student');
	let frmLast_namestudent = document.getElementById('frmLast_namestudent');
	let frmType_document = document.getElementById('frmType_document');
	let frmNumber_document = document.getElementById('frmNumber_document');
	let frmGrade_id = document.getElementById('frmGrade_id');

	// Programar los controles
	btnProcesar.addEventListener("click", fnBtnProcesar);
	btnActualizar.addEventListener("click", fnBtnActualizar);

	// Funcion fnEditar
	function fnRestaurar(id_student) {
		Swal.fire(
		  'Good job!',
		  'You clicked the button!',
		  'success'
		)
		document.getElementById("accion").value = ACCION_RESTAURAR;
		fnCargarForm(id_student);
		fnBtnProcesar();
		setTimeout(fnBtnActualizar, 1000);
	}

	// Funcion fnEliminar
	function fnEliminar(id_student) {
		Swal.fire({
		  title: 'Are you sure?',
		  text: "You won't be able to revert this!",
		  icon: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: 'Yes, delete it!'
		}).then((result) => {
		  if (result.isConfirmed) {
		    Swal.fire(
		      'Deleted!',
		      'Your file has been deleted.',
		      'success'
		    )
			document.getElementById("accion").value = ACCION_ELIMINAR;
			fnCargarForm(id_student);
			fnBtnProcesar();
			setTimeout(fnBtnActualizar, 1000);
		  }
		})
	}

	// Funcion fnBtnProcesar
	function fnBtnProcesar() {
		if(!fnValidar()){
			return;
		}
		let datos = "accion=" + document.getElementById("accion").value;
		datos += "&id_student=" + document.getElementById("frmId_student").value;
		datos += "&names_student=" + document.getElementById("frmNames_student").value;
		datos += "&last_namestudent=" + document.getElementById("frmLast_namestudent").value;
		datos += "&type_document=" + document.getElementById("frmType_document").value;
		datos += "&number_document=" + document.getElementById("frmNumber_document").value;
		datos += "&grade_id=" + document.getElementById("frmGrade_id").value;
		let xhr = new XMLHttpRequest();
		xhr.open("POST", "StudentProcesar", true);
		xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4 && xhr.status === 200) {
				console.log(xhr.responseText);
			}
		};
		xhr.send(datos);
	}
	
	function fnBtnActualizar() {
		let xhttp = new XMLHttpRequest();
		xhttp.open("GET", "StudentHistorial", true);
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				let respuesta = xhttp.responseText;
				arreglo = JSON.parse(respuesta);
				let detalleTabla = "";
				arreglo.forEach(function(item) {
							detalleTabla += "<tr>";
							detalleTabla += "<td>" + item.id_student + "</td>";
							detalleTabla += "<td>" + item.names_student + "</td>";
							detalleTabla += "<td>" + item.last_namestudent + "</td>";
							detalleTabla += "<td>" + item.type_document + "</td>";
							detalleTabla += "<td>" + item.number_document + "</td>";
							detalleTabla += "<td>" + item.grade_id + "</td>";
							detalleTabla += "<td>";
							detalleTabla += "<a class='btn btn-success' href='javascript:fnRestaurar(" + item.id_student + ");'><i class='fa-solid fa-trash-arrow-up'></i></a> ";
							detalleTabla += "<a class='btn btn-danger' href='javascript:fnEliminar(" + item.id_student + ");'><i class='fa-solid fa-trash'></i></a>";
							detalleTabla += "</td>";
							detalleTabla += "</tr>";
						});
				document.getElementById("detalleTabla").innerHTML = detalleTabla;
				document.getElementById("divResultado").style.display = "block";
				document.getElementById("divRegistro").style.display = "none";
			}
		};
		xhttp.send();
	}
	
	fnBtnActualizar();
	
	function fnCargarForm(id_student){
		arreglo.forEach(function(item) {
			if(item.id_student == id_student){
				frmId_student.value = item.id_student;
				frmNames_student.value = item.names_student;
				frmLast_namestudent.value = item.last_namestudent;
				frmType_document.value = item.type_document;
				frmNumber_document.value = item.number_document;
				frmGrade_id.value = item.grade_id;
				return true;
			}
		});
	}
	
	function fnValidar(){
		
		return true;
	}
</script>
</body>
</html>