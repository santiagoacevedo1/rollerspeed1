package com.rollerspeed.rollerspeed1.service;

import com.rollerspeed.rollerspeed1.model.Pedido;
import com.rollerspeed.rollerspeed1.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido guardar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Pedido obtenerPorId(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public List<Pedido> obtenerPorEstado(String estado) {
        return pedidoRepository.findByEstado(estado);
    }

    public List<Pedido> obtenerPorRangoFechas(LocalDate inicio, LocalDate fin) {
        return pedidoRepository.findByFechaPedidoBetween(inicio, fin);
    }

    public void eliminar(Long id) {
        pedidoRepository.deleteById(id);
    }

    public boolean existePorId(Long id) {
        return pedidoRepository.existsById(id);
    }
}
