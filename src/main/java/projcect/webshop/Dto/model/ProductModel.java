package projcect.webshop.Dto.model;

import lombok.Builder;
import lombok.Data;
import projcect.webshop.Domain.Product;


import java.net.URL;

@Data
@Builder
public class ProductModel  {
    String name;

    String description;

    URL picture;

    Integer price;

    public static ProductModel fromEntity(Product product){
        return ProductModel.builder()
                .name(product.getName())
                .description(product.getDescription())
                .picture(product.getPicture())
                .price(product.getPrice())
                .build();
    }
}
