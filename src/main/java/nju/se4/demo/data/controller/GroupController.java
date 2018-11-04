package nju.se4.demo.data.controller;

import nju.se4.demo.data.dao.GroupDAO;
import nju.se4.demo.data.entity.Group;
import nju.se4.demo.data.entity.User;
import nju.se4.demo.data.filter.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * Description:
 *
 * @author xxz
 * Created on 10/30/2018
 */
@Component
public class GroupController implements DataController<Group, Filter> {

    private final GroupDAO groupDAO;

    @Autowired
    public GroupController(GroupDAO groupDAO) {
        this.groupDAO = groupDAO;
    }

    /**
     * 添加新实体
     *
     * @param element 需要添加的实例
     * @return 添加的实例
     */
    @Override
    public Group add(Group element) {
        return groupDAO.save(element);
    }

    /**
     * 删除某一实体
     *
     * @param id 实体id
     * @return 被删除的实体
     */
    @Override
    public Group delete(Integer id) {
        throw new UnsupportedOperationException();
    }

    /**
     * 更新某一实体
     *
     * @param element 需要更新的字段
     * @return 更新后的实体
     */
    @Override
    public Group update(Group element) {
        return groupDAO.save(element);
    }

    /**
     * 多条件查询
     *
     * @param filter 查询条件
     * @return 符合条件的结果
     */
    @Override
    public List<Group> find(Filter filter) {
        throw new UnsupportedOperationException();
    }

    public Group findByMembersContains(User user) {
        List<User> users = Collections.singletonList(user);
        return groupDAO.findGroupByMembersContains(users);
    }

    public int getGroupSize() {
        return groupDAO.findAll().size();
    }

    public List<Group> findAll() {
        return groupDAO.findAll();
    }
}
