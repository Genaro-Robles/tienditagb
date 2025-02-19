package com.integrador1.tienditagb.services;

import java.io.ByteArrayInputStream;
import java.util.List;
import com.integrador1.tienditagb.models.Provider;

public interface ProviderService {
    public Provider newProvider(Provider provider);
    public List<Provider> getAllProvider();
    public Provider getProviderById(int id);
    public Provider updateProvider(Provider newProvider, int id);
    public String deleteProvider(int id);
    public String renewProvider(int id);
    public ByteArrayInputStream exportProvider() throws Exception;
}
