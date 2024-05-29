package projcect.webshop.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import projcect.webshop.serviceSecurity.Filters.FilterJWT;
import projcect.webshop.serviceSecurity.JWTServices.JwtService;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final FilterJWT filterJWT;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(FilterJWT filterJWT, JwtService jwtService, UserDetailsService userDetailsService){
        this.filterJWT = filterJWT;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }


    @Bean
    public DefaultSecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(config ->
                        config.requestMatchers(
                                        "/api/CakeShop/getProducts",
                                        "/api/shop/register",
                                        "/api/shop/login",
                                        "/api/CakeShop/sobaka").permitAll()
                                .requestMatchers("api/CakeShop/user/**").hasAuthority("USER")
                                .requestMatchers("api/CakeShop/admin/**").hasAuthority("Admin")
                                .anyRequest().authenticated())
                .formLogin(AbstractHttpConfigurer::disable);

        httpSecurity.addFilterBefore(filterJWT, UsernamePasswordAuthenticationFilter.class);


        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws  Exception{

        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and().build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
