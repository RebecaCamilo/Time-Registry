package com.project.timeRegistry.service.impl;


import com.project.timeRegistry.model.domain.User;
import com.project.timeRegistry.model.request.UserRequest;
import com.project.timeRegistry.repository.UserRepository;
import com.project.timeRegistry.service.port.UserPort;
import lombok.AllArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserPort {

    private UserRepository userRepository;

    @Override
    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "user"));
    }

    @Override
    public User create(UserRequest userRequest) {
        return null;
    }

    @Override
    public User update(Long id, UserRequest userRequest) {
        return null;
    }

    @Override
    public User deactivate(Long id) {
        return null;
    }

    @Override
    public User activate(Long id) {
        return null;
    }
}

