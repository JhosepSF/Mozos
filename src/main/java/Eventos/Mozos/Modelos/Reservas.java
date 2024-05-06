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
public class Reservas
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int idReservas;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name= "clientesId")
	Clientes clientesId;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name= "eventosId")
	Eventos eventosId;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name= "mozoId")
	Mozos mozoId;
	
	@Column
	private Date fechaReserva;

	public int getIdReservas() {
		return idReservas;
	}

	public void setIdReservas(int idReservas) {
		this.idReservas = idReservas;
	}

	public Clientes getClientesId() {
		return clientesId;
	}

	public void setClientesId(Clientes clientesId) {
		this.clientesId = clientesId;
	}

	public Eventos getEventosId() {
		return eventosId;
	}

	public void setEventosId(Eventos eventosId) {
		this.eventosId = eventosId;
	}

	public Mozos getMozoId() {
		return mozoId;
	}

	public void setMozoId(Mozos mozoId) {
		this.mozoId = mozoId;
	}

	public Date getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}
}
