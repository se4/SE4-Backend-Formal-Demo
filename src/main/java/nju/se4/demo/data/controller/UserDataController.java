package nju.se4.demo.data.controller;

import com.sun.javafx.geom.transform.Identity;
import nju.se4.demo.common.UserIdentity;
import nju.se4.demo.data.dao.UserDAO;
import nju.se4.demo.data.entity.User;
import nju.se4.demo.data.filter.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description:
 * 用户
 *
 * @author xxz
 * Created on 10/30/2018
 */
@Component
public class UserDataController implements DataController<User, Filter> {
    private final UserDAO userDAO;

    @Autowired
    public UserDataController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * 添加新实体
     *
     * @param element 需要添加的实例
     * @return 添加的实例
     */
    @Override
    public User add(User element) {
        return userDAO.save(element);
    }

    /**
     * 删除某一实体
     *
     * @param id 实体id
     * @return 被删除的实体
     */
    @Override
    public User delete(Integer id) {
        User toDelete = userDAO.findUserById(id);
        userDAO.delete(toDelete);
        return toDelete;
    }

    /**
     * 更新某一实体
     *
     * @param element 需要更新的字段
     * @return 更新后的实体
     */
    @Override
    public User update(User element) {
        return userDAO.save(element);
    }

    /**
     * 多条件查询
     *
     * @param filter 查询条件
     * @return 符合条件的结果
     */
    @Override
    public List<User> find(Filter filter) {
        throw new UnsupportedOperationException();
    }

    public User findByUsername(String username) {
        return userDAO.findUserByUsername(username);
    }

    public List<User> listStudents() {
        return userDAO.findByUserIdentity(UserIdentity.STUDENT);
    }

}
