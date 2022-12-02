package com.ormService;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ormModel.User;
import com.ormRepository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepo.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("This username is not present.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),getGrantedAuthorities(user));
	}
	
	 private Collection<GrantedAuthority> getGrantedAuthorities(User user){
		 
		 Collection<GrantedAuthority> authorities = new ArrayList<>();
		 if(user.getRole().equalsIgnoreCase("admin") || user.getRole().equalsIgnoreCase("approver")) { 
			 authorities.add(new SimpleGrantedAuthority("admin")); 
		 } 
	     authorities.add(new SimpleGrantedAuthority("NO_ROLE")); 
		 return authorities; 
	}
	
}
