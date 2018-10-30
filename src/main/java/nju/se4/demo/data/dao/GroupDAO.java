package nju.se4.demo.data.dao;

import nju.se4.demo.data.entity.Group;
import nju.se4.demo.data.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description:
 *
 * @author xxz
 * Created on 10/30/2018
 */
@Component
public interface GroupDAO extends CrudRepository<Group, Integer> {
    Group findGroupById(Integer id);

    Group findGroupByMembersContains(List<User> members);
}
