package Eventos.Mozos.Request;

public class PagosRequest 
{
	Integer idPago;
	Integer idFactura;;
	String metodoPago;
	Double monto;
	String fechaPago;
	
	public Integer getIdPago() {
		return idPago;
	}
	public void setIdPago(Integer idPago) {
		this.idPago = idPago;
	}
	public Integer getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(Integer idFactura) {
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
	public String getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}
	public PagosRequest(Integer idPago, Integer idFactura, String metodoPago, Double monto, String fechaPago) {
		super();
		this.idPago = idPago;
		this.idFactura = idFactura;
		this.metodoPago = metodoPago;
		this.monto = monto;
		this.fechaPago = fechaPago;
	}
}
