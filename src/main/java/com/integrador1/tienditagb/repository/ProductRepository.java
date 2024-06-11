package com.integrador1.tienditagb.repository;

import com.integrador1.tienditagb.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByStatus(boolean status);
    List<Product> findByStatusAndCategory(boolean status, int category);
}
