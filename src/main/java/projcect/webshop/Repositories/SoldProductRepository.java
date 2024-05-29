package projcect.webshop.Repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projcect.webshop.Domain.Bucket;
import projcect.webshop.Domain.SoldProduct;

import java.util.List;

@Repository
public interface SoldProductRepository extends CrudRepository<SoldProduct, Integer>{
    List<SoldProduct> findAllByOwner(Bucket bucket);
}
