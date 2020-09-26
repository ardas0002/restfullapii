package pl.ardas.restfullapi.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import pl.ardas.restfullapi.model.dto.UserDto;

public interface UserService extends UserDetailsService {

    UserDto createUser(UserDto user);
    UserDto getUserByEmail(String email);
    UserDto getUserById(String id);
    List<UserDto> getUsers();
}