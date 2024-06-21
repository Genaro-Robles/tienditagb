package com.integrador1.tienditagb.services;

import java.util.List;

import com.integrador1.tienditagb.Exceptions.RoleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integrador1.tienditagb.Exceptions.ProviderNotFoundException;
import com.integrador1.tienditagb.models.Provider;
import com.integrador1.tienditagb.repository.ProviderRepository;

@Service
public class ProviderServiceImp implements ProviderService{
    
    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public Provider newProvider(Provider provider) {
        return this.providerRepository.save(provider);
    }

    @Override
    public List<Provider> getAllProvider() {
        return this.providerRepository.findAll();
    }

    @Override
    public Provider getProviderById(int id) {
        return this.providerRepository.findById(id)
        .orElseThrow(() -> new ProviderNotFoundException(id));
    }

    @Override
    public Provider updateProvider(Provider newProvider, int id) {
        return this.providerRepository.findById(id)
        .map(provider -> {
            provider.setName(newProvider.getName());
            provider.setAddress(newProvider.getAddress());
            provider.setRuc(newProvider.getRuc());
            provider.setPhone(newProvider.getPhone());
            return this.providerRepository.save(provider);
        }).orElseThrow(() -> new ProviderNotFoundException(id));
    }

    @Override
    public String deleteProvider(int id) {
        providerRepository.findById(id)
        .map(provider -> {
            provider.setStatus(false);
            return providerRepository.save(provider);
        }).orElseThrow(() -> new ProviderNotFoundException(id));
            return "Proveedor con el id: " + id + " a sido eliminado.";
    }

    @Override
    public String renewProvider(int id) {
        providerRepository.findById(id)
                .map(provider -> {
                    provider.setStatus(true);
                    return providerRepository.save(provider);
                }).orElseThrow(() -> new ProviderNotFoundException(id));
        return "Proveedor con el id: " + id + " a sido restaurado.";
    }
    
}
