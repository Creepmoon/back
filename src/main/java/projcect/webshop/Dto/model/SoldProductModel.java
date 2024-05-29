package projcect.webshop.Dto.model;

import lombok.Builder;
import lombok.Data;
import projcect.webshop.Domain.SoldProduct;


import java.util.Optional;

@Data
@Builder
public class SoldProductModel {

    ProductModel productModel;
    BucketModel bucketModel;

    public static SoldProductModel fromEntity(SoldProduct soldProduct){
        return SoldProductModel.builder()
                .bucketModel(Optional.ofNullable(soldProduct.getOwner()).map(BucketModel::fromEntity).orElse(null))
                .productModel(Optional.ofNullable(soldProduct.getProduct()).map(ProductModel::fromEntity).orElse(null))
                .build();
    }

}
