package com.project.timeRegistry.service.impl;

import com.project.timeRegistry.model.domain.User;
import com.project.timeRegistry.repository.UserRepository;
import com.project.timeRegistry.service.port.UserPort;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserPort {

    private UserRepository userRepository;

    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "user"));
    }
}
