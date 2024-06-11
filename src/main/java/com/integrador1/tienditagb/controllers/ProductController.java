package com.integrador1.tienditagb.controllers;

import com.integrador1.tienditagb.models.Product;
import com.integrador1.tienditagb.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public String newProduct(@RequestBody Product product){
        try {
            productService.newProduct(product);
            return "Nuevo producto ingresado con exito";
        } catch (Exception ex) {
            return "Error: "+ex;
        }
    }
    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProduct();
    }

    @GetMapping("/active")
    public List<Product> getProductsActive(){
        return productService.getProductsActive();
    }

    @GetMapping("/category/{category}")
    public List<Product> getProductsbyCategoryActive(@PathVariable int category){
        return productService.getProductsbyCategoryActive(category);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id){
        return productService.getProductbyId(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable int id){
        return  productService.updateProduct(product,id);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id){
        return productService.deleteProduct(id);
    }

    @PatchMapping("/{id}")
    public String renewProduct(@PathVariable int id){
        return productService.renewProduct(id);
    }
}
