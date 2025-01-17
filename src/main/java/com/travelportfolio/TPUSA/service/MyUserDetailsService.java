package com.travelportfolio.TPUSA.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.travelportfolio.TPUSA.model.User;
import com.travelportfolio.TPUSA.model.UserPrincipal;
import com.travelportfolio.TPUSA.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }
    System.out.println("user we got " + user);
    return new UserPrincipal(user);
  }

}
