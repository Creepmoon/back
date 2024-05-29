package projcect.webshop.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projcect.webshop.Dto.model.ProductModel;
import projcect.webshop.service.productService.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/CakeShop")
public class MainController {

    private final ProductService productService;


    @GetMapping("/getProducts")
    public List<ProductModel> GetProducts(HttpServletRequest request){
       return productService.getAllProducts();
    }
}
