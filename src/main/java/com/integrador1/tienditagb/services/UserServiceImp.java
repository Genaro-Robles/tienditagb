package com.integrador1.tienditagb.services;

import com.integrador1.tienditagb.Exceptions.UserNotFoundException;
import com.integrador1.tienditagb.models.User;
import com.integrador1.tienditagb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User newUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getAllUserbyAuth(String correo, String password) {
        return userRepository.findByCorreoAndPassword(correo,password);
    }

    @Override
    public User getUserbyId(int id) {
        return userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
    }

    @Override
    public User updateUser(User newuser, int id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(newuser.getName());
                    user.setLastname(newuser.getLastname());
                    user.setDni(newuser.getDni());
                    user.setCorreo(newuser.getCorreo());
                    user.setPhone(newuser.getPhone());
                    user.setDireccion(newuser.getDireccion());
                    user.setRol(newuser.getRol());
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public String deleteUser(int id) {
        userRepository.findById(id)
                .map(user -> {
                    user.setStatus(false);
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
        return "Usuario con el "+id+" a sido eliminado";
    }
    @Override
    public String renewUser(int id) {
        userRepository.findById(id)
                .map(user -> {
                    user.setStatus(true);
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
        return "Usuario con el "+id+" a sido restaurado";
    }
}
