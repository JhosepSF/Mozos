package Eventos.Mozos.Controladores;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Eventos.Mozos.Modelos.Facturaciones;
import Eventos.Mozos.Modelos.Pagos;
import Eventos.Mozos.Repositorio.FacturacionesRepositorio;
import Eventos.Mozos.Repositorio.PagosRepositorio;
import Eventos.Mozos.Request.PagosRequest;

@RestController
@RequestMapping("/pagos")
public class PagosControlador 
{
	@Autowired
	PagosRepositorio repository;
	
	@Autowired
	FacturacionesRepositorio facturare;
	
	@GetMapping ("/verpagos")
	public ResponseEntity<?> getPagos()
	{
		List<Pagos> pagos = repository.findAll();
		List<PagosRequest> pagosdto = new ArrayList<>();
		
		for(Pagos pago : pagos) 
		{
			Integer factura = pago.getIdFactura() != null ? pago.getIdFactura().getIdFactura() : null;
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	        String fecha = sdf.format(pago.getFechaPago());
			
			PagosRequest dto = new PagosRequest
					(
							pago.getIdPago(),
							factura,
							pago.getMetodoPago(),
							pago.getMonto(),
							fecha
					);
			pagosdto.add(dto);
		}
		
		return ResponseEntity.ok(pagosdto);
	}
	
	@GetMapping ("/verpagosid/{idpago}")
	public ResponseEntity<?> getPagosById(@PathVariable Integer idpago)
	{
		Pagos pago = repository.findById(idpago)
				.orElseThrow(()->new RuntimeException("No se encontro el pago"));
		
		return ResponseEntity.ok(pago);
	}
	
	@PostMapping ("/pagar")
	public ResponseEntity<?> nuevopago (@RequestBody PagosRequest pago)
	{
		try 
		{
			Facturaciones fact = facturare.findById(pago.getIdFactura())
					.orElseThrow(()->new RuntimeException("No se encontro la factura"));
			
			Pagos pagos = new Pagos();
			
			DateTimeFormatter formatear = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    LocalDate fechapago = LocalDate.parse(pago.getFechaPago(), formatear);
		    pagos.setFechaPago(Date.valueOf(fechapago));

			pagos.setIdFactura(fact);
			pagos.setMetodoPago(pago.getMetodoPago());
			pagos.setMonto(pago.getMonto());
			repository.save(pagos);
			return ResponseEntity.ok("Se guardo con exito el registro del pago");
		}
		catch (Exception e) {return ResponseEntity.badRequest().body("No se pudo guardar el pago, hubo el siguiente error: " + e.getMessage());}
	}
	
	@PutMapping ("/editarpago")
	public ResponseEntity<?> editarpago (@RequestBody PagosRequest pago)
	{
		try 
		{
			Pagos pagos = repository.findById(pago.getIdPago())
					.orElseThrow(()->new RuntimeException("No se encontro el pago"));
			
			Facturaciones fact = facturare.findById(pago.getIdFactura())
					.orElseThrow(()->new RuntimeException("No se encontro la factura"));

			DateTimeFormatter formatear = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    LocalDate fechapago = LocalDate.parse(pago.getFechaPago(), formatear);
		    pagos.setFechaPago(Date.valueOf(fechapago));
		    
			pagos.setIdFactura(fact);
			pagos.setMetodoPago(pago.getMetodoPago());
			pagos.setMonto(pagos.getMonto());
			repository.save(pagos);
			return ResponseEntity.ok("Se edito con exito el registro del pago");
		}
		catch (Exception e) {return ResponseEntity.badRequest().body("No se pudo editar el pago, hubo el siguiente error: " + e.getMessage());}
	}
}	
