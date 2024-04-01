package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String startAdmin(Model model) {
        model.addAttribute("usersList", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin";
    }

    @GetMapping(value = "/remove")
    public String removeUser(@RequestParam Integer id) {
        userService.remove(id);
        return "redirect:/admin";
    }

    @PostMapping(value = "/edit")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam(value = "selectedRoles", required = false) String[] selectedRoles) {
        userService.update(user, selectedRoles);
        return "redirect:/admin";
    }

    @GetMapping(value = "/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "addUser";
    }

    @PostMapping(value = "/new")
    public String createUser(@ModelAttribute("user") User user, @RequestParam(value = "selectedRoles", required = false) String[] selectedRoles) {
        userService.create(user, selectedRoles);
        return "redirect:/admin";
    }

}