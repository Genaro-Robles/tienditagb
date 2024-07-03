package com.integrador1.tienditagb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.integrador1.tienditagb.models.Buys;
import com.integrador1.tienditagb.services.BuysService;
import com.integrador1.tienditagb.services.ProductService;

@RestController
@RequestMapping("/buys")
public class BuysController {

    @Autowired
    private BuysService buysService;

    @Autowired
    private ProductService productService;

    @PostMapping
    public String newBuys(@RequestBody Buys buys){
        try{
            buysService.newBuys(buys);
            productService.updateStockProduct(buys.getProduct(), 
            buys.getPurchasedQuantity(),false);

            return "Nueva compra ingresado con exito.";
        }catch(Exception ex){
            return "Error: " + ex.getMessage();
        }
    }

    @GetMapping
    public List<Buys> getAllBuys(){
        return buysService.getAllBuys();
    }

    @GetMapping("/{id}")
    public Buys getBuysById(@PathVariable int id){
        return buysService.getBuysById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteBuys(@PathVariable int id){
        return buysService.deleteBuys(id);
    }

    @PatchMapping("/{id}")
    public String renewBuys(@PathVariable int id){
        return buysService.renewBuys(id);
    }
}
