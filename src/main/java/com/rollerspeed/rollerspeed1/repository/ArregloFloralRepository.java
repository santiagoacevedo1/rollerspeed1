package com.rollerspeed.rollerspeed1.repository;

import com.rollerspeed.rollerspeed1.model.ArregloFloral;
import com.rollerspeed.rollerspeed1.model.TipoFlor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArregloFloralRepository extends JpaRepository<ArregloFloral, Long> {
    List<ArregloFloral> findByTipoFlorId(Long tipoFlorId);
}
