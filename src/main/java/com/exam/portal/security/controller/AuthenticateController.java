package com.exam.portal.security.controller;

import com.exam.portal.entity.UserEntity;
import com.exam.portal.security.JWTRequest;
import com.exam.portal.security.JWTResponse;
import com.exam.portal.security.UserDetailsServiceImpl;
import com.exam.portal.security.config.JwtUtil;
import com.exam.portal.security.helper.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.xml.soap.SAAJResult;
import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;


    //generate Token
    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JWTRequest jwtRequest) throws Exception {
        try {

            authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());

        }catch (UserNotFoundException e){
            e.printStackTrace();
            throw new Exception("user not Found ! ");
        }
        /////////authenticate

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JWTResponse(token));

    }

    private void authenticate(String usename , String password)throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usename,password));

        }catch (DisabledException e){
//            e.printStackTrace();
            throw new Exception("USER DISABLEd"+e.getMessage());
        }catch (BadCredentialsException e){
            throw new Exception("Invalid Credentials "+e.getMessage());
        }

    }

    //Return the Details of Current User
    @GetMapping("/current-user")
    public UserEntity getCurrentUser(Principal principal){
        return ((UserEntity)this.userDetailsService.loadUserByUsername(principal.getName()));

    }

}//end Class
