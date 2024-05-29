package projcect.webshop.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import projcect.webshop.Dto.model.PersonModel;
import projcect.webshop.dao.PersonDao;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final PersonDao personDao;


    @Override
    public List<PersonModel> getAllUsers() {

        return personDao.getAllUsers() ;
    }

    @Override
    public void lockUser(PersonModel personModel) {
        personDao.findUserByEmail(personModel.getEmail());

    }

    @Override
    public PersonModel getUser(String email){
        PersonModel personModel = personDao.getUserByEmail(email);
        return personModel;
    }

}
