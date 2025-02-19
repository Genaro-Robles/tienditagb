package com.integrador1.tienditagb.controllers;

import java.io.ByteArrayInputStream;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.integrador1.tienditagb.models.*;
import com.integrador1.tienditagb.services.*;

@RestController
@RequestMapping("/buys")
public class BuysController {

    @Autowired
    private BuysService buysService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private CategoryService categoryService;

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

    @GetMapping("/export")
    public ResponseEntity<InputStreamResource> exportBuys() throws Exception{
        ByteArrayInputStream stream = buysService.exportBuys();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=ReporteCompras.xls");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
    }

    @GetMapping("/report")
    public ResponseEntity<Map<String, Object>> reportBuy(){
        try{
            List<Buys> listaBuys = this.buysService.getAllBuys();

            List<Map<String, Object>> jsonListBuy = new ArrayList<>();
            for (Buys buys : listaBuys) {
                Map<String, Object> jsonBuy = new HashMap<>();

                Product product = this.productService.getProductbyId(buys.getProduct());
                User user = this.userService.getUserbyId(buys.getUser());
                Provider provider = this.providerService.getProviderById(buys.getProvider());
                Category category = this.categoryService.getCategorybyId(product.getCategory());
                Role role = this.roleService.getRolebyId(user.getRol());

                jsonBuy.put("buy", buys);
                jsonBuy.put("user", user);
                jsonBuy.put("rol", role);
                jsonBuy.put("product", product);
                jsonBuy.put("category", category);
                jsonBuy.put("provider", provider);

                jsonListBuy.add(jsonBuy);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("buys", jsonListBuy);

            return ResponseEntity.ok(response);
        }catch(Exception ex){
            return null;
        }
    }

}
