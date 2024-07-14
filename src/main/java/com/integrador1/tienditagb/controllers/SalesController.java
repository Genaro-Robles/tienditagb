package com.integrador1.tienditagb.controllers;

import com.integrador1.tienditagb.models.Product;
import com.integrador1.tienditagb.models.Sales;
import com.integrador1.tienditagb.models.SalesDetails;
import com.integrador1.tienditagb.models.User;
import com.integrador1.tienditagb.services.ProductService;
import com.integrador1.tienditagb.services.SalesDetailsService;
import com.integrador1.tienditagb.services.SalesService;
import com.integrador1.tienditagb.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.*;

@RestController
@RequestMapping("/sales")
public class SalesController {
    @Autowired
    private SalesService salesService;

    @Autowired
    private SalesDetailsService salesDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

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

    @GetMapping("/export")
    public ResponseEntity<InputStreamResource> exportSales() throws Exception{
        ByteArrayInputStream stream = salesService.exportSales();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=provides.xls");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
    }

    @GetMapping("/report")
    public ResponseEntity<Map<String, Object>> reportSales(){
        try{
            List<Sales> listaSales = this.salesService.getAllSales();

            List<Map<String, Object>> jsonListSale = new ArrayList<>();
            for (Sales sales : listaSales) {
                Map<String, Object> jsonSale = new HashMap<>();

                User user = this.userService.getUserbyId(sales.getUsuario());
                List<SalesDetails> salesDetailsList = this.salesDetailsService.getSalesDetailsBySale(sales.getId());
                List<Product> products = new ArrayList<>();

                for (SalesDetails salesDetails : salesDetailsList) {
                    Product product = this.productService.getProductbyId(salesDetails.getProduct());

                    products.add(product);
                }

                jsonSale.put("sale", sales);
                jsonSale.put("user", user);
                jsonSale.put("details_sale", salesDetailsList);
                jsonSale.put("products", products);

                jsonListSale.add(jsonSale);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("sales", jsonListSale);

            System.out.println("bien");

            return ResponseEntity.ok(response);
        }catch(Exception ex){
            System.out.println("mal");
            return null;
        }
    }
}
