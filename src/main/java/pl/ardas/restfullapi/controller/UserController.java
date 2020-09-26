package pl.ardas.restfullapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.ardas.restfullapi.exceptions.UserServiceException;
import pl.ardas.restfullapi.model.dto.UserDto;
import pl.ardas.restfullapi.model.request.UserDetailsRequest;
import pl.ardas.restfullapi.model.response.ErrorMessages;
import pl.ardas.restfullapi.model.response.UserRest;
import pl.ardas.restfullapi.service.UserService;

@RestController
@RequestMapping("users")
class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/register", produces = {MediaType.APPLICATION_JSON_VALUE,
    											 MediaType.APPLICATION_XML_VALUE},
    								 consumes = {MediaType.APPLICATION_JSON_VALUE,
											 	 MediaType.APPLICATION_XML_VALUE})
    UserRest createUser(@RequestBody UserDetailsRequest user){

    	if(user.isValid() == false) throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage().toString());
        
    	UserRest returnUser = new UserRest();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);

        UserDto createdUser = userService.createUser(userDto);

        BeanUtils.copyProperties(createdUser, returnUser);


        return returnUser;
    }
    
    @GetMapping
    List<UserRest> getUsers(){
    	
    	List<UserDto> usersDto = userService.getUsers();
    	
    	List<UserRest> usersRest = new ArrayList<>();
    	
    	for(UserDto userDto : usersDto) {
    		UserRest userRest = new UserRest();
    		BeanUtils.copyProperties(userDto, userRest);
    		usersRest.add(userRest);
    	}
    	
    	return usersRest;
    }
    
    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE,
    		MediaType.APPLICATION_XML_VALUE})
    UserRest getUser(@PathVariable String id) {
    	UserDto userDto = userService.getUserById(id);
    	
    	UserRest userRest = new UserRest();
    	BeanUtils.copyProperties(userDto, userRest);
    	
    	return userRest;
    }
}
