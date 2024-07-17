package com.integrador1.tienditagb.controllers;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/export")
    public ResponseEntity<InputStreamResource> exportProvider() throws Exception{
        ByteArrayInputStream stream = providerService.exportProvider();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=ReporteProveedores.xls");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
    }
}
