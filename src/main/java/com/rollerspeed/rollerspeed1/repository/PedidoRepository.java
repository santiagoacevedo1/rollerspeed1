package com.rollerspeed.rollerspeed1.repository;

import com.rollerspeed.rollerspeed1.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByEstado(String estado);
    List<Pedido> findByFechaPedidoBetween(LocalDate inicio, LocalDate fin);
}
