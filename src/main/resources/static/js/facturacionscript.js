//Barra de navegacion
	let sidebarToggle = document.querySelector("#sidebar-toggle");
	
	sidebarToggle.addEventListener("click", function () {
	  document.querySelector("#sidebar").classList.toggle("collapsed");
	});
	
//Cargar Facturas
const selectReservas = document.getElementById("Selectreserva");
fetch("http://localhost:7777/reservas/ver")
    .then(response => response.json())
    .then(clientes => 
    {
        clientes.forEach(cliente => 
        {
            const option = document.createElement("option");
            option.value = cliente.idReservas;
            option.textContent = cliente.idReservas;
            selectReservas.appendChild(option);
        });
    })
    .catch(error => console.error("Error al obtener la lista de opcioness:", error));

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
			"fecha" : document.getElementById("fecha").value,
			"monto" : document.getElementById("monto").value,
			"reservaId" : document.getElementById("Selectreserva").value
		};
		
	    try 
	    {
            const responseRegistro = await fetch("http://localhost:7777/factura/guardar", {
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
            	window.location.href = "/mozos/facturas";
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
	    const response = await fetch("http://localhost:7777/factura/verfactura");
	    const empleados = await response.json();
	    let content = ``;
	    empleados.forEach((empleado) => {
	      content += `
	                <tr>
	                    <td class="centered">${empleado.idFactura}</td>
	                    <td class="centered">${empleado.fecha}</td>
	                    <td class="centered">${empleado.monto}</td>
	                    <td class="centered">${empleado.reservaId}</td>
	                </tr>`;
	    });
	    tableBody_facturacion.innerHTML = content;
	  } catch (ex) {
	    alert(ex);
	  }
	};
	
	document.addEventListener('DOMContentLoaded', (event) => 
	{
	  listEmpleados();
	});