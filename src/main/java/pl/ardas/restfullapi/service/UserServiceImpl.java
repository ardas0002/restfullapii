package pl.ardas.restfullapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.ardas.restfullapi.model.dto.UserDto;
import pl.ardas.restfullapi.model.entity.UserEntity;
import pl.ardas.restfullapi.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public UserDto createUser(UserDto user){
    	
    	UserEntity storedUserEntity = userRepository.findUserByEmail(user.getEmail());
    	
    	if(storedUserEntity != null) throw new RuntimeException("User with this email already exists");
    	
    	UserEntity userEntity = new UserEntity();      
    	
    	BeanUtils.copyProperties(user, userEntity);
    	
    	userEntity.setUserId(UUID.randomUUID().toString());
    	userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    	
    	UserEntity storedUserDetails = userRepository.save(userEntity);
    	    	
        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, returnValue);

        return returnValue;
    }
    
    public UserDto getUserById(String id) {
    	UserEntity userEntity = userRepository.findUserByUserId(id);
    	
    	if(userEntity == null) throw new UsernameNotFoundException(id);
    	
    	UserDto returnValue = new UserDto();
    	BeanUtils.copyProperties(userEntity, returnValue);
    	
    	return returnValue;
    }
	
	public UserDto getUserByEmail(String email) {
		UserEntity userEntity = userRepository.findUserByEmail(email);
		
		if(userEntity == null) throw new UsernameNotFoundException(email);
		
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
	}
	
	public List<UserDto> getUsers(){
		Iterable<UserEntity> iterable = userRepository.findAll();
		
		List<UserEntity> entities = new ArrayList<>();
		
		for(UserEntity userEntity : iterable) {
			entities.add(userEntity);
		}
		
		List<UserDto> users = new ArrayList<>();
		
		for(UserEntity userEntity : entities) {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(userEntity, userDto);
			users.add(userDto);
		}
		
		return users;
	} 
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findUserByEmail(email);
				
		if(userEntity == null) throw new UsernameNotFoundException(email);
		
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}
}
