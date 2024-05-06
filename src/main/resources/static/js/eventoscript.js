//Barra de navegacion
	let sidebarToggle = document.querySelector("#sidebar-toggle");
	
	sidebarToggle.addEventListener("click", function () {
	  document.querySelector("#sidebar").classList.toggle("collapsed");
	});
	
//Establacer min y max de fechas
const hoy = new Date().toISOString().split('T')[0];

document.getElementById("fecha").setAttribute("min", hoy);

//Registrar la nuevo 
	let boton = document.getElementById("btnAceptar");
	boton.addEventListener("click", evento =>
	{
	    newEmpleado();
	});
	
	let newEmpleado = async () =>
	{	
	    let cliente = 
	    {
			"nombreEvento" : document.getElementById("nombreEvento").value,
			"fecha" : document.getElementById("fecha").value,
			"horaInicio" : document.getElementById("horaInicio").value,
			"horaFin" : document.getElementById("horaFin").value,
			"lugar" : document.getElementById("lugar").value,
			"descripcion" : document.getElementById("descripcion").value
		};

	    try 
	    {
            const responseRegistro = await fetch("http://localhost:7777/evento/guardar", {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(cliente),
            });

            if (!responseRegistro.ok) 
            {
				alert("No se pudo registrar nuevo");
            }
			else
			{
            	alert("Se logro guardar");
            	window.location.href = "/mozos/eventos";
			}
	    }
	    catch (error)
	    {
	        console.error('Error durante el registro: ', error);
	    }
	};
	
//Rellenar tablas
	const listEmpleados= async () => 
	{
	  try 
	  {
	    const response = await fetch("http://localhost:7777/evento/vereventos");
	    const empleados = await response.json();
	    let content = ``;
	    empleados.forEach((empleado) => {
	      content += `
	                <tr>
	                    <td class="centered">${empleado.idEvento}</td>
	                    <td class="centered">${empleado.nombreEvento}</td>
	                    <td class="centered">${empleado.fecha}</td>
	                    <td class="centered">${empleado.horaInicio}</td>
	                    <td class="centered">${empleado.horaFin}</td>
	                    <td class="centered">${empleado.lugar}</td>
	                    <td class="centered">${empleado.descripcion}</td>
	                    <td class="centered">
	                    	<button id="edit" class="btn btn-sm btn-primary" onclick="openEditModal('${empleado.idEvento}')"> <i class="fa-solid fa-pencil"></i></button>
	                    </td>
	                </tr>`;
	    });
	    tableBody_eventos.innerHTML = content;
	  } catch (ex) {
	    alert(ex);
	  }
	};
	
	document.addEventListener('DOMContentLoaded', (event) => {
	  listEmpleados();
	});
	
//Editar
async function openEditModal(idEvento) 
{
	try 
	{
    	const response = await fetch(`http://localhost:7777/evento/vereventos/${idEvento}`);
    	if (!response.ok) 
    	{
      		alert("Hubo algun error en el url");
    	}
	    const empleadoDetalles = await response.json();
	
	  	const modalBody = document.getElementById("editModalBody");
	  	modalBody.innerHTML = `
	  	  <div class="mb-3">
		    <label for="editNombre" class="col-form-label">Id Evento:</label>
		    <input type="text" class="form-control" id="editidEvento" value="${empleadoDetalles.idEvento}" readonly/>
		  </div>
  		  <div class="mb-3">
		    <label for="editNombre" class="col-form-label">Nombre Evento:</label>
		    <input type="text" class="form-control" id="editnombreEvento" value="${empleadoDetalles.nombreEvento}"/>
		  </div>
		  <div class="mb-3">
		    <label for="editNombre" class="col-form-label">Fecha:</label>
		    <input type="date" name="fecha" id="editfecha" value="${empleadoDetalles.fecha}"/><br />	
		  </div>
		  <div class="mb-3">
		    <label for="editNombre" class="col-form-label">Hora Inicio:</label>
			<input type="time" name="hora" id="edithoraInicio" value="${empleadoDetalles.horaInicio}" /><br />					
		  </div>
		  <div class="mb-3">
		    <label for="editNombre" class="col-form-label">Hora Fin:</label>
		    <input type="time" name="hora" id="edithoraFin" value="${empleadoDetalles.horaFin}" /><br />
		  </div>
		  <div class="mb-3">
		    <label for="editNombre" class="col-form-label">Lugar:</label>
		    <input type="text" class="form-control" id="editlugar" value="${empleadoDetalles.lugar}"/>
		  </div>
		  <div class="mb-3">
		    <label for="editNombre" class="col-form-label">Descripcion:</label>
		    <input type="text" class="form-control" id="editdescripcion" value="${empleadoDetalles.descripcion}"/>
		  </div>
     	`;
	} 
	catch (error) 
	{
    	console.error('Error durante la obtención de detalles: ', error);
  	}
  		
	const editModal = new bootstrap.Modal(document.getElementById("editModal"));
	editModal.show();
}

//Editar marca (Guardar datos)
let save = document.getElementById("saveChangesBtn");
	save.addEventListener("click", evento =>
	{
	     saveChanges();
	});

async function saveChanges()
{
	const request = 
	{
		"idEvento" : document.getElementById("editidEvento").value,
		"nombreEvento" : document.getElementById("editnombreEvento").value,
		"fecha" : document.getElementById("editfecha").value,
		"horaInicio" : document.getElementById("edithoraInicio").value,
		"horaFin" : document.getElementById("edithoraFin").value,
		"lugar" : document.getElementById("editlugar").value,
		"descripcion" : document.getElementById("editdescripcion").value
	}
	
	try 
	{
	    const response = await fetch(`http://localhost:7777/evento/editar`, {
	      method: 'PUT',
	      headers: {
	        'Content-Type': 'application/json',
	      },
	      body: JSON.stringify(request),
	    });
	
	    if (!response.ok) 
	    {
	      alert("No se pudo modificar");
	    }
		else
		{
			alert("Se guardo la edicion");
	    	window.location.href="/mozos/eventos";
		}
	}
	catch (error) 
	{console.error('Error al actualizar: ', error);}
	
	const editModal = bootstrap.Modal.getInstance(
		document.getElementById("editModal")
	);
	
	editModal.hide();
}	
