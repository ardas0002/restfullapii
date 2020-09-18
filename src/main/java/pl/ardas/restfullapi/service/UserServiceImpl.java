package pl.ardas.restfullapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pl.ardas.restfullapi.model.dto.UserDto;
import pl.ardas.restfullapi.model.entity.UserEntity;
import pl.ardas.restfullapi.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserDto createUser(UserDto user){
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        UserEntity storedUserDetails = userRepository.save(userEntity);

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, returnValue);

        return returnValue;
    }
}
