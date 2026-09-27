package com.rollerspeed.rollerspeed1.service;

import com.rollerspeed.rollerspeed1.model.ArregloFloral;
import com.rollerspeed.rollerspeed1.model.TipoFlor;
import com.rollerspeed.rollerspeed1.repository.ArregloFloralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArregloFloralService {

    @Autowired
    private ArregloFloralRepository arregloFloralRepository;

    public List<ArregloFloral> listarTodos() {
        return arregloFloralRepository.findAll();
    }

    public ArregloFloral guardar(ArregloFloral arregloFloral) {
        return arregloFloralRepository.save(arregloFloral);
    }

    public ArregloFloral obtenerPorId(Long id) {
        return arregloFloralRepository.findById(id).orElse(null);
    }

    public List<ArregloFloral> obtenerPorTipoFlor(Long tipoFlorId) {
        return arregloFloralRepository.findByTipoFlorId(tipoFlorId);
    }

    public void eliminar(Long id) {
        arregloFloralRepository.deleteById(id);
    }

    public boolean existePorId(Long id) {
        return arregloFloralRepository.existsById(id);
    }
}
