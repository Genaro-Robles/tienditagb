package com.integrador1.tienditagb.controllers;

import com.integrador1.tienditagb.models.Auth;
import com.integrador1.tienditagb.models.User;
import com.integrador1.tienditagb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public String newUser(@RequestBody User user){
        try {
            userService.newUser(user);
            return "Nuevo usuario ingresado con exito";
        } catch (Exception ex) {
            return "Error: "+ex;
        }
    }
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUser();
    }

    @PostMapping("/auth")
    public List<User> getAllUserbyAuth(@RequestBody Auth usuario){
        return userService.getAllUserbyAuth(usuario.getCorreo(),usuario.getPassword());
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id){
        return userService.getUserbyId(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable int id){
        return  userService.updateUser(user,id);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id){
        return userService.deleteUser(id);
    }

    @PatchMapping("/{id}")
    public String renewUser(@PathVariable int id){
        return userService.renewUser(id);
    }
}
