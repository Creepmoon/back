package projcect.webshop.Dto.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenModel {

    String email;

    String token;

}
