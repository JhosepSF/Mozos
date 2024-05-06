package Eventos.Mozos.Modelos;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Pagos 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int idPago;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name= "facturaId")
	Facturaciones idFactura;;
	
	@Column
	private String metodoPago;
	private Double monto;
	private Date fechaPago;
	
	public int getIdPago() {
		return idPago;
	}
	public void setIdPago(int idPago) {
		this.idPago = idPago;
	}
	public Facturaciones getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(Facturaciones idFactura) {
		this.idFactura = idFactura;
	}
	public String getMetodoPago() {
		return metodoPago;
	}
	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	public Date getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
}
