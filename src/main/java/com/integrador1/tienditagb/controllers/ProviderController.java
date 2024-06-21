package com.integrador1.tienditagb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
            providerService.newProvider(provider);

            return "Nuevo Proveedor creado con exito.";
        }catch(Exception ex){
            return "Error: "+ ex.getMessage();
        }
    }

    @GetMapping
    public List<Provider> getAllProviders(){
        return providerService.getAllProvider();
    }

    @GetMapping("/{id}")
    public Provider getProviderById(@PathVariable int id){
        return providerService.getProviderById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteProvider(@PathVariable int id){
        return providerService.deleteProvider(id);
    }

    @PutMapping("/{id}")
    public Provider updatedProvider(@RequestBody Provider provider, @PathVariable int id){
        return providerService.updateProvider(provider, id);
    }
    @PatchMapping("/{id}")
    public String renewProvider(@PathVariable int id){
        return providerService.renewProvider(id);
    }
}
