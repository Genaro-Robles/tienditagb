package com.integrador1.tienditagb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.integrador1.tienditagb.models.Provider;

@Repository
public interface ProviderRepository extends JpaRepository<Provider,Integer>{
    
}
