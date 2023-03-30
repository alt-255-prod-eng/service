package ro.unibuc.hello.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import ro.unibuc.hello.data.user.User;
import ro.unibuc.hello.data.user.UserDTO;
import ro.unibuc.hello.data.user.UserRepository;

@SpringBootTest
@Tag("IT")
public class UserServiceTestIT {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @Test
    void test_buildUserFromFirstNameLastName_returnUserWithInfo() {
        //Arrange
        String firstName = "Popescu";
        String lastName = "Georgescu";
        User user = new User();
        //Act
        String userId = userService.saveUser(new UserDTO(firstName, lastName));
        try {
            user = userService.getUserById(userId);
        } catch (Exception E) {
            Assertions.fail();
        }
        //Assert
        Assertions.assertEquals(userId, user.getId());
        Assertions.assertEquals(firstName, user.getFirstName());
        Assertions.assertEquals(lastName, user.getLastName());
    }

    @Test
    void test_buildAndDeleteUser_returnNotFound() throws Exception {
        //Arrange
        String firstName = "Popescu";
        String lastName = "Georgescu";
        User user = new User();
        //Act
        String userId = userService.saveUser(new UserDTO(firstName, lastName));
        userService.deleteUserById(userId);
        try {
            user = userService.getUserById(userId);
        } catch (Exception E) {
            Assertions.assertEquals(E.getMessage(), HttpStatus.NOT_FOUND.toString());
        }
    }
}
