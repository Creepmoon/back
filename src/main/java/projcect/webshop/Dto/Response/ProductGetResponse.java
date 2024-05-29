package projcect.webshop.Dto.Response;

import lombok.Builder;
import lombok.Data;
import projcect.webshop.Dto.model.ProductModel;


import java.net.URL;

@Data
@Builder
public class ProductGetResponse {
    private String name;

    private String description;

    private URL picture;

    private Integer price;

    public static ProductGetResponse fromProductModel(ProductModel productModel){
        return ProductGetResponse.builder()
                .name(productModel.getName())
                .description(productModel.getDescription())
                .picture(productModel.getPicture())
                .price(productModel.getPrice())
                .build();
    }


}
