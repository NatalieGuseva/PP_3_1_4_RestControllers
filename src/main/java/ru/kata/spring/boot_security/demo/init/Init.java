package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class Init {

    private final RoleRepository roleDao;
    private final UserService userService;

    @Autowired
    public Init(RoleRepository roleDao, UserService userService) {
        this.roleDao = roleDao;
        this.userService = userService;
    }

    @PostConstruct
    private void init() {
        roleDao.save(new Role(1L, "ROLE_ADMIN"));
        roleDao.save(new Role(2L, "ROLE_USER"));
        List<Role> adminRole = roleDao.findById(1L).stream().toList();
        List<Role> userRole = roleDao.findById(2L).stream().toList();
        userService.saveUser(new User("Наталья", "Михалева", 35,
                                "user@mail.ru", "user", adminRole));

        userService.saveUser(new User("Ivanov", "Ivan", 30,
                                "usera@mail.ru", "user", userRole));
    }
}
