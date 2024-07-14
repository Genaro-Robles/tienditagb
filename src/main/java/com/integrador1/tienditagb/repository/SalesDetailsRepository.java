package com.integrador1.tienditagb.repository;

import com.integrador1.tienditagb.models.SalesDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalesDetailsRepository extends JpaRepository<SalesDetails,Integer> {
    List<SalesDetails> findBySale(int sale);
}
