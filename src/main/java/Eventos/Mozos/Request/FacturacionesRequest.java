package Eventos.Mozos.Request;

public class FacturacionesRequest 
{
	Integer idFactura;
	String fecha;
	Double monto;
	Integer reservaId;
	
	public Integer getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(Integer idFactura) {
		this.idFactura = idFactura;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	public Integer getReservaId() {
		return reservaId;
	}
	public void setReservaId(Integer reservaId) {
		this.reservaId = reservaId;
	}
	public FacturacionesRequest(Integer idFactura, String fecha, Double monto, Integer reservaId) {
		super();
		this.idFactura = idFactura;
		this.fecha = fecha;
		this.monto = monto;
		this.reservaId = reservaId;
	}
}
