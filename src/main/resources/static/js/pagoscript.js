//Barra de navegacion
	let sidebarToggle = document.querySelector("#sidebar-toggle");
	
	sidebarToggle.addEventListener("click", function () {
	  document.querySelector("#sidebar").classList.toggle("collapsed");
	});

//Establacer min y max de fechas
const fechaActual = new Date();
const primerDiaDelMes = new Date(fechaActual.getFullYear(), fechaActual.getMonth(), 1);
const formattedMinDate = primerDiaDelMes.toISOString().split('T')[0];
const hoy = new Date().toISOString().split('T')[0];

document.getElementById("fechaPago").setAttribute("min", formattedMinDate);
document.getElementById("fechaPago").setAttribute("max", hoy);

//Cargar Facturas
const selectFactura= document.getElementById("Selectfactura");
fetch("http://localhost:7777/factura/verfactura")
    .then(response => response.json())
    .then(clientes => 
    {
        clientes.forEach(cliente => 
        {
            const option = document.createElement("option");
            option.value = cliente.idFactura;
            option.textContent = cliente.idFactura;
            selectFactura.appendChild(option);
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
			"idFactura" : document.getElementById("Selectfactura").value,
			"metodoPago" : document.getElementById("metodoPago").value,
			"monto" : document.getElementById("monto").value,
			"fechaPago" : document.getElementById("fechaPago").value
		};
	    try 
	    {
            const responseRegistro = await fetch("http://localhost:7777/pagos/pagar", {
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
            	window.location.href = "/mozos/pagos";
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
	    const response = await fetch("http://localhost:7777/pagos/verpagos");
	    const empleados = await response.json();
	    let content = ``;
	    empleados.forEach((empleado) => {
	      content += `
	                <tr>
	                    <td class="centered">${empleado.idPago}</td>
	                    <td class="centered">${empleado.idFactura}</td>
	                    <td class="centered">${empleado.metodoPago}</td>
	                    <td class="centered">${empleado.monto}</td>
	                    <td class="centered">${empleado.fechaPago}</td>
	                </tr>`;
	    });
	    tableBody_pagos.innerHTML = content;
	  } catch (ex) {
	    alert(ex);
	  }
	};
	
	document.addEventListener('DOMContentLoaded', (event) => 
	{
	  listEmpleados();
	});