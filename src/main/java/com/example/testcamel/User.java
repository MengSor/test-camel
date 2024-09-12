package com.example.testcamel;

public record User(
        Long id,
        String name,
        String email,
        String username,
        String password,
        Long phone,
        String city
) {
}
