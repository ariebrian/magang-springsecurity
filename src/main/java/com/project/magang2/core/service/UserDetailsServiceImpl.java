package com.project.magang2.core.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.magang2.core.model.LoginModel;
import com.project.magang2.core.repository.LoginRepository;

import static java.util.Collections.emptyList;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service(value = "userService")
public class UserDetailsServiceImpl implements UserDetailsService, ServiceInterface {
    private LoginRepository applicationUserRepository;

    public UserDetailsServiceImpl(LoginRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginModel applicationUser = applicationUserRepository.findByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        System.out.println(applicationUser.getUsername());
        System.out.println(applicationUser.getRoles().getRole());
        System.out.println(applicationUser.getActor().getNama());
        
		List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(applicationUser.getRoles().getRole());

//        return new User(applicationUser.getUsername(),applicationUser.getPassword(), (Collection<? extends GrantedAuthority>) emptyList());
		return new User(applicationUser.getUsername(), applicationUser.getPassword(), authorityList);

    }
    
    @Override
    public LoginModel findOne(String username) {
		return applicationUserRepository.findByUsername(username);
	}
}