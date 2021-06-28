package com.bestcommerce.controller;

import com.bestcommerce.security.jwt.JwtRequest;
import com.bestcommerce.security.jwt.JwtResponse;
import com.bestcommerce.security.jwt.JwtTokenUtil;
import com.bestcommerce.service.MerchantDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final MerchantDetailService merchantDetailService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> logIn(
            @RequestBody JwtRequest authenticationRequest,
            @RequestParam(defaultValue = "false") Boolean rememberMe) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final String token = jwtTokenUtil.generateToken(authenticationRequest.getUsername(),rememberMe);

        return ResponseEntity.status(HttpStatus.OK).body(new JwtResponse(token));

    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
