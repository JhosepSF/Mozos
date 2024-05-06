package Eventos.Mozos.Repositorio;
import org.springframework.data.jpa.repository.JpaRepository;

import Eventos.Mozos.Modelos.Eventos;

public interface EventoRepositorio extends JpaRepository <Eventos, Integer>{}