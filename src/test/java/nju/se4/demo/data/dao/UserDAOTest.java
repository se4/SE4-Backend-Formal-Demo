package nju.se4.demo.data.dao;

import nju.se4.demo.common.UserIdentity;
import nju.se4.demo.data.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserDAOTest {

    @Autowired
    private UserDAO userDAO;
    @Test
    public void findByIdentity() {
        User user = new User();
        user.setUserIdentity(UserIdentity.STUDENT);
        user.setUsername("xz");
        user.setPassword("admin1");
        userDAO.save(user);
        List<User> list = new ArrayList<>();
        list = userDAO.findByIdentity(UserIdentity.STUDENT);
        System.out.println(list.size());
    }
}