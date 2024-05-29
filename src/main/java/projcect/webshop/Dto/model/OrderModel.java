package projcect.webshop.Dto.model;

import lombok.Builder;
import lombok.Data;
import projcect.webshop.Domain.Order;


import java.util.Date;
import java.util.Optional;

@Data
@Builder
public class OrderModel {
    BucketModel bucketModel;
    Date date;
    String message;
    String address;
    PersonModel owner;
    boolean finish;

    public static OrderModel fromEntity(Order order){
        return OrderModel.builder()
                .bucketModel(Optional.ofNullable(order.getBucket()).map(BucketModel::fromEntity).orElse(null))
                .date(order.getDateOfCreation())
                .message(order.getMessage())
                .address(order.getAdress())
                .owner(Optional.ofNullable(order.getOwner()).map(PersonModel::fromEntity).orElse(null))
                .finish(order.isFinish())
                .build();
    }

}
