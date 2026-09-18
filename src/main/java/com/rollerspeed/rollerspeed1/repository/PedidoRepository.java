package com.rollerspeed.rollerspeed1.repository;

import com.rollerspeed.rollerspeed1.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
