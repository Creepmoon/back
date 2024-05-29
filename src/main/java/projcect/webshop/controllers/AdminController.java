package projcect.webshop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import projcect.webshop.Dto.Requests.ProductCreateRequest;
import projcect.webshop.Dto.model.OrderModel;
import projcect.webshop.Dto.model.PersonModel;
import projcect.webshop.service.UserService.UserServiceImpl;
import projcect.webshop.service.orderService.OrderServiceImpl;
import projcect.webshop.service.productService.ProductServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/CakeShop/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ProductServiceImpl productService;
    private final UserServiceImpl userService;
    private final OrderServiceImpl orderService;

    @PostMapping("/createProduct")
    HttpStatus CreateProduct(@RequestBody ProductCreateRequest request){
        productService.createProduct(request);
        return HttpStatus.CREATED;
    }

    @GetMapping("/getAllUsers")
    List<PersonModel> GetAllPersons(){
        return userService.getAllUsers();
    }

    @GetMapping("/getAllOrders")
    List<OrderModel> GetAllOrderModel(){
        return orderService.getAllOrders();
    }

    @PostMapping("/lockUser")
    HttpStatus LockPerson(PersonModel personModel){
        userService.lockUser(personModel);
        return HttpStatus.LOCKED;
    }

    @PostMapping("/finishOrder")
    HttpStatus Finish(OrderModel orderModel){
        orderService.finishOrder(orderModel);
        return HttpStatus.OK;
    }


}
