package projcect.webshop.service.UserService;



import projcect.webshop.Dto.model.PersonModel;

import java.util.List;

public interface UserService {

    public List<PersonModel> getAllUsers();

    public void lockUser(PersonModel personModel);

    public PersonModel getUser(String email);
}
