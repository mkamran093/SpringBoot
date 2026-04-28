package net.engineeringdigest.journalapp.service;

import lombok.NonNull;
import net.engineeringdigest.journalapp.entity.User;
import net.engineeringdigest.journalapp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getById(ObjectId myid) {
        return userRepository.findById(myid);
    }

    public Boolean deleteById(ObjectId myid) {
        if (userRepository.existsById(myid)) {
            userRepository.deleteById(myid);
            return true;
        }
        return false;
    }

    public User findByUserName (String username) {
        return userRepository.findByUserName(username);
    }
}
