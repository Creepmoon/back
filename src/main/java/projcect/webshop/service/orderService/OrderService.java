package projcect.webshop.service.orderService;


import projcect.webshop.Dto.Requests.OrderRequest;
import projcect.webshop.Dto.model.OrderModel;
import projcect.webshop.Dto.model.PersonModel;

import java.util.List;

public interface OrderService {

    public void CreateOrder(OrderRequest orderRequest, PersonModel owner);

    public List<OrderModel> getAllOrders();

    public void finishOrder(OrderModel orderModel);

    public List<OrderModel> GetAllUserOrders(PersonModel personModel);
}
