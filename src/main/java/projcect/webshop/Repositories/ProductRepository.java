package projcect.webshop.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projcect.webshop.Domain.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    Product findByName(String Name);

    List<Product> getAllBy();
    Product getProductByName(String name);
    boolean existsByName(String name);
}
