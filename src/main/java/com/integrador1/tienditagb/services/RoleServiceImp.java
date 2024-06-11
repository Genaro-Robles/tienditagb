package com.integrador1.tienditagb.services;

import com.integrador1.tienditagb.Exceptions.RoleNotFoundException;
import com.integrador1.tienditagb.models.Role;
import com.integrador1.tienditagb.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class RoleServiceImp implements RoleService{
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role newRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRolebyId(int id) {
        return roleRepository.findById(id).orElseThrow(()->new RoleNotFoundException(id));
    }

    @Override
    public Role updateRole(Role newrole, int id) {
        return roleRepository.findById(id)
                .map(role -> {
                    role.setName(newrole.getName());
                    return roleRepository.save(role);
                }).orElseThrow(() -> new RoleNotFoundException(id));
    }

    @Override
    public String deleteRole(int id) {
        roleRepository.findById(id)
                .map(role -> {
                    role.setStatus(false);
                    return roleRepository.save(role);
                }).orElseThrow(() -> new RoleNotFoundException(id));
        return "Rol con el "+id+" a sido eliminado";
    }

    @Override
    public String renewRole(int id) {
        roleRepository.findById(id)
                .map(role -> {
                    role.setStatus(true);
                    return roleRepository.save(role);
                }).orElseThrow(() -> new RoleNotFoundException(id));
        return "Rol con el "+id+" a sido restaurado";
    }
}
