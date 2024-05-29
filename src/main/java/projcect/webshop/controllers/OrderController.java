package projcect.webshop.controllers;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import projcect.webshop.Dto.Requests.OrderRequest;
import projcect.webshop.Dto.model.PersonModel;
import projcect.webshop.service.UserService.UserService;
import projcect.webshop.service.orderService.OrderService;
import projcect.webshop.serviceSecurity.JWTServices.JwtService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/CakeShop/order")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/Create/")
    public HttpStatus CreateOrder(@RequestParam OrderRequest orderRequest,
                                  HttpServletRequest httpServletRequest){
        orderService.CreateOrder(orderRequest, jwtService.getUserModelByReqToken(httpServletRequest));
        return HttpStatus.CREATED;
    }


}
