package com.example.testcamel;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @GetMapping("/all")
    public List<User> getUser(){
        return userRepository.getUser();
    }
    @PostMapping("")
    public void save(@RequestBody User user){
        userRepository.save(user);
    }
}
