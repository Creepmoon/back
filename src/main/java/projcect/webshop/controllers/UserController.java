package projcect.webshop.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import projcect.webshop.Dto.Requests.ProductRequest;
import projcect.webshop.Dto.model.PersonModel;
import projcect.webshop.Dto.model.ProductModel;
import projcect.webshop.service.UserService.UserService;
import projcect.webshop.service.bucketService.BucketService;
import projcect.webshop.serviceSecurity.JWTServices.JwtService;

@RestController
@RequestMapping("/api/CakeShop")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final BucketService bucketService;
    private final JwtService jwtService;

    @GetMapping("/UserInfo")
    public PersonModel GetUserData(HttpServletRequest request){
        System.out.println("1");
        return userService.getUser(jwtService.getUserModelByReqToken(request).getEmail());
    }

    @PostMapping("/user/createbucket")
    public HttpStatus Bucket(
           @RequestParam ProductRequest productRequest,
           HttpServletRequest request) {
        bucketService.CreateBucket(productRequest, jwtService.getUserModelByReqToken(request));
        return HttpStatus.CREATED;
    }

    @PostMapping("/user/updatebucket")
    public HttpStatus UpdateBucket( @RequestParam ProductRequest productRequest,
                                    HttpServletRequest request){
        bucketService.UpdateBucket(productRequest ,jwtService.getUserModelByReqToken(request));
        return HttpStatus.OK;
    }




}
