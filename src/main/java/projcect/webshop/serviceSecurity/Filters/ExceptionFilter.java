package projcect.webshop.serviceSecurity.Filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import projcect.webshop.Dto.Exception.ErrorResponse;

import java.io.IOException;

@Component
public class ExceptionFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
           filterChain.doFilter(request,response);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        }



    }
}
