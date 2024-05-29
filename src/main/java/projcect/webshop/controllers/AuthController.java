package projcect.webshop.controllers;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projcect.webshop.Dto.Requests.UserLoginRequest;
import projcect.webshop.Dto.Requests.UserRegisterRequest;
import projcect.webshop.Dto.Response.LoginResponse;
import projcect.webshop.service.authService.UserAuthService;

import javax.security.auth.login.CredentialException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/shop")
public class AuthController {

    private final UserAuthService authService;


    @PostMapping("/register")
    public ResponseEntity<String> Register(@RequestBody UserRegisterRequest request){
        try {
            authService.register(request);
            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> Login(@RequestBody UserLoginRequest request,
                            HttpServletRequest httpServletRequest,
                            HttpServletResponse httpServletResponse) throws CredentialException {
    try {
        LoginResponse loginResponse = authService.login(request,httpServletRequest,httpServletResponse);
        Cookie cookie = new Cookie("jwt", loginResponse.getToken().getToken());
        cookie.setHttpOnly(true);
        cookie.setPath("/**");
        httpServletResponse.addCookie(cookie);
        return new ResponseEntity<>(loginResponse.getToken().getToken(), HttpStatus.OK);} catch (CredentialException e) {
        return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);} catch (Exception e) {
        return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }



}
