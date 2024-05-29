package projcect.webshop.dao;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import projcect.webshop.Domain.Person;
import projcect.webshop.Dto.model.PersonModel;
import projcect.webshop.Enums.Roles;
import projcect.webshop.Enums.Status;
import projcect.webshop.Repositories.PersonRepository;

import java.util.List;


@RequiredArgsConstructor
@Component
public class PersonDao {

    private final PersonRepository personRepository;

    public void addNewUser(PersonModel personModel){
        System.out.println("2");
        Person person = Person.builder()
                .name(personModel.getName())
                .email(personModel.getEmail())
                .status(Status.Working)
                .role(Roles.USER)
                .password(personModel.getPassword())
                .phoneNumber(personModel.getPhoneNumber()).build();
        personRepository.save(person);
    }
    public PersonModel getUserByEmail(String Email){
        return PersonModel.fromEntity(personRepository.findByEmail(Email));
    }

    public Boolean isUserWithEmailExist(String Email){
       return personRepository.existsByEmail(Email);
    }

    public Boolean isUserPasswordValid(String Email, String Password){
        Person person = personRepository.findByEmail(Email);
        return null;
    }


    public List<PersonModel> getAllUsers(){
        System.out.println(1);
        List<Person> personList = personRepository.getAllBy();

        return personList.stream()
                .map(PersonModel::fromEntity)
                .toList();
    }

    public void findUserByEmail(String email){
        Person person = personRepository.findByEmail(email);
        person.setStatus(Status.blocked);
        personRepository.save(person);
    }


}
