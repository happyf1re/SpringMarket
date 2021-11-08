package com.example.springdata.controller;

import com.example.springdata.entity.User;
import com.example.springdata.repository.RoleRepository;
import com.example.springdata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String indexUserPage(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user_views/user";
    }

    @GetMapping("/{id}")
    public String editUser(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("roles", roleRepository.findAll());
        return "user_views/user_form";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute(new User());
        return "user_views/user_form";
    }

    @PostMapping("/update")
    public String updateUser(User user) {
        userService.createOrUpdate(user);
        return "redirect:/user";
    }

    @GetMapping("/delete/{id}")
    public String removeUser(@PathVariable(value = "id") Long id) {
        userService.deleteById(id);
        return "redirect:/user";
    }
}
