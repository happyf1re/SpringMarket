package com.example.springdata.service;

import lombok.*;
import com.example.springdata.entity.User;
import com.example.springdata.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    private UserRepository repository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public User createOrUpdate(User user) {
        //по какой-то причине сеттеры и шеттеры из ломбока сюда не подтянулись
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return  repository.save(user);
    }

    @Transactional
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public List<User> findAll () {
        return repository.findAll();
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }


}
