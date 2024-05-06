package Eventos.Mozos.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Eventos.Mozos.Modelos.Mozos;
import Eventos.Mozos.Repositorio.MozosRepositorio;

@RestController
@RequestMapping("/mozos")
public class MozosControlador 
{
	@Autowired
	MozosRepositorio reMozos;
	
	@GetMapping ("/vermozos")
	public ResponseEntity<?> getPagos()
	{
		return ResponseEntity.ok(reMozos.findAll());
	}
	
	@GetMapping ("/vermozosid/{idmozo}")
	public ResponseEntity<?> getPagosById(@PathVariable Integer idmozo)
	{
		Mozos mo = reMozos.findById(idmozo)
				.orElseThrow(()->new RuntimeException("No se encontro al mozo"));
		
		return ResponseEntity.ok(mo);
	}
	
	@PostMapping ("/registrar")
	public ResponseEntity<?> nuevo (@RequestBody Mozos mozo)
	{
		try 
		{
			reMozos.save(mozo);
			return ResponseEntity.ok("Se guardo con exito el mozo");
		}
		catch (Exception e) {return ResponseEntity.badRequest().body("No se pudo guardar el mozo, hubo el siguiente error: " + e.getMessage());}
	}
	
	@PutMapping ("/editar")
	public ResponseEntity<?> editar (@RequestBody Mozos mozo)
	{
		try 
		{
			reMozos.save(mozo);
			
			return ResponseEntity.ok("Se edito con exito el registro del mozo");
		}
		catch (Exception e) {return ResponseEntity.badRequest().body("No se pudo editar el mozo, hubo el siguiente error: " + e.getMessage());}
	}
	
	@DeleteMapping ("/delete/{idmozo}")
	public ResponseEntity<?> eliminar (@PathVariable Integer idmozo)
	{
		try
		{
			Mozos mo = reMozos.findById(idmozo)
					.orElseThrow(()->new RuntimeException("No se encontro al mozo"));
			mo.setDisponibilidad("No activo");
			reMozos.save(mo);
			return ResponseEntity.ok("Se desactivo al mozo");
		}
		catch(Exception e)
		{
			return ResponseEntity.badRequest().body("No se pudo eliminar al mozo por: " + e.getMessage());
		}
	}
}
