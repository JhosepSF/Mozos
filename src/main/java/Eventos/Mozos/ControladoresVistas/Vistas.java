package Eventos.Mozos.ControladoresVistas;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Vistas
{
	@GetMapping("/mozos")
	public String index() 
	{
		return "index";
	}
	
	@GetMapping("/mozos/clientes")
	public String clientes() 
	{
		return "cliente";
	}
	
	@GetMapping("/mozos/eventos")
	public String eventos() 
	{
		return "evento";
	}
	
	@GetMapping("/mozos/facturas")
	public String facturas() 
	{
		return "facturacion";
	}
	
	@GetMapping("/mozos/mozos")
	public String mozos() 
	{
		return "mozo";
	}
	
	@GetMapping("/mozos/pagos")
	public String pagos() 
	{
		return "pago";
	}
	
	@GetMapping("/mozos/reservas")
	public String reservas() 
	{
		return "reserva";
	}
}
