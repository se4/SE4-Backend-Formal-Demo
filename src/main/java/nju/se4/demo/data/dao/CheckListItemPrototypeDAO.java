package nju.se4.demo.data.dao;

import nju.se4.demo.data.entity.CheckListItemPrototype;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description:
 *
 * @author xxz
 * Created on 11/07/2018
 */
@Component
public interface CheckListItemPrototypeDAO extends CrudRepository<CheckListItemPrototype, Integer> {
    List<CheckListItemPrototype> findAllByDocumentID(Integer documentID);
}
