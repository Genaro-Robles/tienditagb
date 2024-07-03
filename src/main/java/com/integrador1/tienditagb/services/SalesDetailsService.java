package com.integrador1.tienditagb.services;

import com.integrador1.tienditagb.models.SalesDetails;
import java.util.List;

public interface SalesDetailsService {
    public void newSalesDetails(SalesDetails salesDetails);
    public List<SalesDetails> getByIdSales(int id);
}
