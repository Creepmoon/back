package projcect.webshop.service.authService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import projcect.webshop.Dto.Requests.UserRegisterRequest;
import projcect.webshop.Dto.Response.LoginResponse;


import javax.security.auth.login.CredentialException;

import projcect.webshop.Dto.Requests.UserLoginRequest;


public interface UserAuthService {

    public LoginResponse login(UserLoginRequest userLoginRequest,
                               HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse) throws CredentialException;


    public void register(UserRegisterRequest userRegisterRequest);



}
