package com.integrador1.tienditagb.repository;

import com.integrador1.tienditagb.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    List<Category> findByStatus(boolean status);

}
