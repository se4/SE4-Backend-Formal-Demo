package nju.se4.demo.data.dao;

import nju.se4.demo.data.entity.CheckListItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface CheckListItemDAO extends CrudRepository<CheckListItem, Integer> {

}
