package com.github.YourSergic1.domain.service;

import com.github.YourSergic1.datasource.service.RepositoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final RepositoryServiceImpl repositoryServiceImpl;

    @Autowired
    CustomUserDetailsService(RepositoryServiceImpl repositoryService) {
        this.repositoryServiceImpl = repositoryService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repositoryServiceImpl.getUser(username)
                .map(user -> org.springframework.security.core.userdetails.User.builder()
                        .username(user.getLogin())
                        .password(user.getPassword())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
}
