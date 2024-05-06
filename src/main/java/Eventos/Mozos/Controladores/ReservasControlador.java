package Eventos.Mozos.Controladores;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Eventos.Mozos.Modelos.Clientes;
import Eventos.Mozos.Modelos.Eventos;
import Eventos.Mozos.Modelos.Mozos;
import Eventos.Mozos.Modelos.Reservas;
import Eventos.Mozos.Repositorio.ClientesRepositorio;
import Eventos.Mozos.Repositorio.EventoRepositorio;
import Eventos.Mozos.Repositorio.MozosRepositorio;
import Eventos.Mozos.Repositorio.ReservasRepositorio;
import Eventos.Mozos.Request.ReservasRequest;

@RestController
@RequestMapping("/reservas")
public class ReservasControlador 
{
	@Autowired
	ReservasRepositorio reReservas;
	
	@Autowired
	ClientesRepositorio reClientes;
	
	@Autowired
	EventoRepositorio reEventos;
	
	@Autowired
	MozosRepositorio reMozos;
	
	@GetMapping ("/ver")
	public ResponseEntity<?> get()
	{
		List<Reservas> reservas = reReservas.findAll();
		List<ReservasRequest> reservasdto = new ArrayList<>();
		for(Reservas rese : reservas) 
		{
			String cliente = rese.getClientesId() != null ? rese.getClientesId().getNombre() : null;
			String evento = rese.getEventosId() != null ? rese.getEventosId().getNombreEvento() : null;
			String mozo = rese.getMozoId() != null ? rese.getMozoId().getNombre() : null;
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	        String fecha = sdf.format(rese.getFechaReserva());
			
			ReservasRequest dto = new ReservasRequest
					(
							rese.getIdReservas(),
							cliente,
							evento,
							mozo,
							fecha
					);
			
			reservasdto.add(dto);		
		}
		
		return ResponseEntity.ok(reservasdto);
	}
	
	@PostMapping ("/guardar")
	public ResponseEntity<?> nuevo (@RequestBody ReservasRequest reserva)
	{
		try 
		{
			Clientes clie = reClientes.findById(Integer.parseInt(reserva.getClientesId()))
					.orElseThrow(()->new RuntimeException("No se encontro al cliente"));
			
			Mozos mo = reMozos.findById(Integer.parseInt(reserva.getMozoId()))
					.orElseThrow(()->new RuntimeException("No se encontro al mozo"));
			
			Eventos eve = reEventos.findById(Integer.parseInt(reserva.getEventosId()))
					.orElseThrow(()->new RuntimeException("No se encontro el evento"));
			
			Reservas re = new Reservas();
			re.setClientesId(clie);
			re.setEventosId(eve);
			re.setMozoId(mo);
			
			DateTimeFormatter formatear = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    LocalDate fechaReserva = LocalDate.parse(reserva.getFechaReserva(), formatear);
		    re.setFechaReserva(Date.valueOf(fechaReserva));
		    
			reReservas.save(re);
			return ResponseEntity.ok("Se guardo con exito la reserva");
		}
		catch (Exception e) {return ResponseEntity.badRequest().body("No se pudo guardar la reserva, hubo el siguiente error: " + e.getMessage());}
	}
	
	@DeleteMapping ("/delete/{idreserva}")
	public ResponseEntity<?> deleteById(@PathVariable Integer idreserva)
	{
		try
		{
			reReservas.deleteById(idreserva);
			return ResponseEntity.ok("Borrado correctamente");
		}
		catch(Exception e) 
		{
			return ResponseEntity.badRequest().body("No se pudo borrar");
		}
	}
}
