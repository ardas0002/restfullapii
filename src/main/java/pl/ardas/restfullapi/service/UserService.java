package pl.ardas.restfullapi.service;

import pl.ardas.restfullapi.model.dto.UserDto;

public interface UserService {

    UserDto createUser(UserDto user);
}
