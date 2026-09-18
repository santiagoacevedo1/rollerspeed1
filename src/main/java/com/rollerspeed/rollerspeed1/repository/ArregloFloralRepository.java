package com.rollerspeed.rollerspeed1.repository;

import com.rollerspeed.rollerspeed1.model.ArregloFloral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArregloFloralRepository extends JpaRepository<ArregloFloral, Long> {
}
