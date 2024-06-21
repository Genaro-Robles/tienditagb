package com.integrador1.tienditagb.controllers;

import com.integrador1.tienditagb.models.Sales;
import com.integrador1.tienditagb.services.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SalesController {
    @Autowired
    private SalesService salesService;

    @PostMapping
    public String newSales(@RequestBody Sales sales){
        try {
            salesService.newSales(sales);
            return "Nueva venta ingresado con exito";
        } catch (Exception ex) {
            return "Error: "+ex;
        }
    }
    @GetMapping
    public List<Sales> getAllSales(){
        return salesService.getAllSales();
    }

    @GetMapping("/{id}")
    public Sales getSalesById(@PathVariable int id){
        return salesService.getSalesbyId(id);
    }


    @DeleteMapping("/{id}")
    public String deleteSales(@PathVariable int id){
        return salesService.deleteSales(id);
    }
    @PatchMapping("/{id}")
    public String renewSales(@PathVariable int id){
        return salesService.renewSales(id);
    }
}
