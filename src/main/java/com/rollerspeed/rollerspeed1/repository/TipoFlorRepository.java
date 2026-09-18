package com.rollerspeed.rollerspeed1.repository;

import com.rollerspeed.rollerspeed1.model.TipoFlor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoFlorRepository extends JpaRepository<TipoFlor, Long> {
}
