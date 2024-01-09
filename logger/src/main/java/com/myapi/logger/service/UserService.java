package com.myapi.logger.service;

import com.myapi.logger.dto.UserDTO;
import com.myapi.logger.entity.User;
import com.myapi.logger.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public User addUser(UserDTO userDTO) {
        return userRepository.save(User.builder()
                        .age(userDTO.getAge())
                        .email(userDTO.getEmail())
                        .name(userDTO.getName())
                        .password(userDTO.getPassword())
                        .build());
    }

}
