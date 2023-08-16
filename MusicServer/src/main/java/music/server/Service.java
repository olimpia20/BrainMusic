package music.server;

import music.model.User;
import repository.UserRepository;

import java.util.List;


public class Service {
    UserRepository userRepository;

    public Service(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user){
        return userRepository.add(user);
    }
    public User updateUser(User user, Integer id){
        userRepository.update(user,id);
        user.setId(id);
        return user;
    }
    public void deleteUser(User user){
        userRepository.delete(user);
    }
    public User findUserById(Integer id){
        return userRepository.findById(id);
    }
    public List<User> findAllUsers(){
        return (List<User>) userRepository.findAll();
    }


}
