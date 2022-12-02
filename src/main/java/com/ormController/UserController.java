package com.ormController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ormModel.User;
import com.ormRepository.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping(value="/signup")
	public @ResponseBody String signup(@RequestBody User user) {
		
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		userRepository.save(user);
		return "Successfully Signed Up";
	}
}
