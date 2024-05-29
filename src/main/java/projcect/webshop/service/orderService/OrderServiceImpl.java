package projcect.webshop.service.orderService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import projcect.webshop.Dto.Requests.OrderRequest;
import projcect.webshop.Dto.model.OrderModel;
import projcect.webshop.Dto.model.PersonModel;
import projcect.webshop.dao.BucketDao;
import projcect.webshop.dao.OrderDao;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private BucketDao bucketDao;

    private OrderDao orderDao;



    @Override
    public void CreateOrder(OrderRequest orderRequest, PersonModel owner) {
        OrderModel orderModel = OrderModel.builder()
                .bucketModel(bucketDao.GetUserBucket(owner))
                .date(new Date())
                .message(orderRequest.getMessage())
                .address(orderRequest.getAddres())
                .owner(owner)
                .finish(false)
                .build();
        orderDao.AddNewOrder(orderModel);
    }

    @Override
    public List<OrderModel> getAllOrders() {
        return orderDao.GetAllOrders();
    }

    @Override
    public void finishOrder(OrderModel orderModel){
       orderDao.finishOrder(orderModel);
    }

    @Override
    public List<OrderModel> GetAllUserOrders(PersonModel personModel) {
        return orderDao.GetAllUserOrders(personModel);
    }

}
