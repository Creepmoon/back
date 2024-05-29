package projcect.webshop.Repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projcect.webshop.Domain.Bucket;
import projcect.webshop.Domain.Person;

import java.util.List;

@Repository
public interface BucketRepository extends CrudRepository<Bucket,Integer>{



    Bucket findFirstByOwnerOrderByIdDesc(Person person);

   List<Bucket> findBucketByOwner(Person person);

}
