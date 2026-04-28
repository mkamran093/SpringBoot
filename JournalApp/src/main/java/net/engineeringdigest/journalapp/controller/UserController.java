package net.engineeringdigest.journalapp.controller;

import net.engineeringdigest.journalapp.entity.User;
import net.engineeringdigest.journalapp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAll () {
        List<User> all = userService.getAll();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{myId}")
    public ResponseEntity<User> getUserById(@PathVariable ObjectId myId) {
        Optional<User> user = userService.getById(myId);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            userService.saveUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{username}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String username) {
        User old = userService.findByUserName(username);
        if (old != null) {
            old.setUserName(user.getUserName());
            old.setPassword(user.getPassword());
            userService.saveUser(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{myId}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable ObjectId myId) {
        Boolean deleted = userService.deleteById(myId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }



}
