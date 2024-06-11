package com.integrador1.tienditagb.services;
import com.integrador1.tienditagb.models.Role;


import java.util.List;

public interface RoleService {

    public Role newRole(Role role);
    public List<Role> getAllRole();
    public Role getRolebyId(int id);
    public  Role updateRole(Role role, int id);
    public String deleteRole(int id);
    public String renewRole(int id);
}
