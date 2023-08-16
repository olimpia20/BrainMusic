package music;

import music.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/users/")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/test")
    public  String test(@RequestParam(value="name", defaultValue="Hello") String name) {
        return name.toUpperCase();
    }


    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = (List<User>) userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer id){
        User user = userRepository.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user){
        User userNew = userRepository.add(user);
        return new ResponseEntity<>(userNew, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user,@PathVariable("id") Integer id){
        userRepository.update(user,id);
        user.setId(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id){
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find/{username}/{password}")
    public ResponseEntity<User> getUserByUsernamePassword(@PathVariable("username") String username, @PathVariable("password") String password){
        User user = userRepository.findByUsernamePassword(username,password);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
