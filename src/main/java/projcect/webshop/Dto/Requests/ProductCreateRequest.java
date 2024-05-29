package projcect.webshop.Dto.Requests;

import lombok.Data;

import java.net.URL;

@Data
public class ProductCreateRequest {

    private String name;

    private String discrpiption;

    private URL picture;

    private Integer pirce;


}
