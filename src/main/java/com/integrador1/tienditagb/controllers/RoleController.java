package com.integrador1.tienditagb.controllers;

import com.integrador1.tienditagb.models.Role;
import com.integrador1.tienditagb.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping
    public String newRole(@RequestBody Role role){
        try {
            roleService.newRole(role);
            return "Nuevo rol ingresado con exito";
        } catch (Exception ex) {
            return "Error: "+ex;
        }
    }
    @GetMapping
    public List<Role> getAllRoles(){
        return roleService.getAllRole();
    }

    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable int id){
        return roleService.getRolebyId(id);
    }

    @PutMapping("/{id}")
    public Role updateRole(@RequestBody Role role, @PathVariable int id){
        return  roleService.updateRole(role,id);
    }

    @DeleteMapping("/{id}")
    public String deleteRole(@PathVariable int id){
        return roleService.deleteRole(id);
    }
    @PatchMapping("/{id}")
    public String renewRole(@PathVariable int id){
        return roleService.renewRole(id);
    }
}
