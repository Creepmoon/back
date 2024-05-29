package projcect.webshop.serviceSecurity.Filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import projcect.webshop.Dto.model.PersonModel;
import projcect.webshop.dao.PersonDao;
import projcect.webshop.serviceSecurity.JWTServices.JwtService;

import java.io.IOException;
import java.util.Objects;

import static org.springframework.util.ObjectUtils.isEmpty;

@Component
@RequiredArgsConstructor
public class FilterJWT extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final PersonDao personDao;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String jwt = getToken(request);

        if (Objects.isNull(jwt)) {
            filterChain.doFilter(request, response);
            return;
        }

        PersonModel personModel = jwtService.parseToken(jwt);

        if (Objects.isNull(personModel)) {
            filterChain.doFilter(request, response);
            return;
        }

        if (!personDao.isUserWithEmailExist(personModel.getEmail())) {
            filterChain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                personModel.getEmail(),
                null,
                personModel.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isEmpty(header) || !header.startsWith("Bearer ")) {
            System.out.println("НЕ ВИДИТ ХЭДЕР");
            return null;
        }

        final String[] parts = header.split(" ");
        if (parts.length != 2) {
            return null;
        }

        return parts[1].trim();
    }
}
