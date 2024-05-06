package Eventos.Mozos.Controladores;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Eventos.Mozos.Modelos.Eventos;
import Eventos.Mozos.Repositorio.EventoRepositorio;
import Eventos.Mozos.Request.EventosRequest;

@RestController
@RequestMapping("/evento")
public class EventosControlador 
{
	@Autowired
	EventoRepositorio reEventos;
	
	@GetMapping ("/vereventos")
	public ResponseEntity<?> get()
	{
		return ResponseEntity.ok(reEventos.findAll());
	}
	
	@GetMapping ("/vereventos/{idevento}")
	public ResponseEntity<?> getById(@PathVariable Integer idevento)
	{
		Eventos eve = reEventos.findById(idevento)
				.orElseThrow(()->new RuntimeException("No se encontro el evento"));
		
		return ResponseEntity.ok(eve);
	}
	
	@PostMapping ("/guardar")
	public ResponseEntity<?> nuevo (@RequestBody EventosRequest request)
	{
		try 
		{
			Eventos eve = new Eventos();
			eve.setDescripcion(request.getDescripcion());
			eve.setLugar(request.getLugar());
			eve.setNombreEvento(request.getNombreEvento());
			
			DateTimeFormatter formatear = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    LocalDate fechapago = LocalDate.parse(request.getFecha(), formatear);
		    eve.setFecha(Date.valueOf(fechapago));
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
	        LocalTime horaInicio = LocalTime.parse((CharSequence) request.getHoraInicio(), formatter);
	        eve.setHoraInicio(horaInicio);
	        
	        LocalTime horaFin = LocalTime.parse((CharSequence) request.getHoraFin(), formatter);
	        eve.setHoraFin(horaFin);
	        
			reEventos.save(eve);
			return ResponseEntity.ok("Se guardo con exito el evento");
		}
		catch (Exception e) {return ResponseEntity.badRequest().body("No se pudo guardar el pago, hubo el siguiente error: " + e.getMessage());}
	}
	
	@PutMapping ("/editar")
	public ResponseEntity<?> editar (@RequestBody Eventos evento)
	{
		try 
		{
			Eventos eve = reEventos.findById(evento.getIdEvento())
					.orElseThrow(()->new RuntimeException("No se encontro el evento"));
			
			eve.setDescripcion(evento.getDescripcion());
			eve.setFecha(evento.getFecha());
			eve.setHoraInicio(evento.getHoraInicio());
			eve.setHoraFin(evento.getHoraFin());
			eve.setLugar(evento.getLugar());
			eve.setNombreEvento(evento.getNombreEvento());
			reEventos.save(eve);
			return ResponseEntity.ok("Se edito con exito el evento");
		}
		catch (Exception e) {return ResponseEntity.badRequest().body("No se pudo editar el evento, hubo el siguiente error: " + e.getMessage());}
	}
}
