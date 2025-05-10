package com.arutha.service.users;

import com.arutha.api.response.users.CurrentUserUsersResponse;
import com.arutha.model.users.Users;
import com.arutha.repository.users.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for User.
 */
@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE" + user.getRole().getRoleName());
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                authorities);
    }

    /**
     * Get Current User.
     *
     * @param userEmailAddress - user email
     * @return CurrentUserUsersResponse
     */
    public CurrentUserUsersResponse getCurrentUser(String userEmailAddress) {
        Users user = userRepository.findByEmail(userEmailAddress);
        CurrentUserUsersResponse response = new CurrentUserUsersResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setRoleName(user.getRole().getRoleName());
        return response;

    }
}
