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
public class Facturaciones 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int idFactura;
	
	@Column
	private Date fecha;
	private Double monto;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name= "reservaId")
	Reservas reservaId;

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Reservas getReservaId() {
		return reservaId;
	}

	public void setReservaId(Reservas reservaId) {
		this.reservaId = reservaId;
	}
}
