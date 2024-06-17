package com.integrador1.tienditagb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integrador1.tienditagb.models.Buys;
import com.integrador1.tienditagb.services.BuysService;

@RestController
@RequestMapping("/buys")
public class BuysController {

    @Autowired
    private BuysService buysService;

    @PostMapping
    public String newBuys(@RequestBody Buys buys){
        try{
            this.buysService.newBuys(buys);

            return "Nueva compra ingresado con exito.";
        }catch(Exception ex){
            return "Error: " + ex.getMessage();
        }
    }

    @GetMapping
    public List<Buys> getAllBuys(){
        return this.buysService.getAllBuys();
    }

    @GetMapping("/{id}")
    public Buys getBuysById(@PathVariable int id){
        return this.buysService.getBuysById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteBuys(@PathVariable int id){
        return this.buysService.deleteBuys(id);
    }
}
