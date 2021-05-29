package com.epam.esm.controller;

import com.epam.esm.convertor.UserDtoConvertor;
import com.epam.esm.domain.User;
import com.epam.esm.dto.AuthUserDto;
import com.epam.esm.dto.UserSignUpDto;
import com.epam.esm.dto.UserViewDto;
import com.epam.esm.security.jwt.JwtTokenProvider;
import com.epam.esm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Catches users requests to login and to sign up by mapping /auth
 */
@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    private UserService userService;

    private UserDtoConvertor userDtoConvertor;

    /**
     * Sets user dto convertor.
     *
     * @param userDtoConvertor the user dto convertor
     */
    @Autowired
    public void setUserDtoConvertor(UserDtoConvertor userDtoConvertor) {
        this.userDtoConvertor = userDtoConvertor;
    }

    /**
     * Sets authentication manager.
     *
     * @param authenticationManager the authentication manager
     */
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * Sets jwt token provider.
     *
     * @param jwtTokenProvider the jwt token provider
     */
    @Autowired
    public void setJwtTokenProvider(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * Sets user service.
     *
     * @param userService the user service
     */
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Login.
     *
     * @param dto the dto
     * @return the response entity
     */
    @PostMapping("/login")
    public ResponseEntity<Map<Object, Object>> login(@RequestBody AuthUserDto dto) {
        try {
            String username = dto.getUsername();
            authenticationManager.authenticate
                    (new UsernamePasswordAuthenticationToken(username, dto.getPassword()));
            User user = userService.findByUsername(username);

            String token = jwtTokenProvider.createToken(username, user.getRole());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    /**
     * Sign up.
     *
     * @param dto the dto
     * @return the response entity
     */
    @PostMapping("/signUp")
    public ResponseEntity<UserViewDto> signUp(@Valid @RequestBody UserSignUpDto dto) {
        User user = userDtoConvertor.dtoToEntity(dto);
        return new ResponseEntity<>(
                userDtoConvertor.toModel(user), HttpStatus.CREATED);
    }
}
