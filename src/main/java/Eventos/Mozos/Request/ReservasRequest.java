package Eventos.Mozos.Request;

public class ReservasRequest 
{
    Integer idReservas;
	String clientesId;
	String eventosId;
	String mozoId;
	String fechaReserva;

	public Integer getIdReservas() {
		return idReservas;
	}

	public void setIdReservas(Integer idReservas) {
		this.idReservas = idReservas;
	}

	public String getClientesId() {
		return clientesId;
	}

	public void setClientesId(String clientesId) {
		this.clientesId = clientesId;
	}

	public String getEventosId() {
		return eventosId;
	}

	public void setEventosId(String eventosId) {
		this.eventosId = eventosId;
	}

	public String getMozoId() {
		return mozoId;
	}

	public void setMozoId(String mozoId) {
		this.mozoId = mozoId;
	}

	public String getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(String fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public ReservasRequest(Integer idReservas, String clientesId, String eventosId, String mozoId,
			String fechaReserva) {
		super();
		this.idReservas = idReservas;
		this.clientesId = clientesId;
		this.eventosId = eventosId;
		this.mozoId = mozoId;
		this.fechaReserva = fechaReserva;
	}	
}
