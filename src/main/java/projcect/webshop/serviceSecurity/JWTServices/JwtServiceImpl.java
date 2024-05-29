package projcect.webshop.serviceSecurity.JWTServices;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import projcect.webshop.Dto.model.PersonModel;
import projcect.webshop.Enums.Roles;
import projcect.webshop.dao.PersonDao;
import projcect.webshop.properties.Token;

import java.security.Key;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


import static org.springframework.util.ObjectUtils.isEmpty;


@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final Token token;

    private final PersonDao personDao;


    @Override
    public PersonModel parseToken(String jwt) {

        try{

            JwtParser jwtParser = Jwts.parserBuilder()
                    .setSigningKey(getSigninKey())
                    .build();

            Jws<Claims> parsedJwt = jwtParser.parseClaimsJws(jwt);

            Claims claims = parsedJwt.getBody();
            System.out.println(claims.getSubject());
            System.out.println(claims.get("role"));
            return PersonModel.builder()
                    .Email(claims.getSubject())
                    .role(Roles.valueOf(((String)claims.get("role"))))
                    .build();


        }catch (ExpiredJwtException e){
            throw new ExpiredJwtException(e.getHeader(), e.getClaims(), e.getMessage());
        }
    }

    @Override
    public String generateToken(PersonModel personModel) {
        Claims claims = Jwts.claims();
        claims.setSubject(personModel.getEmail());
        claims.put("role", personModel.getRole().getAuthority());

        System.out.println(personModel.getAuthorities());
        System.out.println(token.getSecret() + " " + token.getTimeToLive());
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + token.getTimeToLive());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(personModel.getEmail())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, token.getSecret())
                .compact();
    }





    @Override
    public PersonModel getUserModelByReqToken(HttpServletRequest request) {

        String jwt = getToken(request);
        PersonModel personModel = parseToken(jwt);
        return personDao.getUserByEmail(personModel.getEmail());


    }

    private String getToken(HttpServletRequest request){

        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if( isEmpty(header) || !header.startsWith("Bearer ")){
            return null;
        }
        final  String[] strs = header.split(" ");

        if (strs.length != 2){
            return null;
        }
        return strs[1].trim();


    }
    private Key getSigninKey(){
        byte[] KeyBytes = Base64.getDecoder().decode(token.getSecret());
        return Keys.hmacShaKeyFor(KeyBytes);
    }

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    @Override
    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(this.token.getSecret()).parseClaimsJws(token).getBody();
    }

    @Override
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    @Override
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }






}
