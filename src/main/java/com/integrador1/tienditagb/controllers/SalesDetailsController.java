package com.integrador1.tienditagb.controllers;

<<<<<<< HEAD
import com.integrador1.tienditagb.models.Sales;
import com.integrador1.tienditagb.models.SalesDetails;
import com.integrador1.tienditagb.services.SalesDetailsService;
import com.integrador1.tienditagb.services.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salesdetails")
public class SalesDetailsController {
    @Autowired
    private SalesDetailsService salesDetailsService;

    @PostMapping
    public String newSalesDetails(@RequestBody SalesDetails salesDetails){
        try {
            salesDetailsService.newSalesDetails(salesDetails);
            return "Nuevo detalle de venta ingresado con exito";
        } catch (Exception ex) {
            return "Error: "+ex;
        }
    }
    @GetMapping
    public List<SalesDetails> getAllSalesDetails(){
        return salesDetailsService.getAllSalesDetails();
    }

    @GetMapping("/sale/{id}")
    public List<SalesDetails> getAllSalesDetails(@PathVariable int id){
        return salesDetailsService.getSalesDetailsBySale(id);
    }

    @GetMapping("/{id}")
    public SalesDetails getSalesDetailsById(@PathVariable int id){
        return salesDetailsService.getSalesDetailsbyId(id);
    }


    @DeleteMapping("/{id}")
    public String deleteSalesDetails(@PathVariable int id){
        return salesDetailsService.deleteSalesDetails(id);
    }
    @PatchMapping("/{id}")
    public String renewSalesDetails(@PathVariable int id){
        return salesDetailsService.renewSalesDetails(id);
=======
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

    @GetMapping("/{sale}")
    public List<SalesDetails> getDetailsSaleByIdSale(@PathVariable int sale){
        try{
            return this.salesDetailsService.getSalesDetailsBySale(sale);
        }catch(Exception ex){
            return null;
        }
>>>>>>> origin/main
    }
}
