package Eventos.Mozos.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import Eventos.Mozos.Modelos.Clientes;

public interface ClientesRepositorio extends JpaRepository <Clientes, Integer> {}
