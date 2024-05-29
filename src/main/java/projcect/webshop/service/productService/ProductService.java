package projcect.webshop.service.productService;


import projcect.webshop.Dto.Requests.ProductCreateRequest;
import projcect.webshop.Dto.model.ProductModel;

import java.util.List;

public interface ProductService {

    public void createProduct(ProductCreateRequest productCreateRequest);

    public List<ProductModel> getAllProducts();

}
