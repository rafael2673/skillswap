package br.com.skillswap.AuthService.service;

import br.com.skillswap.AuthService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * @param email email
     * @return UserDetails
     * @throws UsernameNotFoundException UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) {
        if (userRepository.findByEmail(email).isPresent())
            return userRepository.findByEmail(email).get();
        else
            throw new UsernameNotFoundException("");
    }
}
