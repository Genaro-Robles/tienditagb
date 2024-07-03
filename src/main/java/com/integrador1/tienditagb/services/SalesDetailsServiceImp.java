package com.integrador1.tienditagb.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.integrador1.tienditagb.models.SalesDetails;
import com.integrador1.tienditagb.repository.SalesDetailsRepository;

@Service
public class SalesDetailsServiceImp implements SalesDetailsService {

    @Autowired
    private SalesDetailsRepository salesDetailsRepository;

    @Override
    public void newSalesDetails(SalesDetails salesDetails) {
        this.salesDetailsRepository.save(salesDetails);
    }

    @Override
    public List<SalesDetails> getByIdSales(int id) {
        return this.salesDetailsRepository.findAll();
    }
    
}
