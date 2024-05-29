package projcect.webshop.service.authService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projcect.webshop.Dto.Requests.UserLoginRequest;
import projcect.webshop.Dto.Requests.UserRegisterRequest;
import projcect.webshop.Dto.model.TokenModel;
import projcect.webshop.dao.PersonDao;
import projcect.webshop.serviceSecurity.JWTServices.JwtService;

import javax.security.auth.login.CredentialException;
import projcect.webshop.Dto.Response.LoginResponse;
import projcect.webshop.Dto.model.PersonModel;


@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl  implements UserAuthService{

    private final PersonDao personDao;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginResponse login(UserLoginRequest userLoginRequest,
                               HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse) throws CredentialException {
        if (!personDao.isUserWithEmailExist(userLoginRequest.getEmail())){
            throw new CredentialException("Invalid email");
        }

        if(!passwordEncoder.matches(userLoginRequest.getPassword(),personDao.getUserByEmail(userLoginRequest.getEmail()).getPassword())){
            throw new CredentialException("Invalid password");
        }






        PersonModel personModel = personDao.getUserByEmail(userLoginRequest.getEmail());

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                personModel.getEmail(),
                personModel.getPassword(),
                personModel.getAuthorities()
        );

        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(token);
        SecurityContextHolder.setContext(securityContext);

        String jwt = jwtService.generateToken(personModel);



        return LoginResponse.builder()
                .email(personModel.getEmail())
                .token(
                        TokenModel.builder()
                                .email(personModel.getEmail())
                                .token(jwt)
                                .build()
                )
                .build();
    }

    @Override
    public void register(UserRegisterRequest userRegisterRequest) {
        System.out.println("1");
        PersonModel personModel= PersonModel.builder()
                .Email(userRegisterRequest.getEmail())
                .name(userRegisterRequest.getName())
                .PhoneNumber(userRegisterRequest.getPhoneNumber())
                .Password(passwordEncoder.encode(userRegisterRequest.getPassword()))
                .build();
        personDao.addNewUser(personModel);
    }
}
