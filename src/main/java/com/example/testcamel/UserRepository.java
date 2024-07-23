package com.example.testcamel;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    List<User> getUser();
    User getUserById(Long id);
    void save(User user);
}
