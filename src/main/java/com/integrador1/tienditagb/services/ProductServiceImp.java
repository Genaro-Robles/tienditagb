package com.integrador1.tienditagb.services;

import com.integrador1.tienditagb.Exceptions.ProductNotFoundException;
import com.integrador1.tienditagb.models.Product;
import com.integrador1.tienditagb.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product newProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsActive() {
        return productRepository.findByStatus(true);
    }

    @Override
    public List<Product> getProductsbyCategoryActive(int category) {
        return productRepository.findByStatusAndCategory(true,category);
    }

    @Override
    public Product getProductbyId(int id) {
        return productRepository.findById(id).orElseThrow(()->new ProductNotFoundException(id));
    }

    @Override
    public Product updateProduct(Product newproduct, int id) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(newproduct.getName());
                    product.setImage(newproduct.getImage());
                    product.setCategory(newproduct.getCategory());
                    product.setStock(newproduct.getStock());
                    product.setPrecioUnitario(newproduct.getPrecioUnitario());
                    return productRepository.save(product);
                }).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public String deleteProduct(int id) {
        productRepository.findById(id)
                .map(product -> {
                    product.setStatus(false);
                    return productRepository.save(product);
                }).orElseThrow(() -> new ProductNotFoundException(id));
        return "Producto con el "+id+" a sido eliminado";
    }

    @Override
    public String renewProduct(int id) {
        productRepository.findById(id)
                .map(product -> {
                    product.setStatus(true);
                    return productRepository.save(product);
                }).orElseThrow(() -> new ProductNotFoundException(id));
        return "Producto con el "+id+" a sido restaurado";
    }

    @Override
    public void updateStockProduct(int id, int purchasedQuantity){
        Product product = this.productRepository.findById(id)
        .orElseThrow(() -> new ProductNotFoundException(id));

        int stockActual = product.getStock();
        int newStock = stockActual + purchasedQuantity;

        product.setStock(newStock);

        this.productRepository.save(product);
    }
}
