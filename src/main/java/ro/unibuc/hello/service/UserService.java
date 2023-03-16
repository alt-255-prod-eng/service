package ro.unibuc.hello.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ro.unibuc.hello.data.User.User;
import ro.unibuc.hello.data.User.UserDTO;
import ro.unibuc.hello.data.User.UserRepository;
import ro.unibuc.hello.data.product.ProductEntity;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void saveUser(UserDTO userDTO) {
        userRepository.save(new User(userDTO));
    }

    public User getUserById(String id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new Exception(HttpStatus.NOT_FOUND.toString()));
    }

    public void deleteUserById(String id) throws Exception {
        userRepository.deleteById(id);
    }

}
