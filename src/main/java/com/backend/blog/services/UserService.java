package com.backend.blog.services;
import java.util.List;

import com.backend.blog.entities.User;
import com.backend.blog.payloads.UserDto;

public interface UserService {
    Object JwtResponse = null;
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user,Integer userId);
    UserDto getUserById(Integer id);
    UserDto getUserByEmail(String email);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);
    UserDto registerNewUser(UserDto userDto);
    public User dtoToUser(UserDto userDto);

    public UserDto userToDto(User user);

}
