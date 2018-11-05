package nju.se4.demo.data.dao;

import nju.se4.demo.common.UserIdentity;
import nju.se4.demo.data.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description:
 *
 * @author xxz
 * Created on 10/27/2018
 */
@Component
@SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
public interface UserDAO extends CrudRepository<User, Integer>, JpaSpecificationExecutor<User> {

    User findUserByUsername(String username);

    User findUserById(Integer id);


    List<User> findByUserIdentity(UserIdentity userIdentity);
}
