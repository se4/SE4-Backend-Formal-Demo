package nju.se4.demo.data.dao;

import nju.se4.demo.data.entity.CheckListItem;
import nju.se4.demo.data.entity.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CheckListItemDAO extends CrudRepository<CheckListItem, Integer> {
    List<CheckListItem> findAllByCommentGroup(Group commentGroup);

    List<CheckListItem> findAllByDocumentID(Integer documentID);

}
