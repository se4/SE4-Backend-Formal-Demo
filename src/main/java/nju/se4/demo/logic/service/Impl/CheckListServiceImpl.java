package nju.se4.demo.logic.service.Impl;

import nju.se4.demo.data.controller.CheckListItemPrototypeDataController;
import nju.se4.demo.data.controller.ChecklistItemController;
import nju.se4.demo.data.controller.UserDataController;
import nju.se4.demo.data.entity.CheckListItem;
import nju.se4.demo.data.entity.CheckListItemPrototype;
import nju.se4.demo.data.entity.Group;
import nju.se4.demo.data.entity.User;
import nju.se4.demo.logic.ChecklistService;
import nju.se4.demo.security.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * @author xxz
 * Created on 11/07/2018
 */
@Component
public class CheckListServiceImpl implements ChecklistService {
    private final CheckListItemPrototypeDataController prototypeDataController;
    private final ChecklistItemController checklistItemController;
    private final UserDataController userDataController;

    @Autowired
    public CheckListServiceImpl(CheckListItemPrototypeDataController prototypeDataController, ChecklistItemController checklistItemController, UserDataController userDataController) {
        this.prototypeDataController = prototypeDataController;
        this.checklistItemController = checklistItemController;
        this.userDataController = userDataController;
    }

    /**
     * 按文档ID和用户名获取对应的checklist
     *
     * @param documentID 文档ID
     * @param username   用户名
     */
    @Override
    public List<CheckListItem> getChecklistByDocumentIDAndUsername(Integer documentID, String username) {
        //TODO 未来功能：老师能够为不同的文档设置不同的checklist
//        prototypeDataController.findByDocId(documentID);
        List<CheckListItemPrototype> checkListItemPrototypes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CheckListItemPrototype checkListItemPrototype = new CheckListItemPrototype();
            checkListItemPrototype.setContent("测试ck表项" + i);
            checkListItemPrototype.setPrototypeID(i);
            checkListItemPrototype.setExplanation("这里是老师的解释" + i);
            checkListItemPrototypes.add(checkListItemPrototype);
        }
        List<CheckListItem> checkListItems = checkListItemPrototypes.stream().map(CheckListItem::new).collect(Collectors.toList());
        //查找本小组已经写过的checklist，将写过的部分更新 <typeID,checklistItem>
        Map<Integer, CheckListItem> typeMap = new HashMap<>();
        for (CheckListItem checkListItem : checkListItems) {
            typeMap.put(checkListItem.getTypeID(), checkListItem);
        }
        User user = userDataController.findByUsername(username);
        if (user == null) {
            throw new NotFoundException("提交checklist的用户没有找到");
        }
        Group group = user.getGroup();

        if (group == null) {
            throw new NotFoundException("提交checklist的用户没有分组信息");
        }
        List<CheckListItem> writtenChecklist = checklistItemController.findByGroup(group);
        for (CheckListItem writtenItem : writtenChecklist) {
            if (Objects.equals(writtenItem.getDocumentID(), documentID)) {
                typeMap.remove(writtenItem.getTypeID());
                typeMap.put(writtenItem.getTypeID(), writtenItem);
            }
        }
        return new ArrayList<>(typeMap.values());
    }

    /**
     * 获取某一文档所有有效的checklist
     *
     * @param docID 文档ID
     */
    @Override
    public List<CheckListItemPrototype> getChecklistPrototypeByDocID(Integer docID) {
        //TODO 未来功能：老师能够为不同的文档设置不同的checklist
//        prototypeDataController.findByDocId(documentID);
        List<CheckListItemPrototype> checkListItemPrototypes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CheckListItemPrototype checkListItemPrototype = new CheckListItemPrototype();
            checkListItemPrototype.setContent("测试ck表项" + i);
            checkListItemPrototype.setPrototypeID(i);
            checkListItemPrototype.setExplanation("这里是老师的解释" + i);
            checkListItemPrototypes.add(checkListItemPrototype);
        }
        return checkListItemPrototypes;
    }


    /**
     * 提交某一文档的checklist
     *
     * @param checkList  文档的checklist
     * @param username   checklist的作者
     * @param documentID checklist所评价的文档
     */
    @Override
    public boolean addCheckList(List<CheckListItem> checkList, String username, Integer documentID) {
        User user = userDataController.findByUsername(username);
        Group group = user.getGroup();
        List<CheckListItem> oldCheckList = getChecklistByDocumentIDAndUsername(documentID, username);
        HashMap<Integer, CheckListItem> idCheckListMap = new HashMap<>();
        for (CheckListItem checkListItem : oldCheckList) {
            idCheckListMap.put(checkListItem.getTypeID(), checkListItem);
        }

        for (CheckListItem checkListItem : checkList) {
            CheckListItem remove = idCheckListMap.remove(checkListItem.getTypeID());
            checkListItem.setId(remove.getId());
            checkListItem.setCommentGroup(group);
            checkListItem.setDocumentID(documentID);
            idCheckListMap.put(checkListItem.getTypeID(), checkListItem);
        }
        checklistItemController.addAll(new ArrayList<>(idCheckListMap.values()));
        return true;
    }

    /**
     * 获取某一文档被评价的所有checklistItem
     *
     * @param docID
     * @return
     */
    @Override
    public List<CheckListItem> getDocResult(Integer docID) {
        return checklistItemController.findByDocID(docID);
    }

}
