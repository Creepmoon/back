package projcect.webshop.serviceSecurity.JWTServices;

import jakarta.servlet.http.HttpServletRequest;
import projcect.webshop.Dto.model.PersonModel;
import org.springframework.security.core.userdetails.UserDetails;
import io.jsonwebtoken.Claims;
public interface JwtService {

    PersonModel parseToken(String jwt);

    String generateToken(PersonModel personModel);

    PersonModel getUserModelByReqToken(HttpServletRequest request);

    boolean isTokenExpired(String token);
    public boolean validateToken(String token, UserDetails userDetails);

    Claims extractAllClaims(String token);

    public String extractUsername(String token);

}
