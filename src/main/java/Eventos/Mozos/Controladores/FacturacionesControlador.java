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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Eventos.Mozos.Modelos.Facturaciones;
import Eventos.Mozos.Modelos.Reservas;
import Eventos.Mozos.Repositorio.FacturacionesRepositorio;
import Eventos.Mozos.Repositorio.ReservasRepositorio;
import Eventos.Mozos.Request.FacturacionesRequest;

@RestController
@RequestMapping("/factura")
public class FacturacionesControlador 
{
	@Autowired
	FacturacionesRepositorio reFacturaciones;
	
	@Autowired
	ReservasRepositorio reReservas;
	
	@GetMapping("/verfactura")
	public ResponseEntity<?> get()
	{
		List<Facturaciones> facturas = reFacturaciones.findAll();
		List<FacturacionesRequest> facturasdto = new ArrayList<>();
		
		for(Facturaciones fac : facturas) 
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	        String fecha = sdf.format(fac.getFecha());
	        
	        Integer reserva = fac.getReservaId() != null ? fac.getReservaId().getIdReservas() : null;
	        
	        FacturacionesRequest dto = new FacturacionesRequest
	        		(
	        				fac.getIdFactura(),
	        				fecha,
	        				fac.getMonto(),
	        				reserva
	        		);
			facturasdto.add(dto);
		}
		
		return ResponseEntity.ok(facturasdto);
	}
	
	@PostMapping ("/guardar")
	public ResponseEntity<?> nuevo (@RequestBody FacturacionesRequest factura)
	{
		try 
		{
			Reservas re = reReservas.findById(factura.getReservaId())
					.orElseThrow(()->new RuntimeException("No se encontro la factura"));
			
			Facturaciones fac = new Facturaciones();

			fac.setMonto(factura.getMonto());
			
			DateTimeFormatter formatear = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    LocalDate fecha = LocalDate.parse(factura.getFecha(), formatear);
		    fac.setFecha(Date.valueOf(fecha));
			
			fac.setReservaId(re);
			reFacturaciones.save(fac);
			return ResponseEntity.ok("Se guardo con exito la factura");
		}
		catch (Exception e) {return ResponseEntity.badRequest().body("No se pudo guardar la factura, hubo el siguiente error: " + e.getMessage());}
	}
}
