package com.integrador1.tienditagb.services;

import com.integrador1.tienditagb.Exceptions.SalesNotFoundException;
import com.integrador1.tienditagb.models.Sales;
import com.integrador1.tienditagb.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class SalesServiceImp implements SalesService{
    @Autowired
    private SalesRepository salesRepository;

    @Override
    public Sales newSales(Sales sales) {
        return salesRepository.save(sales);
    }

    @Override
    public List<Sales> getAllSales() {
        return salesRepository.findAll();
    }

    @Override
    public Sales getSalesbyId(int id) {
        return salesRepository.findById(id).orElseThrow(()->new SalesNotFoundException(id));
    }

    @Override
    public String deleteSales(int id) {
        salesRepository.findById(id)
                .map(sales -> {
                    sales.setStatus(false);
                    return salesRepository.save(sales);
                }).orElseThrow(() -> new SalesNotFoundException(id));
        return "Venta con el "+id+" a sido eliminado";
    }

    @Override
    public String renewSales(int id) {
        salesRepository.findById(id)
                .map(sales -> {
                    sales.setStatus(true);
                    return salesRepository.save(sales);
                }).orElseThrow(() -> new SalesNotFoundException(id));
        return "Venta con el "+id+" a sido restaurado";
    }
}
