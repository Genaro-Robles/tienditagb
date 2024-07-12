package com.integrador1.tienditagb.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integrador1.tienditagb.models.Product;
import com.integrador1.tienditagb.models.Sales;
import com.integrador1.tienditagb.models.SalesDetails;
import com.integrador1.tienditagb.models.User;
import com.integrador1.tienditagb.services.ProductService;
import com.integrador1.tienditagb.services.SalesDetailsService;
import com.integrador1.tienditagb.services.SalesService;
import com.integrador1.tienditagb.services.UserService;

@RestController
@RequestMapping("/boleta")
public class BoletaController {
    
    @Autowired
    private SalesDetailsService salesDetailsService;

    @Autowired
    private SalesService salesService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public  ResponseEntity<Map<String, Object>> boleta(@PathVariable int id){
        Map<String, Object> response = new HashMap<>();

        try{
            Sales sales = this.salesService.getSalesbyId(id);

            User user = this.userService.getUserbyId(sales.getUsuario());

            response.put("sales", sales);
            response.put("user", user);

            List<SalesDetails> salesDetails = this.salesDetailsService.getSalesDetailsBySale(id);
            List<Map<String, Object>> detallesVenta = new ArrayList<>();

            for (SalesDetails salesDetail : salesDetails) {
                Map<String, Object> detalles = new HashMap<>();

                detalles.put("sales_details", salesDetail);

                Product product = this.productService.getProductbyId(salesDetail.getProduct());

                detalles.put("product", product);

                detallesVenta.add(detalles);
            }

            response.put("details_sales", detallesVenta);

            return ResponseEntity.ok(response);
        }catch(Exception ex){
            return null;
        }
    }
}
