//Barra de navegacion
	let sidebarToggle = document.querySelector("#sidebar-toggle");
	
	sidebarToggle.addEventListener("click", function () {
	  document.querySelector("#sidebar").classList.toggle("collapsed");
	});

//Registrar la nuevo cliente
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
			"direccion" : document.getElementById("direccion").value,
			"telefono" : document.getElementById("telefono").value
		};

	    try 
	    {
            const responseRegistro = await fetch("http://localhost:7777/clientes/guardar", {
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
            	window.location.href = "/mozos/clientes";
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
	    const response = await fetch("http://localhost:7777/clientes/verclientes");
	    const empleados = await response.json();
	    let content = ``;
	    empleados.forEach((empleado) => {
	      content += `
	                <tr>
	                    <td class="centered">${empleado.idCliente}</td>
	                    <td class="centered">${empleado.nombre}</td>
	                    <td class="centered">${empleado.direccion}</td>
	                    <td class="centered">${empleado.telefono}</td>
	                    <td class="centered">
	                    	<button id="edit" class="btn btn-sm btn-primary" onclick="openEditModal('${empleado.idCliente}')"> <i class="fa-solid fa-pencil"></i></button>
	        				<button id="delete" class="btn btn-sm btn-danger" onclick="eliminar(${empleado.idCliente})"><i class="fa-solid fa-trash-can"></i></button>
	                    </td>
	                </tr>`;
	    });
	    tableBody_clientes.innerHTML = content;
	  } catch (ex) {
	    alert(ex);
	  }
	};
	
	document.addEventListener('DOMContentLoaded', (event) => {
	  listEmpleados();
	});
	
//Editar
async function openEditModal(idCliente) 
{
	try 
	{
    	const response = await fetch(`http://localhost:7777/clientes/verclientes/${idCliente}`);
    	if (!response.ok) 
    	{
      		alert("Hubo algun error en el url");
    	}
	    const empleadoDetalles = await response.json();
	
	  	const modalBody = document.getElementById("editModalBody");
	  	modalBody.innerHTML = `
  		<div class="mb-3">
		    <label for="recipient-name" class="col-form-label">Id Cliente:</label>
			<input type="text" class="form-control" id="editIdCliente" value="${empleadoDetalles.idCliente}" readonly/>
	    </div>
	    
	    <div class="mb-3">
        	<label for="editNombre" class="col-form-label">Nombre:</label>
        	<input type="text" class="form-control" id="editNombre" value="${empleadoDetalles.nombre}" />
     	</div>
     	
     	<div class="mb-3">
        	<label for="editNombre" class="col-form-label">Direccion:</label>
    		<input type="text" class="form-control" id="editDireccion" value="${empleadoDetalles.direccion}" />
     	</div>
     	
     	<div class="mb-3">
        	<label for="editNombre" class="col-form-label">Telefono:</label>
        	<input type="text" class="form-control" id="editTelefono" value="${empleadoDetalles.telefono}" />
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
		"idCliente" : document.getElementById("editIdCliente").value,
		"nombre" : document.getElementById("editNombre").value,
		"direccion" : document.getElementById("editDireccion").value,
		"telefono" : document.getElementById("editTelefono").value
	}
	
	try 
	{
	    const response = await fetch(`http://localhost:7777/clientes/editar`, {
	      method: 'PUT',
	      headers: {
	        'Content-Type': 'application/json',
	      },
	      body: JSON.stringify(request),
	    });
	
	    if (!response.ok) 
	    {
	      alert("No se pudo modificar al cliente");
	    }
		
	    alert("Se guardo la edicion");
	    window.location.href="/mozos/clientes";
	}
	catch (error) 
	{console.error('Error al actualizar: ', error);}
	
	const editModal = bootstrap.Modal.getInstance(
		document.getElementById("editModal")
	);
	
	editModal.hide();
}	

//Eliminar datos
async function eliminar(idCliente)
{
		
	try
	{
		const borrar = await fetch("http://localhost:7777/clientes/delete/"+idCliente, {
			method: "DELETE",
	        headers: 
	        {
	            'Accept': 'application/json',
	            'Content-Type': 'application/json'
	        }
    	});
	    if (borrar.ok) 
	    {
   			alert("Clienteeliminado");
   			window.location.href="/mozos/clientes";
		} 
		else 
		{
   			alert("No se pudo eliminar al cliente");
	   	}
	}
	catch(error)
	{
		alert("No se pudo eliminar al cliente");
        console.error('Error during delete: ', error);
	}
};   