package com.integrador1.tienditagb.services;
import com.integrador1.tienditagb.models.User;

import java.util.List;

public interface UserService {
    public User newUser(User user);
    public List<User> getAllUser();
    public List<User> getAllUserbyAuth(String correo, String password);
    public User getUserbyId(int id);
    public  User updateUser(User user, int id);
    public String deleteUser(int id);
    public String renewUser(int id);
}
