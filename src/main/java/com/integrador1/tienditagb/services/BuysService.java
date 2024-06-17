package com.integrador1.tienditagb.services;

import java.util.List;
import com.integrador1.tienditagb.models.Buys;

public interface BuysService {
    public Buys newBuys(Buys buys);
    public List<Buys> getAllBuys();
    public Buys getBuysById(int id);
    public String deleteBuys(int id);
}
