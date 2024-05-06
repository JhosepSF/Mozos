//Barra de navegacion
	let sidebarToggle = document.querySelector("#sidebar-toggle");
	
	sidebarToggle.addEventListener("click", function () {
	  document.querySelector("#sidebar").classList.toggle("collapsed");
	});
	

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
			"nombre" : document.getElementById("nombre").value,
			"disponibilidad" : document.getElementById("disponibilidad").value,
			"tarifa" : document.getElementById("tarifa").value
		};

	    try 
	    {
            const responseRegistro = await fetch("http://localhost:7777/mozos/registrar", {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(cliente),
            });

            if (!responseRegistro.ok) 
            {
				alert("No se pudo registrar nuevo cliente");
            }
			else
			{
            	alert("Se logro guardar");
            	window.location.href = "/mozos/mozos";
			}
	    }
	    catch (error)
	    {
	        alert("No se pudo registrar nuevo cliente");
	        console.error('Error durante el registro: ', error);
	    }
	};
	
//Rellenar tablas
	const listEmpleados= async () => 
	{
	  try 
	  {
	    const response = await fetch("http://localhost:7777/mozos/vermozos");
	    const empleados = await response.json();
	    let content = ``;
	    empleados.forEach((empleado) => {
	      content += `
	                <tr>
	                    <td class="centered">${empleado.idMozo}</td>
	                    <td class="centered">${empleado.nombre}</td>
	                    <td class="centered">${empleado.disponibilidad}</td>
	                    <td class="centered">${empleado.tarifa}</td>
	                    <td class="centered">
	                    	<button id="edit" class="btn btn-sm btn-primary" onclick="openEditModal('${empleado.idMozo}')"> <i class="fa-solid fa-pencil"></i></button>
	        				<button id="delete" class="btn btn-sm btn-danger" onclick="eliminar(${empleado.idMozo})"><i class="fa-solid fa-trash-can"></i></button>
	                    </td>
	                </tr>`;
	    });
	    tableBody_mozos.innerHTML = content;
	  } catch (ex) {
	    alert(ex);
	  }
	};
	
	document.addEventListener('DOMContentLoaded', (event) => {
	  listEmpleados();
	});
	
//Editar
async function openEditModal(idMozo) 
{
	try 
	{
    	const response = await fetch(`http://localhost:7777/mozos/vermozosid/${idMozo}`);
    	if (!response.ok) 
    	{
      		alert("Hubo algun error en el url");
    	}
	    const empleadoDetalles = await response.json();
	
	  	const modalBody = document.getElementById("editModalBody");
	  	modalBody.innerHTML = `
  		<div class="mb-3">
		    <label for="recipient-name" class="col-form-label">Id Mozo:</label>
			<input type="text" class="form-control" id="editIdMozo" value="${empleadoDetalles.idMozo}" readonly/>
	    </div>
	    
	    <div class="mb-3">
        	<label for="editNombre" class="col-form-label">Nombre:</label>
        	<input type="text" class="form-control" id="editNombre" value="${empleadoDetalles.nombre}" />
     	</div>
     	
     	<div class="mb-3">
        	<label for="editNombre" class="col-form-label">Disponibilidad:</label>
    		<input type="text" class="form-control" id="editDisponibilidad" value="${empleadoDetalles.disponibilidad}" />
     	</div>
     	
     	correoElectronico
     	<div class="mb-3">
        	<label for="editNombre" class="col-form-label">Tarifa</label>
        	<input type="text" class="form-control" id="editTarifa" value="${empleadoDetalles.tarifa}" />
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
		"idMozo" : document.getElementById("editIdMozo").value,
		"nombre" : document.getElementById("editNombre").value,
		"disponibilidad" : document.getElementById("editDisponibilidad").value,
		"tarifa" : document.getElementById("editTarifa").value
	}
	
	try 
	{
	    const response = await fetch(`http://localhost:7777/mozos/editar`, {
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
	    	window.location.href="/mozos/mozos";
		}
	}
	catch (error) 
	{console.error('Error al actualizar: ', error);}
	
	const editModal = bootstrap.Modal.getInstance(
		document.getElementById("editModal")
	);
	
	editModal.hide();
}	

//Eliminar datos
async function eliminar(idMozo)
{
		
	try
	{
		const borrar = await fetch("http://localhost:7777/mozos/delete/"+idMozo, {
			method: "DELETE",
	        headers: 
	        {
	            'Accept': 'application/json',
	            'Content-Type': 'application/json'
	        }
    	});
	    if (borrar.ok) 
	    {
   			alert("Eliminado");
   			window.location.href="/mozos/mozos";
		} 
		else 
		{
   			alert("No se pudo eliminar");
	   	}
	}
	catch(error)
	{
		alert("No se pudo eliminar");
        console.error('Error during delete: ', error);
	}
}; 	

