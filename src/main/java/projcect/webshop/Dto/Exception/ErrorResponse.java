package projcect.webshop.Dto.Exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private final String message;

    private final Object payload;

    public ErrorResponse(String message){
        this(message, null);
    }

    private ErrorResponse(String message, Object payload){
        this.message = message;
        this.payload = payload;
    }


}
