<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<link rel="icon" type="image/x-icon" href="Image/Insignia_CentroMujeres.jpg">
<title>Grados</title>

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
					<h1 class="mt-4">Grado CRUD</h1>
					<div class="card-body">
						<form method="post" action="#">
							<div class="mb-2 row">
								<div class="col-sm d-none">
									<button type="button" class="btn d-none" id="btnActualizar"
										name="btnActualizar">Actualizar</button>
								</div>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="academic_level" name="names"
										placeholder="Ingrese el nivel academico">
								</div>
								
								<div class="col-sm-2">
									<button type="button" class="btn btn-primary mb-2"
										id="btnBuscar" name="btnBuscar">Buscar</button>
								</div>
								<div class="col-sm-2">
									<button type="button" class="btn btn-success float-end mb-2"
										id="btnNuevo" name="btnNuevo">Nuevo</button>
								</div>
							</div>
						</form>
					</div>
					<div class="card mb-4" id="divResultado">
						<div class="card-header">
							<i class="fas fa-table me-1"></i> Registros
						</div>
						<div class="card-body">
							<button onclick="exportToCSV()">Exportar a CSV</button>
							<button onclick="exportToExcel()">Exportar a Excel</button>
							<button type="button" class="cancel-button"
							 onclick="window.location.href='export-pdf.jsp'"
							 style="background-color: #FF4136; border: none;
							  color: white; text-align: center; text-decoration:
							   none; display: inline-block; font-size: 14px;
							    margin-right: 6px; cursor: pointer; border-radius:
							     4px;">Exportar a PDF</button>

						
							
							<table class="table caption-top" id="tblEstudiante">
								<thead>
									<tr>
										<th scope="col">#</th>
										<th scope="col">Grado</th>
										<th scope="col">Sección</th>
										<th scope="col">Nivel Academico</th>
										<th scope="col">Tutor (a)</th>
										<th scope="col">Accion</th>
									</tr>
								</thead>
								<tbody id="detalleTabla">
								</tbody>
							</table>
						</div>
					</div>
					<div class="card" id="divRegistro" style="display: none;">
						<div class="card-header">
							<i class="fa-sharp fa-light fa-book-open-reader"></i> Formulario
						</div>
						<div class="card-body">
							<form class="row g-3 needs-validation" novalidate>

								<input type="hidden" id="accion" name="accion">

								<div class="col-md-4 d-none">
									<label for="frmId_grade" class="form-label">ID</label> <input
										type="text" class="form-control" id="frmId_grade" required>
								</div>

								<div class="col-md-4">
									<label for="frmGrade" class="form-label">Grado</label> <select
										class="form-select" id="frmGrade" required>
										<option selected disabled value="">Elige...</option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
									</select>
									<div class="invalid-feedback">Seleccione un Grado.</div>
								</div>
								<div class="col-md-4">
									<label for="frmSection" class="form-label">Sección</label> <select
										class="form-select" id="frmSection" required>
										<option selected disabled value="">Elige...</option>
										<option value="A">A</option>
										<option value="B">B</option>
										<option value="C">C</option>
										<option value="D">D</option>
										<option value="E">E</option>
									</select>
									<div class="invalid-feedback">Seleccione una Sección.</div>
								</div>
								
								<div class="col-md-4">
									<label for="frmAcademic_level" class="form-label">Nivel Academico</label> <select
										class="form-select" id="frmAcademic_level" required>
										<option selected disabled value="">Elige...</option>
										<option value="Inicial">Inicial</option>
										<option value="Primaria">Primaria</option>
										<option value="Secundaria">Secundaria</option>
									</select>
									<div class="invalid-feedback">Seleccione un Nivel Academico.</div>
								</div>
								
								<div class="col-md-4">
									<label for="frmTutor" class="form-label">Tutor (a)</label> <input
										type="text" class="form-control" id="frmTutor" value=""
										required>
									<div class="valid-feedback">¡Se ve bien!</div>
									<div class="invalid-feedback">Por favor, coloque algo
										valido.</div>
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
	<script src="https://unpkg.com/xlsx/dist/xlsx.full.min.js"></script>
	<script src = "https://unpkg.com/jspdf-autotable"></script>
	<script src = "https://unpkg.com/jspdf "></script>
	<script>
	
	
	
	// Constantes del CRUD
	const ACCION_NUEVO = "NUEVO";
	const ACCION_EDITAR = "EDITAR";
	const ACCION_ELIMINAR = "ELIMINAR";

	// Arreglo de registros
	let arreglo = [];
	
	// Acceder a los controles
	let btnBuscar = document.getElementById("btnBuscar");
	let btnNuevo = document.getElementById("btnNuevo");
	let btnProcesar = document.getElementById("btnProcesar");
	let btnActualizar = document.getElementById("btnActualizar");
	
	// Campos del formulario
	let accion = document.getElementById('accion');
	let frmId_grade = document.getElementById('frmId_grade');
	let frmGrade = document.getElementById('frmGrade');
	let frmSection = document.getElementById('frmSection');
	let frmAcademic_level = document.getElementById('frmAcademic_level');
	let frmTutor = document.getElementById('frmTutor');

	// Programar los controles
	btnBuscar.addEventListener("click", fnBtnBuscar);
	btnNuevo.addEventListener("click", fnBtnNuevo);
	btnProcesar.addEventListener("click", fnBtnProcesar);
	btnActualizar.addEventListener("click", fnBtnActualizar);

	// Funcion fnEditar
	function fnEditar(id_grade) {
		document.getElementById("accion").value = ACCION_EDITAR;
		fnCargarForm(id_grade);
		fnEstadoFormulario(ACCION_EDITAR);
		document.getElementById("divResultado").style.display = "none";
		document.getElementById("divRegistro").style.display = "block";
	}

	// Funcion fnEliminar
	function fnEliminar(id_grade) {
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
			fnCargarForm(id_grade);
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
		datos += "&id_grade=" + document.getElementById("frmId_grade").value;
		datos += "&grade=" + document.getElementById("frmGrade").value;
		datos += "&section=" + document.getElementById("frmSection").value;
		datos += "&academic_level=" + document.getElementById("frmAcademic_level").value;
		datos += "&tutor=" + document.getElementById("frmTutor").value;
		let xhr = new XMLHttpRequest();
		xhr.open("POST", "GradeProcesar", true);
		xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4 && xhr.status === 200) {
				console.log(xhr.responseText);
			}
		};
		xhr.send(datos);
	}

	function fnBtnNuevo() {
		document.getElementById("accion").value = ACCION_NUEVO;
		fnEstadoFormulario(ACCION_NUEVO);
		document.getElementById("divResultado").style.display = "none";
		document.getElementById("divRegistro").style.display = "block";
	}

	function fnBtnBuscar() {
		let academic_level = document.getElementById("academic_level").value;
		let url = "GradeBuscar?academic_level=" + academic_level ;
		let xhttp = new XMLHttpRequest();
		xhttp.open("GET", url, true);
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				let respuesta = xhttp.responseText;
				arreglo = JSON.parse(respuesta);
				let detalleTabla = "";
				arreglo.forEach(function(item) {
							detalleTabla += "<tr>";
							detalleTabla += "<td>" + item.id_grade + "</td>";
							detalleTabla += "<td>" + item.grade + "</td>";
							detalleTabla += "<td>" + item.section + "</td>";
							detalleTabla += "<td>" + item.academic_level + "</td>";
							detalleTabla += "<td>" + item.tutor + "</td>";
							detalleTabla += "<td>";
							detalleTabla += "<a class='btn btn-success' href='javascript:fnEditar(" + item.id + ");'><i class='fa-solid fa-pen'></i></a> ";
							detalleTabla += "<a class='btn btn-danger' href='javascript:fnEliminar(" + item.id + ");'><i class='fa-solid fa-trash'></i></a>";
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
	
	function fnBtnActualizar() {
		let xhttp = new XMLHttpRequest();
		xhttp.open("GET", "GradeActualizar", true);
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				let respuesta = xhttp.responseText;
				arreglo = JSON.parse(respuesta);
				let detalleTabla = "";
				arreglo.forEach(function(item) {
							detalleTabla += "<tr>";
							detalleTabla += "<td>" + item.id_grade + "</td>";
							detalleTabla += "<td>" + item.grade + "</td>";
							detalleTabla += "<td>" + item.section + "</td>";
							detalleTabla += "<td>" + item.academic_level + "</td>";
							detalleTabla += "<td>" + item.tutor + "</td>";
							detalleTabla += "<td>";
							detalleTabla += "<a class='btn btn-success' href='javascript:fnEditar(" + item.id_grade + ");'><i class='fa-solid fa-pen'></i></a> ";
							detalleTabla += "<a class='btn btn-danger' href='javascript:fnEliminar(" + item.id_grade + ");'><i class='fa-solid fa-trash'></i></a>";
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
	
	function fnCargarForm(id_grade){
		arreglo.forEach(function(item) {
			if(item.id_grade == id_grade){
				frmId_grade.value = item.id_grade;
				frmGrade.value = item.grade;
				frmSection.value = item.section;
				frmAcademic_level.value = item.academic_level;
				frmTutor.value = item.tutor;
				return true;
			}
		});
	}
	
	function fnEstadoFormulario(estado){
		frmGrade.disabled = (estado==ACCION_ELIMINAR)
		frmSection.disabled = (estado==ACCION_ELIMINAR)
		frmAcademic_level.disabled = (estado==ACCION_ELIMINAR)
		frmTutor.disabled = (estado==ACCION_ELIMINAR)
		if(estado==ACCION_NUEVO){
			frmId_grade.value = "0";
			frmGrade.value = "";
			frmSection.value = "";
			frmAcademic_level.value = "";
			frmTutor.value = "";
		}
	}
	
	$('#exportBtn').click(function() {
		  var doc = document.getElementById("tblEstudiante");
		  newWin= window.open("");
	      newWin.document.write(doc.outerHTML);
	      newWin.print();
	 });
	function fnValidar(){
		
		return true;
	}
	</script>
	<!-- VALIDACION CAMPO TUTOR -->
	<script>
    // Obtener el campo de entrada de nombres
    var frmNamesInput = document.getElementById('frmTutor');

    // Agregar un event listener para el evento 'input'
    frmNamesInput.addEventListener('input', function(event) {
        // Obtener el valor actual del campo de nombres
        var name = event.target.value;

        // ExpresiÃ³n regular para validar solo letras y espacios, incluyendo Ã± y diÃ©resis
        var regex = /^[A-Za-zÃ±Ã‘\u00C0-\u017F\s]+$/;

        // Validar el valor ingresado
        if (name === '') {
            // El campo estÃ¡ vacÃ­o
            frmNamesInput.classList.remove('is-valid');
            frmNamesInput.classList.remove('is-invalid');
        } else if (regex.test(name)) {
            // El valor es vÃ¡lido
            frmNamesInput.classList.remove('is-invalid');
            frmNamesInput.classList.add('is-valid');
        } else {
            // El valor es invÃ¡lido
            frmNamesInput.classList.remove('is-valid');
            frmNamesInput.classList.add('is-invalid');
        }
    });
</script>
	<!-- VALIDACION CAMPO TIPO DE NIVEL -->
	<script>
		// Obtener el campo de entrada de Tipo de Documento
		var tipoDocumentoInput = document.getElementById('frmAcademic_level');

		// Obtener el elemento del check
		var tipoDocumentoIcon = document.getElementById('frmAcademic_level');

		// Agregar un event listener para el evento 'input'
		tipoDocumentoInput.addEventListener('input', function(event) {
			// Obtener el valor actual del campo de Tipo de Documento y eliminar los espacios en blanco
			var tipoDocumento = event.target.value.trim();

			// Validar el valor ingresado
			if (tipoDocumento === '') {
				// El campo estÃ¡ vacÃ­o
				tipoDocumentoInput.classList.remove('is-valid');
				tipoDocumentoInput.classList.remove('is-invalid');
				tipoDocumentoIcon.style.display = 'none';
			} else if (/^[A-Za-zÃ±Ã‘\u00C0-\u017F\s]+$/.test(tipoDocumento)) {
				// El valor es vÃ¡lido: solo letras en mayÃºscula
				tipoDocumentoInput.classList.remove('is-invalid');
				tipoDocumentoInput.classList.add('is-valid');
				tipoDocumentoIcon.style.display = 'inline-block';
			} else {
				// El valor es invÃ¡lido
				tipoDocumentoInput.classList.remove('is-valid');
				tipoDocumentoInput.classList.add('is-invalid');
				tipoDocumentoIcon.style.display = 'none';
			}
		});
	</script>



	<!-- VALIDACION CAMPO GRADO -->
	<script>
    // Obtener el campo de entrada de grado
    var frmGradeInput = document.getElementById('frmGrade');

    // Agregar un event listener para el evento 'input'
    frmGradeInput.addEventListener('input', function(event) {
        // Obtener el valor actual del campo de grado
        var grade = event.target.value;

        // ExpresiÃ³n regular para validar solo nÃºmeros del 1 al 5
        var regex = /^[1-5]$/;

        // Validar el valor ingresado
        if (grade === '') {
            // El campo estÃ¡ vacÃ­o
            frmGradeInput.classList.remove('is-valid');
            frmGradeInput.classList.remove('is-invalid');
        } else if (regex.test(grade)) {
            // El valor es vÃ¡lido
            frmGradeInput.classList.remove('is-invalid');
            frmGradeInput.classList.add('is-valid');
        } else {
            // El valor es invÃ¡lido
            frmGradeInput.classList.remove('is-valid');
            frmGradeInput.classList.add('is-invalid');
        }
    });
</script>


	<!-- VALIDACION CAMPO SECCIÃ“N -->
	<script>
    // Obtener el campo de entrada de secciÃ³n
    var frmSectionInput = document.getElementById('frmSection');

    // Agregar un event listener para el evento 'input'
    frmSectionInput.addEventListener('input', function(event) {
        // Obtener el valor actual del campo de secciÃ³n
        var section = event.target.value;

        // ExpresiÃ³n regular para validar solo las secciones permitidas
        var regex = /^[a-e]$/i;

        // Validar el valor ingresado
        if (section === '') {
            // El campo estÃ¡ vacÃ­o
            frmSectionInput.classList.remove('is-valid');
            frmSectionInput.classList.remove('is-invalid');
        } else if (regex.test(section)) {
            // El valor es vÃ¡lido
            frmSectionInput.classList.remove('is-invalid');
            frmSectionInput.classList.add('is-valid');
        } else {
            // El valor es invÃ¡lido
            frmSectionInput.classList.remove('is-valid');
            frmSectionInput.classList.add('is-invalid');
        }
    });
</script>
	<script>
function exportToExcel() {
	  // Obtener la referencia a la tabla
	  var table = document.querySelector('.table');

	  // Obtener todas las filas de la tabla
	  var rows = Array.from(table.querySelectorAll('tbody tr'));

	  // Crear una matriz para almacenar los datos del archivo de Excel
	  var excelData = [];

	  // Iterar sobre cada fila y obtener los datos de las celdas
	  rows.forEach(function(row) {
	    var rowData = [];
	    var cells = Array.from(row.querySelectorAll('td'));

	    // Obtener el texto de cada celda y agregarlo a la matriz de datos
	    cells.forEach(function(cell) {
	      rowData.push(cell.textContent.trim());
	    });

	    // Agregar la fila de datos a la matriz del archivo de Excel
	    excelData.push(rowData);
	  });

	  // Crear una nueva hoja de cÃ¡lculo de Excel
	  var workbook = XLSX.utils.book_new();

	  // Crear una hoja de trabajo
	  var worksheet = XLSX.utils.aoa_to_sheet(excelData);

	  // Agregar la hoja de trabajo al libro de Excel
	  XLSX.utils.book_append_sheet(workbook, worksheet, 'Tabla');

	  // Generar un archivo de Excel binario
	  var excelBuffer = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' });

	  // Convertir el buffer de Excel a un blob
	  var blob = new Blob([excelBuffer], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });

	  // Crear un enlace temporal para descargar el archivo de Excel
	  var link = document.createElement('a');
	  link.href = window.URL.createObjectURL(blob);
	  link.download = 'Activos.xlsx';

	  // Simular el clic en el enlace para descargar el archivo de Excel
	  link.click();
	}
</script>
	<script>
function exportToCSV() {
	  // Obtener la referencia a la tabla
	  var table = document.querySelector('.table');

	  // Obtener todas las filas de la tabla
	  var rows = Array.from(table.querySelectorAll('tbody tr'));

	  // Crear una matriz para almacenar los datos del CSV
	  var csvData = [];

	  // Iterar sobre cada fila y obtener los datos de las celdas
	  rows.forEach(function(row) {
	    var rowData = [];
	    var cells = Array.from(row.querySelectorAll('td'));

	    // Obtener el texto de cada celda y agregarlo a la matriz de datos
	    cells.forEach(function(cell) {
	      rowData.push(cell.textContent.trim());
	    });

	    // Agregar la fila de datos al CSV
	    csvData.push(rowData.join(','));
	  });

	  // Crear el contenido del archivo CSV
	  var csvContent = csvData.join('\n');

	  // Crear un enlace temporal para descargar el archivo CSV
	  var link = document.createElement('a');
	  link.href = 'data:text/csv;charset=utf-8,' + encodeURI(csvContent);
	  link.target = '_blank';
	  link.download = 'Activos.csv';

	  // Simular el clic en el enlace para descargar el archivo CSV
	  link.click();
}
</script>
</body>
</html>