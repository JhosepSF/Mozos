package Eventos.Mozos.Request;

public class EventosRequest 
{
	Integer idEvento;
	String nombreEvento;
	String fecha;
	String horaInicio;
	String horaFin;
	String lugar;
	String descripcion;
	
	public Integer getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
	}
	public String getNombreEvento() {
		return nombreEvento;
	}
	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public EventosRequest(Integer idEvento, String nombreEvento, String fecha, String horaInicio, String horaFin,
			String lugar, String descripcion) {
		super();
		this.idEvento = idEvento;
		this.nombreEvento = nombreEvento;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.lugar = lugar;
		this.descripcion = descripcion;
	}
}
