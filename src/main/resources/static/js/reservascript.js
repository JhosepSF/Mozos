//Barra de navegacion
	let sidebarToggle = document.querySelector("#sidebar-toggle");
	
	sidebarToggle.addEventListener("click", function () {
	  document.querySelector("#sidebar").classList.toggle("collapsed");
	});
	
//Establacer min de fechas
const hoy = new Date().toISOString().split('T')[0];

document.getElementById("fechaReserva").setAttribute("min", hoy);

//Cargar Datos
const selectCliente = document.getElementById("Selectcliente");
fetch("http://localhost:7777/clientes/verclientes")
    .then(response => response.json())
    .then(clientes => 
    {
        clientes.forEach(cliente => 
        {
            const option = document.createElement("option");
            option.value = cliente.idCliente;
            option.textContent = cliente.nombre;
            selectCliente.appendChild(option);
        });
    })
    .catch(error => console.error("Error al obtener la lista de opciones:", error));

const selectEvento = document.getElementById("Selectevento");
fetch("http://localhost:7777/evento/vereventos")
    .then(response => response.json())
    .then(clientes => 
    {
        clientes.forEach(cliente => 
        {
            const option = document.createElement("option");
            option.value = cliente.idEvento;
            option.textContent = cliente.nombreEvento;
            selectEvento.appendChild(option);
        });
    })
    .catch(error => console.error("Error al obtener la lista de opciones:", error));
 
const selectMozo= document.getElementById("Selectmozo");
fetch("http://localhost:7777/mozos/vermozos")
    .then(response => response.json())
    .then(clientes => 
    {
        clientes.forEach(cliente => 
        {
            const option = document.createElement("option");
            option.value = cliente.idMozo;
            option.textContent = cliente.nombre;
            selectMozo.appendChild(option);
        });
    })
    .catch(error => console.error("Error al obtener la lista de opciones:", error));

//Registrar la nueva Marca
	let boton = document.getElementById("btnAceptar");
	boton.addEventListener("click", evento =>
	{
	    newEmpleado();
	});
	
	let newEmpleado = async () =>
	{	
	    let asistencia =
	    {
			"clientesId" : document.getElementById("Selectcliente").value,
			"eventosId" : document.getElementById("Selectevento").value,
			"mozoId" : document.getElementById("Selectmozo").value,
			"fechaReserva" : document.getElementById("fechaReserva").value
		};

	    try 
	    {
            const responseRegistro = await fetch("http://localhost:7777/reservas/guardar", {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(asistencia),
            });

            if (!responseRegistro.ok) 
            {
				alert("No se pudo registrar nueva");
            }
			else
			{
            	alert("Se logro guardar");
            	window.location.href = "/mozos/reservas";
			}
	    }
	    catch (error)
	    {
	        alert("No se pudo registrar nuevo asistencia");
	        console.error('Error durante el registro: ', error);
	    }
	};
	
//Rellenar tablas
	const listEmpleados= async () => 
	{
	  try 
	  {
	    const response = await fetch("http://localhost:7777/reservas/ver");
	    const empleados = await response.json();
	    let content = ``;
	    empleados.forEach((empleado) => {
	      content += `
	                <tr>
	                    <td class="centered">${empleado.idReservas}</td>
	                    <td class="centered">${empleado.clientesId}</td>
	                    <td class="centered">${empleado.eventosId}</td>
	                    <td class="centered">${empleado.mozoId}</td>
	                    <td class="centered">${empleado.fechaReserva}</td>
	                    <td class="centered">
	        				<button id="delete" class="btn btn-sm btn-danger" onclick="eliminar(${empleado.idReservas})"><i class="fa-solid fa-trash-can"></i></button>
	                    </td>
	                </tr>`;
	    });
	    tableBody_reservas.innerHTML = content;
	  } catch (ex) {
	    alert(ex);
	  }
	};
	
	document.addEventListener('DOMContentLoaded', (event) => 
	{
	  listEmpleados();
	});

//Eliminar datos
async function eliminar(idReservas)
{
		
	try
	{
		const borrar = await fetch("http://localhost:7777/reservas/delete/"+idReservas, {
			method: "DELETE",
	        headers: 
	        {
	            'Accept': 'application/json',
	            'Content-Type': 'application/json'
	        }
    	});
	    if (borrar.ok) 
	    {
   			alert("Eliminado exitosamente");
   			window.location.href="/mozos/reservas";
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
