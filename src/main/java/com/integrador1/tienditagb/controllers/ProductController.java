package com.integrador1.tienditagb.controllers;

import com.integrador1.tienditagb.models.*;
import com.integrador1.tienditagb.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

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

    @GetMapping("/export")
    public ResponseEntity<InputStreamResource> exportProducts() throws Exception{
        ByteArrayInputStream stream = productService.exportProducts();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=provides.xls");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
    }

    @GetMapping("/report")
    public ResponseEntity<Map<String, Object>> reportProduct(){
        try{
            List<Product> listaProducts = this.productService.getAllProduct();

            List<Map<String, Object>> jsonListProduct = new ArrayList<>();
            for (Product product : listaProducts) {
                Map<String, Object> jsonProduct = new HashMap<>();

                Category category = this.categoryService.getCategorybyId(product.getCategory());

                jsonProduct.put("product", product);
                jsonProduct.put("category", category);

                jsonListProduct.add(jsonProduct);
            }

            Map<String, Object> response = new HashMap<>();

            response.put("products", jsonListProduct);

            return ResponseEntity.ok(response);
        }catch(Exception ex){
            return null;
        }
    }

}
