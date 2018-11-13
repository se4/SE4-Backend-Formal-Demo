package nju.se4.demo.logic;

import nju.se4.demo.data.entity.CheckListItem;
import nju.se4.demo.data.entity.CheckListItemPrototype;

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

    /**
     * 获取某一文档所有有效的checklist
     *
     * @param docID 文档ID
     */
    List<CheckListItemPrototype> getChecklistPrototypeByDocID(Integer docID);

    boolean addCheckList(List<CheckListItem> checkList, String username, Integer documentID);

    /**
     * 获取某一文档被评价的所有checklistItem
     *
     * @param docID
     * @return
     */
    List<CheckListItem> getDocResult(Integer docID);
}
