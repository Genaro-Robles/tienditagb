package com.integrador1.tienditagb.services;

import com.integrador1.tienditagb.models.Sales;

import java.util.List;

public interface SalesService {
    public Sales newSales(Sales sales);
    public List<Sales> getAllSales();
    public Sales getSalesbyId(int id);
    public String deleteSales(int id);
    public String renewSales(int id);
}
