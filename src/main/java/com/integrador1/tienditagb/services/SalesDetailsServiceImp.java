package com.integrador1.tienditagb.services;

import com.integrador1.tienditagb.Exceptions.SalesDetailsNotFoundException;
import com.integrador1.tienditagb.models.SalesDetails;
import com.integrador1.tienditagb.repository.SalesDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class SalesDetailsServiceImp implements SalesDetailsService{
    @Autowired
    private SalesDetailsRepository salesDetailsRepository;

    @Override
    public SalesDetails newSalesDetails(SalesDetails salesDetails) {
        return salesDetailsRepository.save(salesDetails);
    }

    @Override
    public List<SalesDetails> getAllSalesDetails() {
        return salesDetailsRepository.findAll();
    }

    @Override
    public List<SalesDetails> getSalesDetailsBySale(int sale) {
        return salesDetailsRepository.findBySale(sale);
    }

    @Override
    public SalesDetails getSalesDetailsbyId(int id) {
        return salesDetailsRepository.findById(id).orElseThrow(()->new SalesDetailsNotFoundException(id));
    }

    @Override
    public String deleteSalesDetails(int id) {
        salesDetailsRepository.findById(id)
                .map(salesDetails -> {
                    salesDetails.setStatus(false);
                    return salesDetailsRepository.save(salesDetails);
                }).orElseThrow(() -> new SalesDetailsNotFoundException(id));
        return "Detalle de venta con el "+id+" a sido eliminado";
    }

    @Override
    public String renewSalesDetails(int id) {
        salesDetailsRepository.findById(id)
                .map(salesDetails -> {
                    salesDetails.setStatus(true);
                    return salesDetailsRepository.save(salesDetails);
                }).orElseThrow(() -> new SalesDetailsNotFoundException(id));
        return "Venta con el "+id+" a sido restaurado";
    }
}
