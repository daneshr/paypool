package ir.dotin.rest;

import ir.dotin.model.User;
import ir.dotin.repository.UserRepository;
import ir.dotin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.accepted().build();
    }

    @PatchMapping(value = "/{userId}/disable")
    public ResponseEntity deactivateUser(@PathVariable Long userId) {
        userService.deactivateUser(userId);
        return ResponseEntity.accepted().build();
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<User> getStatus(@PathVariable Long userId){
        return ResponseEntity.accepted().body(userService.getUserStatus(userId));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List> getUsers(){
        return ResponseEntity.accepted().body(userService.getAllUsers());
    }
}
