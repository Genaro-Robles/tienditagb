package com.integrador1.tienditagb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integrador1.tienditagb.Exceptions.BuysNotFoundException;
import com.integrador1.tienditagb.models.Buys;
import com.integrador1.tienditagb.repository.BuysRepository;

@Service
public class BuysServiceImpl implements BuysService{
    
    @Autowired
    private BuysRepository buysRepository;

    @Override
    public Buys newBuys(Buys buys) {
        return buysRepository.save(buys);
    }

    @Override
    public List<Buys> getAllBuys() {
        return buysRepository.findAll();
    }

    @Override
    public Buys getBuysById(int id) {
        return buysRepository.findById(id).
        orElseThrow(() -> new BuysNotFoundException(id));
    }

    @Override
    public String deleteBuys(int id) {
        buysRepository.findById(id)
        .map(buys -> {
            buys.setStatus(false);
            return buysRepository.save(buys);
        }).orElseThrow(() -> new BuysNotFoundException(id));
            return "Compra con el id: " + id + " a sido eliminada.";

    }

    @Override
    public String renewBuys(int id) {
        buysRepository.findById(id)
                .map(buys -> {
                    buys.setStatus(true);
                    return buysRepository.save(buys);
                }).orElseThrow(() -> new BuysNotFoundException(id));
        return "Compra con el id: " + id + " a sido restaurada.";

    }
}
