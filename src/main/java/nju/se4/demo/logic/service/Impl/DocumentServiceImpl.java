package nju.se4.demo.logic.service.Impl;

import nju.se4.demo.DocumentVO;
import nju.se4.demo.data.controller.*;
import nju.se4.demo.data.entity.*;
import nju.se4.demo.logic.DocumentService;
import nju.se4.demo.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DocumentServiceImpl implements DocumentService {
    private final DocumentDataController documentDataController;
    private final DivisioinDataController divisioinDataController;
    private final UserDataController userDataController;
    private final GroupController groupController;
    private final ChecklistItemController checklistItemController;

    @Autowired
    public DocumentServiceImpl(DocumentDataController documentDataController, DivisioinDataController divisioinDataController, UserDataController userDataController, GroupController groupController, ChecklistItemController checklistItemController) {
        this.documentDataController = documentDataController;
        this.divisioinDataController = divisioinDataController;
        this.userDataController = userDataController;
        this.groupController = groupController;
        this.checklistItemController = checklistItemController;
    }

    @Override
    //@Transactional(readOnly=true)//todo:只读事务(虽然不是很必要-因为我们似乎没有很强的一致性要求,所以加不加事务好像没影响)/by sheen
    public Response<Document> getDocById(int id) {
        Document documentByID = documentDataController.findByID(id);
        Response<Document> response = new Response<>(documentByID);
        return response;
    }

    /**
     * 获得某个用户的待处理文档列表
     */
    @Override
    //@Transactional(readOnly=true)//todo:只读事务(虽然不是很必要-因为我们似乎没有很强的一致性要求,所以加不加事务好像没影响)/by sheen
    public Response<List<DocumentVO>> getDocByUser(String username) {
        User user = userDataController.findByUsername(username);
        Group userGroup = user.getGroup();
        List<Division> divisions = divisioinDataController.findByGroup(userGroup);
        List<DocumentVO> documents = divisions.stream()
                .filter(division ->
                        user.getAssignments().contains(division.getHomework().getName()))
                .map(Division::getDocument)
                .map(DocumentVO::new)
                .collect(Collectors.toList());
        return new Response<>(documents);
//        documentDataController
    }

    /**
     * @param id 文档ID
     * @return
     */
    @Override
    public Response<List<CheckListItem>> getCheckListById(int id) {
        Document document = documentDataController.findByID(id);
        List<CheckListItem> checkList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CheckListItem checkListItem = new CheckListItem();
            checkListItem.setContent("测试ck表项" + i);
            checkListItem.setDocument(document);
            checkListItem.setExplanation("这里是老师的解释" + i);
            checkList.add(checkListItem);
        }

        return new Response<>(checkList);
    }

    /**
     * 先提取全部文档，乱序排列以后分配给每个组
     * @return
     */
    @Override
    @Transactional
    public Response<Boolean> distributeDocs() {
        List<Document> documentList = documentDataController.listDocument();
        Collections.shuffle(documentList);
        List<Group> groupList = groupController.findAll();
        int groupSize = groupController.getGroupSize();

        for (Document aDocumentList : documentList) {
            for (int j = 0; j < groupSize; j++) {
                int k = 1;
                if(!groupList.get(j).equals(aDocumentList.getOwner())) {
                    Division division = new Division();
                    division.setDocument(aDocumentList);
                    division.setLabor(groupList.get(j));
                    divisioinDataController.add(division);
                    k++;
                }
                if ( k == 3 ) {
                    break;
                }
            }
        }

        return new Response<>(true);
    }

    /**
     * 提交某一文档的checklist
     *
     * @param checkList 文档的checklist
     * @param username     checklist的作者
     * @param documentID  checklist所评价的文档
     */
    @Override
    public boolean addCheckList(List<CheckListItem> checkList, String username, Integer documentID) {
        Group group = groupController.findByMembersContains(userDataController.findByUsername(username));
        Document document = documentDataController.findByID(documentID);
        for (CheckListItem checkListItem : checkList) {
            checkListItem.setCommentGroup(group);
            checkListItem.setDocument(document);
            checklistItemController.add(checkListItem);
        }
        return true;
    }


}