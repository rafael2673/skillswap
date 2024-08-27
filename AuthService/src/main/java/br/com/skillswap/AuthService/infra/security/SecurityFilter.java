package br.com.skillswap.AuthService.infra.security;

import br.com.skillswap.AuthService.repository.UserRepository;
import br.com.skillswap.AuthService.service.TokenService;
import br.com.skillswap.AuthService.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {



    @Autowired
    TokenService tokenService;
    @Autowired
    UserRepository userRepository;

    /**
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = this.recoverToken(request);

        if (token != null && (!token.isBlank() && !token.isEmpty())) {
            String email = tokenService.validateToken(token);
            UserDetails user = userRepository.findByEmail(email).isPresent() ? userRepository.findByEmail(email).get() : null;

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(user, null, user != null ? user.getAuthorities() : null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || authHeader.isEmpty() || authHeader.isBlank()) return null;
        return authHeader.replace("Bearer ", "");

    }
}
