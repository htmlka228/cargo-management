package com.example.application.service;

import com.example.application.entity.User;
import com.example.application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsManager {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    @Override
    public void createUser(UserDetails user) {
        if (user instanceof User internalUser) {
            userRepository.save(internalUser);
        } else {
            log.error("User cannot be saved because it's not an instance of internal user model");
        }
    }

    @Override
    public void updateUser(UserDetails user) {
        if (user instanceof User internalUser) {
            userRepository.save(internalUser);
        } else {
            log.error("User cannot be saved because it's not an instance of internal user model");
        }
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteByUsername(username);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        log.error("Password change is not supported");
    }

    @Override
    public boolean userExists(String username) {
        return loadUserByUsername(username) != null;
    }
}
