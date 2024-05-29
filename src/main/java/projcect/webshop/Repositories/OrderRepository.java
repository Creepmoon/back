package projcect.webshop.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projcect.webshop.Domain.Order;
import projcect.webshop.Domain.Person;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {


    List<Order> findAll();

    List<Order> findAllByOwner(Person person);

}
