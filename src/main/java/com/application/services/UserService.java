package com.application.services;

import com.application.dtos.UserRequestDto;
import com.application.entities.User;
import com.application.repositories.UserRepository;
import com.application.services.exceptions.ObjectNotFoundException;
import com.application.services.exceptions.ValidateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("user not found: "+id));
    }

    public User insert(UserRequestDto dto) {
        if(dto.getName() == null) {
            throw new ValidateException("The name field is empty");
        }
        return userRepository.insert(dto.toUser());
    }

    public User updateById(String id, UserRequestDto dto) {
        if(dto.getName() == null) {
            throw new ValidateException("The name field is empty");
        }
        User user = findById(id);
        user.setName(dto.getName());
        return userRepository.save(user);
    }

    public void deleteById(String id) {
        User user = findById(id);
        userRepository.delete(user);
    }
}
