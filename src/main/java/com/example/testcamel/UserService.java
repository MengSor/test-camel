package com.example.testcamel;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserRepository{
    private final JdbcClient jdbcClient;

    public UserService(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<User> getUser() {
        String sql = "select * from users";
        return jdbcClient
                .sql(sql)
                .query(User.class)
                .list();
    }

    @Override
    public User getUserById(Long id) {
        String sql = "select * from users where id = ?";
        return jdbcClient.sql(sql)
                .param(id)
                .query(User.class)
                .single();
    }

    @Override
    public void save(User user) {
        String sql = "insert into users (id,name,email,phone) values (?,?,?,?)";
        jdbcClient
                .sql(sql)
                .params(List.of(user.id(),user.name(),user.email(),user.phone()))
                .update();
    }
}
