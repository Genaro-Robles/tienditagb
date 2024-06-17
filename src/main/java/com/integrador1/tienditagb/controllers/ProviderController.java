package com.integrador1.tienditagb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integrador1.tienditagb.models.Provider;
import com.integrador1.tienditagb.services.ProviderService;


@RestController
@RequestMapping("/provider")
public class ProviderController {

    @Autowired
    private ProviderService providerService;
    
    @PostMapping
    public String newProvider(@RequestBody Provider provider){
        try{
            this.providerService.newProvider(provider);

            return "Nuevo Proveedor creado con exito.";
        }catch(Exception ex){
            return "Error: "+ ex.getMessage();
        }
    }

    @GetMapping
    public List<Provider> getAllProviders(){
        return this.providerService.getAllProvider();
    }

    @GetMapping("/{id}")
    public Provider getProviderById(@PathVariable int id){
        return this.providerService.getProviderById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteProvider(@PathVariable int id){
        return this.providerService.deleteProvider(id);
    }

    @PutMapping("/{id}")
    public Provider updatedProvider(@RequestBody Provider provider, @PathVariable int id){
        return this.providerService.updateProvider(provider, id);
    }
}
