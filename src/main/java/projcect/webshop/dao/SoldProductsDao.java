package projcect.webshop.dao;

import org.springframework.stereotype.Component;
import projcect.webshop.Domain.SoldProduct;
import projcect.webshop.Dto.model.PersonModel;
import projcect.webshop.Dto.model.SoldProductModel;
import projcect.webshop.Repositories.BucketRepository;
import projcect.webshop.Repositories.PersonRepository;
import projcect.webshop.Repositories.ProductRepository;
import projcect.webshop.Repositories.SoldProductRepository;

import java.util.List;

@Component
public class SoldProductsDao {

    SoldProductRepository soldProductRepository;

    ProductRepository productRepository;

    PersonRepository personRepository;
    BucketRepository bucketRepository;


    public void AddSoldProduct(SoldProductModel soldProductModel){

        SoldProduct soldProduct = SoldProduct.builder()
                .product(productRepository.findByName(soldProductModel.getProductModel().getName()))
                .owner(bucketRepository.findFirstByOwnerOrderByIdDesc(
                        personRepository.findByEmail(soldProductModel.getBucketModel().getOwner().getEmail())
                )).build();
        soldProductRepository.save(soldProduct);

    }

    public List<SoldProductModel> getAllProductByOwner(PersonModel personModel){
        List<SoldProduct> soldProducts = soldProductRepository.findAllByOwner(bucketRepository.findFirstByOwnerOrderByIdDesc(
                personRepository.findByEmail(personModel.getEmail())
        ));
        return soldProducts.stream()
                .map(SoldProductModel::fromEntity)
                .toList();
    }




}
