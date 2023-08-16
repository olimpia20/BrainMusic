package repository;

import music.model.User;
import org.springframework.stereotype.Component;

public interface UserRepository extends Repository<User,Integer>{

    void deleteById(Integer id);

    User findByUsernamePassword(String username, String password);
}
