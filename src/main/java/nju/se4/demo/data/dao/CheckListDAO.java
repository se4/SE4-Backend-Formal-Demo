package nju.se4.demo.data.dao;

import nju.se4.demo.data.entity.CheckList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface CheckListDAO extends CrudRepository<CheckList, Integer>{

}
