package com.rollerspeed.rollerspeed1.service;

import com.rollerspeed.rollerspeed1.model.TipoFlor;
import com.rollerspeed.rollerspeed1.repository.TipoFlorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoFlorService {

    @Autowired
    private TipoFlorRepository tipoFlorRepository;

    public List<TipoFlor> listarTodos() {
        return tipoFlorRepository.findAll();
    }

    public TipoFlor guardar(TipoFlor tipoFlor) {
        return tipoFlorRepository.save(tipoFlor);
    }

    public TipoFlor obtenerPorId(Long id) {
        return tipoFlorRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        tipoFlorRepository.deleteById(id);
    }

    public boolean existePorId(Long id) {
        return tipoFlorRepository.existsById(id);
    }
}
