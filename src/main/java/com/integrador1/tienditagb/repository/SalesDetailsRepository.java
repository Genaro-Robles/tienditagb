package com.integrador1.tienditagb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.integrador1.tienditagb.models.SalesDetails;

@Repository
public interface SalesDetailsRepository extends JpaRepository<SalesDetails,Integer>{
    public List<SalesDetails> findBySale(int sale);
}
