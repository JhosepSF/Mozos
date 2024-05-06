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

import Eventos.Mozos.Modelos.Clientes;
import Eventos.Mozos.Repositorio.ClientesRepositorio;

@RestController
@RequestMapping("/clientes")
public class ClientesControlador 
{	
	@Autowired
	ClientesRepositorio repository;
	
	@GetMapping ("/verclientes")
	public ResponseEntity<?> get()
	{
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping ("/verclientes/{idcliente}")
	public ResponseEntity<?> getById(@PathVariable Integer idcliente)
	{
		Clientes clie = repository.findById(idcliente)
				.orElseThrow(()->new RuntimeException("No se encontro el cleinte"));
		
		return ResponseEntity.ok(clie);
	}
	
	@PostMapping ("/guardar")
	public ResponseEntity<?> nuevo (@RequestBody Clientes clientes)
	{
		try 
		{
			repository.save(clientes);
			return ResponseEntity.ok("Se guardo con exito el cliente");
		}
		catch (Exception e) {return ResponseEntity.badRequest().body("No se pudo guardar al cliente, hubo el siguiente error: " + e.getMessage());}
	}
	
	@PutMapping ("/editar")
	public ResponseEntity<?> editar (@RequestBody Clientes cliente)
	{
		try 
		{
			Clientes clie = repository.findById(cliente.getIdCliente())
					.orElseThrow(()-> new RuntimeException("Cliente no encontrado"));
			clie.setNombre(cliente.getNombre());
			clie.setDireccion(cliente.getDireccion());
			clie.setTelefono(cliente.getTelefono());
			repository.save(clie);
			return ResponseEntity.ok("Se edito con exito el cliente");
		}
		catch (Exception e) {return ResponseEntity.badRequest().body("No se pudo editar al cliente, hubo el siguiente error: " + e.getMessage());}
	}
	
	@DeleteMapping ("/delete/{idcliente}")
	public ResponseEntity<?> eliminar (@PathVariable Integer idcliente)
	{
		try
		{
			repository.deleteById(idcliente);
			return ResponseEntity.ok("Se elimino al cliente");
		}
		catch(Exception e)
		{
			return ResponseEntity.badRequest().body("No se pudo eliminar al cliente por: " + e.getMessage());
		}
	}
}
