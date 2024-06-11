package com.integrador1.tienditagb.repository;

import com.integrador1.tienditagb.models.Product;
import com.integrador1.tienditagb.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findByCorreoAndPassword(String correo, String password);
}
