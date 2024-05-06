package Eventos.Mozos.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Eventos.Mozos.Modelos.Facturaciones;
import Eventos.Mozos.Modelos.Reservas;

public interface FacturacionesRepositorio extends JpaRepository <Facturaciones, Integer>
{
	List<Facturaciones> findByReservaId(Reservas reserva);
}
