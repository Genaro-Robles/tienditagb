 package com.integrador1.tienditagb.services;

import com.integrador1.tienditagb.models.Sales;
import com.integrador1.tienditagb.models.SalesDetails;

import java.util.List;

public interface SalesDetailsService {
    public SalesDetails newSalesDetails(SalesDetails salesDetails);
    public List<SalesDetails> getAllSalesDetails();
    public List<SalesDetails> getSalesDetailsBySale(int sale);
    public SalesDetails getSalesDetailsbyId(int id);
    public String deleteSalesDetails(int id);
    public String renewSalesDetails(int id);
}
