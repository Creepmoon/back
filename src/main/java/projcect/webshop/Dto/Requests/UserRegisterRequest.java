package projcect.webshop.Dto.Requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegisterRequest {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
}
