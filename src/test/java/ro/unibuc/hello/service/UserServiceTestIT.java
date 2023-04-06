package ro.unibuc.hello.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import ro.unibuc.hello.data.user.User;
import ro.unibuc.hello.data.user.UserDTO;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Tag("IT")
public class UserServiceTestIT {
    @Autowired
    UserService userService;

    String firstName = "Popescu";
    String lastName = "Georgescu";

    @Test
    void test_createValidUser_savesUserInDb()
    {
        User user = new User();
        String userId = userService.saveUser(new UserDTO(firstName, lastName));
        try
        {
            user = userService.getUserById(userId);
        }
        catch (Exception E)
        {
            Assertions.fail();
        }

        Assertions.assertEquals(userId, user.getId());
        Assertions.assertEquals(firstName, user.getFirstName());
        Assertions.assertEquals(lastName, user.getLastName());

        userService.deleteUserById(userId);
    }

    @Test
    void test_deleteValidUser_deletesUserFromDb()
    {
        String userId = userService.saveUser(new UserDTO(firstName, lastName));
        userService.deleteUserById(userId);

        Exception exception = assertThrows(Exception.class, () -> {
            userService.getUserById(userId);
        });
        Assertions.assertEquals(HttpStatus.NOT_FOUND.toString(), exception.getMessage());
    }

    @Test
    void test_getInvalidUser_throwsNotFound()
    {
        Exception exception = assertThrows(Exception.class, () -> {
            userService.getUserById("invalid");
        });
        Assertions.assertEquals(HttpStatus.NOT_FOUND.toString(), exception.getMessage());
    }
}
