package nju.se4.demo.logic;

import nju.se4.demo.data.entity.CheckListItem;

import java.util.List;

/**
 * Description:
 *
 * @author xxz
 * Created on 11/07/2018
 */
public interface ChecklistService {
    /**
     * 按文档ID和用户名获取对应的checklist
     */
    List<CheckListItem> getChecklistByDocumentIDAndUsername(Integer documentID, String username);

    boolean addCheckList(List<CheckListItem> checkList, String username, Integer documentID);
}
