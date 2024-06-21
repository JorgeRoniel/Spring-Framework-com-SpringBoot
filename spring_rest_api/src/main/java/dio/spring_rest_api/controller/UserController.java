package dio.spring_rest_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dio.spring_rest_api.model.User;
import dio.spring_rest_api.repository.UserRepository;



@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserRepository repository;

    @GetMapping()
    public List<User> listUsers(){
        return repository.listAll();
    }

    @GetMapping("/{id}")
    public User findOne(@PathVariable("id") int id){
        return repository.finById(id);
    }

    @PostMapping()
    public void saveUser(@RequestBody User user){
        repository.save(user);
    }

    @PutMapping()
    public void updateUser(@RequestBody User user){
        repository.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") int id){
        repository.remove(id);
    }
}
