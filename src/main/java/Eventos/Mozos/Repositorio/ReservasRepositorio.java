package Eventos.Mozos.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import Eventos.Mozos.Modelos.Reservas;

public interface ReservasRepositorio extends JpaRepository <Reservas, Integer>{}
