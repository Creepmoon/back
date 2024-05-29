package projcect.webshop.dao;

import lombok.RequiredArgsConstructor;


import org.springframework.stereotype.Component;
import projcect.webshop.Domain.Product;
import projcect.webshop.Dto.model.ProductModel;
import projcect.webshop.Repositories.ProductRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductDao {

  private final ProductRepository productRepository;

  public List<ProductModel> getAllProducts(){

      List<Product> products = productRepository.getAllBy();

      return products.stream()
              .map(ProductModel::fromEntity)
              .toList();

  }

  public void addNewProduct(ProductModel productModel){

      if(!productRepository.existsByName(productModel.getName())){
          Product product = Product.builder()
                  .name(productModel.getName())
                  .price(productModel.getPrice())
                  .description(productModel.getDescription())
                  .Picture(productModel.getPicture()).build();
          productRepository.save(product);
      }

  }

  public ProductModel GetProductModel(String name){
     return ProductModel.fromEntity(productRepository.getProductByName(name));
  }



}
