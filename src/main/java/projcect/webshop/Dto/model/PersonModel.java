package projcect.webshop.Dto.model;


import lombok.Builder;
import lombok.Data;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import projcect.webshop.Enums.*;

import projcect.webshop.Domain.Person;

import java.util.Collection;
import java.util.Collections;

@Data
@Builder
public class PersonModel implements UserDetails {
    Roles role;

    String name;

    String Email;

    String Password;

    Status status;

    String PhoneNumber;

    public static PersonModel fromEntity(Person person){
        return PersonModel.builder()
                .role(person.getRole())
                .Email(person.getEmail())
                .name(person.getName())
                .Password(person.getPassword())
                .status(person.getStatus())
                .PhoneNumber(person.getPhoneNumber()).build();
    }




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}








