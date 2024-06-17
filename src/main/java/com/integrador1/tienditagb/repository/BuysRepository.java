package com.integrador1.tienditagb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.integrador1.tienditagb.models.Buys;

@Repository
public interface BuysRepository extends JpaRepository<Buys,Integer>{
    
}
