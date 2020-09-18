package pl.ardas.restfullapi.controller;

import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ardas.restfullapi.model.dto.UserDto;
import pl.ardas.restfullapi.model.request.UserDetailsRequest;
import pl.ardas.restfullapi.model.response.UserRest;
import pl.ardas.restfullapi.service.UserService;

@RestController
@RequestMapping("users")
class UserController {

    private UserService userService;

    @PostMapping
    UserRest createUser(@RequestBody UserDetailsRequest user){

        UserRest returnUser = new UserRest();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);

        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, returnUser);

        return returnUser;
    }
}
