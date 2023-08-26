<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<link rel="icon" type="image/x-icon" href="Image/Insignia_CentroMujeres.jpg">
<title>Usuarios</title>
<link href="css/styles.css" rel="stylesheet" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://unpkg.com/xlsx/dist/xlsx.full.min.js"></script>
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
					<h1 class="mt-4">Profesores CRUD</h1>
					<div class="card-body">
						<form method="post" action="#">
							<div class="mb-2 row">
								<div class="col-sm d-none">
									<button type="button" class="btn d-none" id="btnActualizar"
										name="btnActualizar">Actualizar</button>
								</div>



								<div class="col-sm-4">
									<input type="text" class="form-control" id="name_teacher" name="names"
										placeholder="Ingrese nombre">
								</div>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="last_name"
										name="last_name" placeholder="Ingrese apellido">
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
							<i class="fa-sharp fa-light fa-book-open-reader"></i> Registros
						</div>
						<div class="card-body">
							<button onclick="exportToCSV()">Exportar a CSV</button>
							<button onclick="exportToExcel()">Exportar a Excel</button>
							<button type="button" class="cancel-button"
							 onclick="window.location.href='exportpdf-usuario.jsp'"
							 style="background-color: #FF4136; border: none;
							  color: white; text-align: center; text-decoration:
							   none; display: inline-block; font-size: 14px;
							    margin-right: 6px; cursor: pointer; border-radius:
							     4px;">Exportar a PDF</button>
							     
							     
							<table class="table caption-top">
								<thead>
									<tr>
										<th scope="col">#</th>
										<th scope="col">Nombre</th>
										<th scope="col">Apellido</th>
										<th scope="col">Usuario</th>
										<th scope="col">Contrase�a</th>
										<th scope="col">Accion</th>
									</tr>
								</thead>
								<tbody id="detalleTabla">
								</tbody>
							</table>
						</div>
					</div>

					<!-- Formulario de edicion de registro -->
					<div class="card" id="divRegistro" style="display: none;">
						<div class="card-header">
							<i class="fa-solid fa-list"></i> Formulario
						</div>
						<div class="card-body">
							<form class="row g-3 needs-validation" novalidate>

								<input type="hidden" id="accion" name="accion">

								<div class="col-md-4 d-none">
									<label for="frmId_teacher" class="form-label">ID</label> <input
										type="text" class="form-control" id="frmId_teacher" required>
								</div>


								<div class="col-md-4">
									<label for="frmName_teacher" class="form-label">Nombre</label> <input
										type="text" class="form-control" id="frmName_teacher" value=""
										required>
									<div class="valid-feedback">�El nombre es correcto!</div>
									<div class="invalid-feedback">Por favor, coloque un
										nombre valido.</div>
								</div>



								<div class="col-md-4">
									<label for="frmLast_name" class="form-label">Apellido</label> <input
										type="text" class="form-control" id="frmLast_name" required>
									<div class="valid-feedback">�El apellido es correcto!</div>
									<div class="invalid-feedback">Por favor, coloque un
										apellido valido.</div>
								</div>


								<div class="col-md-4">
									<label for="frmUser_teacher" class="form-label">Usuario</label> <input
										type="text" class="form-control" id="frmUser_teacher" required>
									<div class="valid-feedback">�El apellido es correcto!</div>
									<div class="invalid-feedback">Por favor, coloque un
										apellido valido.</div>
								</div>


								<div class="col-md-3">
									<label for="frmPassword_teacher" class="form-label">Contrase�a
									</label> <input type="text" class="form-control"
										id="frmPassword_teacher" maxlength="9" required>
									<div class="valid-feedback">�El documento es correcto!</div>
									<div class="invalid-feedback">Por favor, coloque un
										número de documento valido.</div>
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
	let frmId_teacher = document.getElementById('frmId_teacher');
	let frmName_teacher = document.getElementById('frmName_teacher');
	let frmLast_name = document.getElementById('frmLast_name');
	let frmUser_teacher = document.getElementById('frmUser_teacher');
	let frmPassword_teacher = document.getElementById('frmPassword_teacher');

	// Programar los controles
	btnBuscar.addEventListener("click", fnBtnBuscar);
	btnNuevo.addEventListener("click", fnBtnNuevo);
	btnProcesar.addEventListener("click", fnBtnProcesar);
	btnActualizar.addEventListener("click", fnBtnActualizar);

	// Funcion fnEditar
	function fnEditar(id_teacher) {
		document.getElementById("accion").value = ACCION_EDITAR;
		fnCargarForm(id_teacher);
		fnEstadoFormulario(ACCION_EDITAR);
		document.getElementById("divResultado").style.display = "none";
		document.getElementById("divRegistro").style.display = "block";
	}

	// Funcion fnEliminar
	function fnEliminar(id_teacher) {
		Swal.fire({
			title: '�Estas seguro?',
			  text: "No podras revertir esto!",
			  icon: 'Advertencia',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Si, eliminalo!'
			}).then((result) => {
			  if (result.isConfirmed) {
			    Swal.fire(
			      'Eliminado!',
			      'Sus datos han sido eliminados',
			      '�xito'
		    )
			document.getElementById("accion").value = ACCION_ELIMINAR;
			fnCargarForm(id_teacher);
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
		datos += "&id_teacher=" + document.getElementById("frmId_teacher").value;
		datos += "&name_teacher=" + document.getElementById("frmName_teacher").value;
		datos += "&last_name=" + document.getElementById("frmLast_name").value;
		datos += "&user_teacher=" + document.getElementById("frmUser_teacher").value;
		datos += "&password_teacher=" + document.getElementById("frmPassword_teacher").value;
		let xhr = new XMLHttpRequest();
		xhr.open("POST", "TeacherProcesar", true);
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
		let name_teacher = document.getElementById("name_teacher").value;
		let last_name = document.getElementById("last_name").value;
		let url = "TeacherBuscar?name_teacher=" + name_teacher + "&last_name=" + last_name;
		let xhttp = new XMLHttpRequest();
		xhttp.open("GET", url, true);
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				let respuesta = xhttp.responseText;
				arreglo = JSON.parse(respuesta);
				let detalleTabla = "";
				arreglo.forEach(function(item) {
							detalleTabla += "<tr>";
							detalleTabla += "<td>" + item.id_teacher + "</td>";
							detalleTabla += "<td>" + item.name_teacher + "</td>";
							detalleTabla += "<td>" + item.last_name + "</td>";
							detalleTabla += "<td>" + item.user_teacher + "</td>";
							detalleTabla += "<td>" + item.password_teacher + "</td>";
							detalleTabla += "<td>";
							detalleTabla += "<a class='btn btn-success' href='javascript:fnEditar(" + item.id_teacher + ");'><i class='fa-solid fa-pen'></i></a> ";
							detalleTabla += "<a class='btn btn-danger' href='javascript:fnEliminar(" + item.id_teacher + ");'><i class='fa-solid fa-trash'></i></a>";
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
		xhttp.open("GET", "TeacherActualizar", true);
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				let respuesta = xhttp.responseText;
				arreglo = JSON.parse(respuesta);
				let detalleTabla = "";
				arreglo.forEach(function(item) {
							detalleTabla += "<tr>";
							detalleTabla += "<td>" + item.id_teacher + "</td>";
							detalleTabla += "<td>" + item.name_teacher + "</td>";
							detalleTabla += "<td>" + item.last_name + "</td>";
							detalleTabla += "<td>" + item.user_teacher + "</td>";
							detalleTabla += "<td>" + item.password_teacher + "</td>";
							detalleTabla += "<td>";
							detalleTabla += "<a class='btn btn-success' href='javascript:fnEditar(" + item.id_teacher + ");'><i class='fa-solid fa-pen'></i></a> ";
							detalleTabla += "<a class='btn btn-danger' href='javascript:fnEliminar(" + item.id_teacher + ");'><i class='fa-solid fa-trash'></i></a>";
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
	
	function fnCargarForm(id_teacher){
		arreglo.forEach(function(item) {
			if(item.id_teacher == id_teacher){
				frmId_teacher.value = item.id_teacher;
				frmName_teacher.value = item.name_teacher;
				frmLast_name.value = item.last_name;
				frmUser_teacher.value = item.user_teacher;
				frmPassword_teacher.value = item.password_teacher;
				return true;
			}
		});
	}
	
	function fnEstadoFormulario(estado){
		frmName_teacher.disabled = (estado==ACCION_ELIMINAR)
		frmLast_name.disabled = (estado==ACCION_ELIMINAR)
		frmUser_teacher.disabled = (estado==ACCION_ELIMINAR)
		frmPassword_teacher.disabled = (estado==ACCION_ELIMINAR)
		if(estado==ACCION_NUEVO){
			frmId_teacher.value = "0";
			frmName_teacher.value = "";
			frmLast_name.value = "";
			frmUser_teacher.value = "";
			frmPassword_teacher.value = "";
		}
	}
	
	function fnValidar(){
		
		return true;
	}
	
	
</script>

	<!-- VALIDACION CAMPO NOMBRE -->
	<script>
		// Obtener el campo de entrada de nombres
    	var frmName_teacherInput = document.getElementById('frmName_teacher');

		// Agregar un event listener para el evento 'input'
		    frmName_teacherInput.addEventListener('input', function(event) {
			// Obtener el valor actual del campo de nombres
		  	var name = event.target.value;

			// Expresión regular para validar solo letras y espacios
			var regex = /^[A-Za-z\s]+$/;

			// Validar el valor ingresado
			if (name === '') {
				// El campo está vacío
				frmName_teacherInput.classList.remove('is-valid');
				frmName_teacherInput.classList.remove('is-invalid');
			} else if (regex.test(name)) {
				// El valor es válido
				frmName_teacherInput.classList.remove('is-invalid');
				frmName_teacherInput.classList.add('is-valid');
			} else {
				// El valor es inválido
				frmName_teacherInput.classList.remove('is-valid');
				frmName_teacherInput.classList.add('is-invalid');
	        }
		});
	</script>


	<!-- VALIDACION CAMPO APELLIDO -->
	<script>
    // Obtener el campo de entrada de apellido
    var frmLastNameInput = document.getElementById('frmLast_name');

    // Agregar un event listener para el evento 'input'
    frmLastNameInput.addEventListener('input', function(event) {
        // Obtener el valor actual del campo de apellido
        var lastName = event.target.value;

        // Expresión regular para validar solo letras y espacios
        var regex = /^[A-Za-z\s]+$/;

        // Validar el valor ingresado
        if (lastName === '') {
            // El campo está vacío
            frmLastNameInput.classList.remove('is-valid');
            frmLastNameInput.classList.remove('is-invalid');
        } else if (regex.test(lastName)) {
            // El valor es válido
            frmLastNameInput.classList.remove('is-invalid');
            frmLastNameInput.classList.add('is-valid');
        } else {
            // El valor es inválido
            frmLastNameInput.classList.remove('is-valid');
            frmLastNameInput.classList.add('is-invalid');
        }
    });
</script>

	
	<!-- VALIDACION CAMPO USUARIO -->
	<script>
    // Obtener el campo de entrada de apellido
    var frmUserTeacherInput = document.getElementById('frmUser_teacher');

    // Agregar un event listener para el evento 'input'
    frmUserTeacherInput.addEventListener('input', function(event) {
        // Obtener el valor actual del campo de apellido
        var userTeacher = event.target.value;

        // Expresión regular para validar solo letras y espacios
        var regex = /^[A-Za-z\s]+$/;

        // Validar el valor ingresado
        if (userTeacher === '') {
            // El campo está vacío
            frmUserTeacherInput.classList.remove('is-valid');
            frmUserTeacherInput.classList.remove('is-invalid');
        } else if (regex.test(userTeacher)) {
            // El valor es válido
            frmUserTeacherInput.classList.remove('is-invalid');
            frmUserTeacherInput.classList.add('is-valid');
        } else {
            // El valor es inválido
            frmUserTeacherInput.classList.remove('is-valid');
            frmUserTeacherInput.classList.add('is-invalid');
        }
    });
</script>

	<!-- VALIDACION CAMPO CONTRASE�A -->
	<script>
    // Obtener el campo de entrada de apellido
    var frmPasswordTeacherInput = document.getElementById('Password_teacher');

    // Agregar un event listener para el evento 'input'
    frmPasswordTeacherInput.addEventListener('input', function(event) {
        // Obtener el valor actual del campo de apellido
        var passwordTeacher = event.target.value;

        // Expresión regular para validar solo letras y espacios
        var regex = /^[A-Za-z\s]+$/;

        // Validar el valor ingresado
        if (passwordTeacher === '') {
            // El campo está vacío
            frmPasswordTeacherInput.classList.remove('is-valid');
            frmPasswordTeacherInput.classList.remove('is-invalid');
        } else if (regex.test(passwordTeacher)) {
            // El valor es válido
            frmPasswordTeacherInput.classList.remove('is-invalid');
            frmPasswordTeacherInput.classList.add('is-valid');
        } else {
            // El valor es inválido
            frmPasswordTeacherInput.classList.remove('is-valid');
            frmPasswordTeacherInput.classList.add('is-invalid');
        }
    });
</script>
	

	<!-- VALIDACION CAMPO CELULAR 
	<script>
    // Obtener el campo de entrada de Celular
    var celularInput = document.getElementById('frmCellphone');

    // Agregar un event listener para el evento 'input'
    celularInput.addEventListener('input', function(event) {
        // Obtener el valor actual del campo de Celular
        var celular = event.target.value.trim();

        // Validar el valor ingresado
        if (/^9\d{0,8}$/.test(celular) && celular.length === 9) {
            // El valor es válido
            celularInput.classList.remove('is-invalid');
            celularInput.classList.add('is-valid');
        } else {
            // El valor es inválido
            celularInput.classList.remove('is-valid');
            celularInput.classList.add('is-invalid');
        }
    });
</script> -->
	<script>
  function exportToExcel() {
    // Obtener la referencia a la tabla
    const tabla = document.getElementById('detalleTabla');

    // Crear una nueva hoja de cálculo de Excel
    const workbook = XLSX.utils.book_new();

    // Crear una nueva hoja en el libro de Excel
    const sheet = XLSX.utils.table_to_sheet(tabla);

    // Agregar la hoja al libro de Excel
    XLSX.utils.book_append_sheet(workbook, sheet, 'Datos');

    // Generar el archivo Excel
    const excelBuffer = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' });

    // Convertir el archivo Excel a un objeto Blob
    const blob = new Blob([excelBuffer], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });

    // Crear un enlace para descargar el archivo Excel
    const enlace = document.createElement('a');
    enlace.href = window.URL.createObjectURL(blob);
    enlace.download = 'datos.xlsx';

    // Simular un clic en el enlace para iniciar la descarga
    enlace.click();
  }
</script>
	<script>
  function exportToCSV() {
    // Obtener la referencia a la tabla
    const tabla = document.getElementById('detalleTabla');

    // Crear una variable para almacenar los datos CSV
    let csv = '';

    // Recorrer las filas de la tabla
    for (let i = 0; i < tabla.rows.length; i++) {
      const fila = tabla.rows[i];

      // Recorrer las celdas de la fila
      for (let j = 0; j < fila.cells.length; j++) {
        const celda = fila.cells[j];

        // Agregar el valor de la celda al CSV, separado por comas
        csv += celda.innerText + ',';
      }

      // Agregar un salto de línea al final de cada fila
      csv += '\n';
    }

    // Crear un enlace para descargar el archivo CSV
    const enlace = document.createElement('a');
    enlace.href = 'data:text/csv;charset=utf-8,' + encodeURIComponent(csv);
    enlace.download = 'Alumnado_de_SRC.csv';

    // Simular un clic en el enlace para iniciar la descarga
    enlace.click();
  }
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

	  // Crear una nueva hoja de cálculo de Excel
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