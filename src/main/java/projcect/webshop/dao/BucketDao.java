package projcect.webshop.dao;

import org.springframework.stereotype.Component;
import projcect.webshop.Domain.Bucket;
import projcect.webshop.Dto.model.BucketModel;
import projcect.webshop.Dto.model.PersonModel;
import projcect.webshop.Repositories.BucketRepository;
import projcect.webshop.Repositories.PersonRepository;
import projcect.webshop.Repositories.SoldProductRepository;

import java.util.List;

@Component
public class BucketDao {
    BucketRepository bucketRepository;
    SoldProductRepository soldProductRepository;
    PersonRepository personRepository;


    public BucketModel GetUserBucket(PersonModel personModel){
        return BucketModel.fromEntity(bucketRepository
                .findFirstByOwnerOrderByIdDesc(personRepository
                .findByEmail(personModel.getEmail())));
    }

    public void AddBucket(BucketModel bucketModel){

        Bucket bucket = Bucket.builder()
                .owner(personRepository.findByEmail(bucketModel.getOwner().getEmail()))
                .price(bucketModel.getPrice()).build();
        bucketRepository.save(bucket);

    }

    public List<BucketModel> GetAllBucketUser(PersonModel personModel){

        List<Bucket> buckets = bucketRepository.findBucketByOwner(personRepository.findByEmail(personModel.getEmail()));

        return buckets.stream()
                .map(BucketModel::fromEntity)
                .toList();
    }



}
