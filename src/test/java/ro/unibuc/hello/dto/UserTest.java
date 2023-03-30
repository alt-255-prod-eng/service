package ro.unibuc.hello.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ro.unibuc.hello.data.user.User;
import ro.unibuc.hello.data.user.UserDTO;

@SpringBootTest
public class UserTest {
    UserDTO myUserDTO = new UserDTO("John", "Snow");
    UserDTO myUserDTOEmpty = new UserDTO();
    User myUser = new User(myUserDTO);
    User myUserEmpty = new User();

    @Test
    void test_constructor() {
        Assertions.assertSame("John", myUser.getFirstName());
        Assertions.assertSame("Snow", myUser.getLastName());
    }

    @Test
    void test_id() {
        myUserEmpty.setId("1");
        Assertions.assertSame("1", myUserEmpty.getId());
    }

    @Test
    void test_firstName() {
        myUserEmpty.setFirstName("Ion");
        Assertions.assertSame("Ion", myUserEmpty.getFirstName());
    }

    @Test
    void test_lastName() {
        myUserEmpty.setLastName("Popescu");
        Assertions.assertSame("Popescu", myUserEmpty.getLastName());
    }

    @Test
    void testDTO_constructor() {
        Assertions.assertSame("John", myUserDTO.getFirstName());
        Assertions.assertSame("Snow", myUserDTO.getLastName());
    }

    @Test
    void testDTO_firstName() {
        myUserDTOEmpty.setFirstName("Ion");
        Assertions.assertSame("Ion", myUserDTOEmpty.getFirstName());
    }

    @Test
    void testDTO_lastName() {
        myUserDTOEmpty.setLastName("Zapada");
        Assertions.assertSame("Zapada", myUserDTOEmpty.getLastName());
    }
}
