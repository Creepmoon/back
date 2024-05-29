package projcect.webshop.Dto.Requests;

import lombok.Data;

@Data
public class UserLoginRequest {
    String email;
    String password;
}
