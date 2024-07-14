package com.integrador1.tienditagb.controllers;

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
    }
}
