package Eventos.Mozos.Modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Mozos 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int idMozo;
	
	@Column
	private String nombre;
	private String disponibilidad;
	private Double tarifa;
	
	public int getIdMozo() {
		return idMozo;
	}
	public void setIdMozo(int idMozo) {
		this.idMozo = idMozo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDisponibilidad() {
		return disponibilidad;
	}
	public void setDisponibilidad(String disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	public Double getTarifa() {
		return tarifa;
	}
	public void setTarifa(Double tarifa) {
		this.tarifa = tarifa;
	}
}
