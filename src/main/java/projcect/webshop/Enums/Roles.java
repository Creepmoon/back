package projcect.webshop.Enums;

import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {
    Admin,
    USER;


    public String getAuthority(){ return this.name();}

}
