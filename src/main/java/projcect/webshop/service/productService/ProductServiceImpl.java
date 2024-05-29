package projcect.webshop.service.productService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import projcect.webshop.Dto.Requests.ProductCreateRequest;
import projcect.webshop.Dto.model.ProductModel;
import projcect.webshop.dao.ProductDao;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductDao productDao;


    @Override
    public void createProduct(ProductCreateRequest productCreateRequest) {

        productDao.addNewProduct(
                ProductModel.builder()
                        .name(productCreateRequest.getName())
                        .description(productCreateRequest.getDiscrpiption())
                        .picture(productCreateRequest.getPicture())
                        .price(productCreateRequest.getPirce())
                        .build()
        );
    }

    @Override
    public List<ProductModel> getAllProducts(){
        return productDao.getAllProducts();
    }
}
