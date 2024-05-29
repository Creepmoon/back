package projcect.webshop.Repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projcect.webshop.Domain.Person;
import java.util.List;


@Repository
public interface PersonRepository extends CrudRepository<Person,Integer> {


    Person findByEmail(String Email);

    boolean existsByEmail(String Email);

    List<Person> getAllBy();


}
