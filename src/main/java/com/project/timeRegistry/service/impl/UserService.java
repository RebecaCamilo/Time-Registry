package com.project.timeRegistry.service.impl;


import com.project.timeRegistry.model.domain.User;
import com.project.timeRegistry.repository.UserRepository;
import com.project.timeRegistry.service.port.UserPort;
import lombok.AllArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.project.timeRegistry.model.domain.UserStatus.ACTIVE;
import static com.project.timeRegistry.model.domain.UserStatus.INACTIVE;

@Service
@AllArgsConstructor
public class UserService implements UserPort {

    private UserRepository userRepository;

    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "user"));
    }

    @Override
    public User create(User user) {
        checkIfAlreadyExistsByLogin(user.getLogin());
        user.setStatus(ACTIVE);

        return userRepository.save(user);
    }

    @Override
    public User update(Long id, User user) {
        getById(id);
        checkIfAlreadyExistsByLogin(id, user.getLogin());

        return userRepository.save(user);
    }

    @Override
    public User deactivate(Long id) {
        User user = getById(id);
        user.setStatus(INACTIVE);

        return userRepository.save(user);
    }

    @Override
    public User activate(Long id) {
        User user = getById(id);
        user.setStatus(ACTIVE);

        return userRepository.save(user);
    }

    private void checkIfAlreadyExistsByLogin(String login) {
        if (userRepository.countByLogin(login) != 0) {
            throw new DataIntegrityViolationException(login);
        }
    }

    private void checkIfAlreadyExistsByLogin(Long id, String login) {
        Optional<User> user = getUserBylogin(login);

        if (user.isPresent() &&  !user.get().getId().equals(id)) {
            throw new DataIntegrityViolationException(login);
        }
    }

    private Optional<User> getUserBylogin(String login) {
        return userRepository.getByLogin(login);
    }
}

