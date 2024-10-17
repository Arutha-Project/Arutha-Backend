package com.arutha.controller.auth;

import com.arutha.api.request.jwt.JwtApi;
import com.arutha.api.response.jwt.JwtResponse;
import com.arutha.constants.AppErrorCodes;
import com.arutha.exception.CustomException;
import com.arutha.service.users.CustomUserDetailsService;
import com.arutha.utill.JwtHelper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller class for Auth.
 */
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {


    private final CustomUserDetailsService customUserDetailsService;

    private final AuthenticationManager authenticationManager;

    private final JwtHelper jwtHelper;


    /**
     * Login.
     *
     * @param request - username and password
     * @return response token
     * @throws CustomException - on invalid credentials
     */
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtApi request) throws CustomException {
        // Authenticate username and password
        doAuthenticate(request.getEmail(), request.getPassword());
        // Fetch user details after authenticating
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.getEmail());
        String token = this.jwtHelper.generateToken(userDetails);
        // return response token
        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) throws CustomException {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new CustomException(AppErrorCodes.AuthErrorCodes.INVALID_CREDENTIALS,
                    "Invalid username or password");
        }
    }
}
