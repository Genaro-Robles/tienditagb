package com.integrador1.tienditagb.services;
import com.integrador1.tienditagb.models.Product;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface ProductService {
    public Product newProduct(Product product);
    public List<Product> getAllProduct();
    public List<Product> getProductsActive();
    public List<Product> getProductsbyCategoryActive(int category);
    public Product getProductbyId(int id);
    public  Product updateProduct(Product product, int id);
    public String deleteProduct(int id);
    public String renewProduct(int id);
    public void updateStockProduct(int id,int purchasedQuantity,boolean operator);
    public ByteArrayInputStream exportProducts() throws Exception;
}
