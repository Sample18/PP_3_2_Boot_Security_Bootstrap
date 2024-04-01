package ru.kata.spring.boot_security.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class Utils {

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public Utils(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    public void createMockData() {
        Role role1 = new Role();
        role1.setName("ROLE_ADMIN");
        roleService.create(role1);
        Role role2 = new Role();
        role2.setName("ROLE_USER");
        roleService.create(role2);

        User user = new User();
        user.setUsername("user");
        user.setPassword("user");
        user.setName("test user");
        user.setSurname("test surname");
        user.setDepartment("IT");
        user.setSalary(100);
        Set<Role> userRole = new HashSet<>(roleService.findByNameIn(Collections.singletonList("ROLE_USER")));
        user.setRoles(userRole);
        userService.create(user);

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setName("test admin");
        admin.setSurname("test surname");
        admin.setDepartment("HR");
        admin.setSalary(150);
        Set<Role> adminRole = new HashSet<>(roleService.findByNameIn(Collections.singletonList("ROLE_ADMIN")));
        admin.setRoles(adminRole);
        userService.create(admin);
    }
}
