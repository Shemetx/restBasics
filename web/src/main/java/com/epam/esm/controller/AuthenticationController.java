package com.epam.esm.controller;

import com.epam.esm.convertor.UserDtoConvertor;
import com.epam.esm.domain.User;
import com.epam.esm.dto.AuthUserDto;
import com.epam.esm.dto.UserDto;
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

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    private UserService userService;

    private UserDtoConvertor userDtoConvertor;

    @Autowired
    public void setUserDtoConvertor(UserDtoConvertor userDtoConvertor) {
        this.userDtoConvertor = userDtoConvertor;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setJwtTokenProvider(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<Object,Object>> login(@RequestBody AuthUserDto dto) {
        try {
            String username = dto.getUsername();
            authenticationManager.authenticate
                    (new UsernamePasswordAuthenticationToken(username,dto.getPassword()));
            User user = userService.findByUsername(username);

            String token = jwtTokenProvider.createToken(username,user.getRole());

            Map<Object, Object> response = new HashMap<>();
            response.put("username",username);
            response.put("token",token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("/signUp")
    public ResponseEntity<UserDto> signUp(@Valid @RequestBody UserDto dto) {
        User user = userDtoConvertor.dtoToEntity(dto);
        userService.signUp(user);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
