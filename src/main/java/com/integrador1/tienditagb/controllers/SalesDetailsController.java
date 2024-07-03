package com.integrador1.tienditagb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.integrador1.tienditagb.models.SalesDetails;
import com.integrador1.tienditagb.services.ProductService;
import com.integrador1.tienditagb.services.SalesDetailsService;
import java.util.List;

@RestController
@RequestMapping("/sales_details")
public class SalesDetailsController {
    
    @Autowired
    private SalesDetailsService salesDetailsService;

    @Autowired
    private ProductService productService;

    @PostMapping
    public String newSalesDetails(@RequestBody List<SalesDetails> salesDetails){
        try{
            for(SalesDetails details: salesDetails){
                this.salesDetailsService.newSalesDetails(details);
                this.productService.updateStockProduct(details.getProduct(), 
                details.getCantidad(), true);
            }
            return "Productos vendidos exitosamente";
        }catch(Exception ex){
            return "Error: " + ex.getMessage(); 
        }
    }
}
