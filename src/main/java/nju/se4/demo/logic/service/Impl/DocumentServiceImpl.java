package nju.se4.demo.logic.service.Impl;

import nju.se4.demo.data.controller.DivisioinDataController;
import nju.se4.demo.data.controller.DocumentDataController;
import nju.se4.demo.data.controller.GroupController;
import nju.se4.demo.data.controller.UserDataController;
import nju.se4.demo.data.entity.*;
import nju.se4.demo.logic.ChecklistService;
import nju.se4.demo.logic.DocumentService;
import nju.se4.demo.security.exception.NotFoundException;
import nju.se4.demo.util.Response;
import nju.se4.demo.vo.DocResultItem;
import nju.se4.demo.vo.DocResultVO;
import nju.se4.demo.vo.DocumentVO;
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
    private final ChecklistService checklistService;

    @Autowired
    public DocumentServiceImpl(DocumentDataController documentDataController,
                               DivisioinDataController divisioinDataController,
                               UserDataController userDataController,
                               GroupController groupController,
                               ChecklistService checklistService) {
        this.documentDataController = documentDataController;
        this.divisioinDataController = divisioinDataController;
        this.userDataController = userDataController;
        this.groupController = groupController;
        this.checklistService = checklistService;
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
    public Response<List<DocumentVO>> getDocToProcessByUser(String username) {
        User user = userDataController.findByUsername(username);
        Group userGroup = user.getGroup();
        List<Division> divisions = divisioinDataController.findByGroup(userGroup);
        List<DocumentVO> documents = divisions.stream()
                .filter(division ->
                {
                    //TODO:这里目前先写成始终为true，直到引入"作业"的概念
                    return true;
//                    List<String> assignments = Optional.ofNullable(user.getAssignments()).orElse(new ArrayList<>());
//                    Homework homework = division.getHomework();
//                    if (homework != null) {
//                        return assignments.contains(homework.getName());
//                    }else {
//                        return false;
//                    }
                })
                .map(Division::getDocument)
                .map(DocumentVO::new)
                .collect(Collectors.toList());
        return new Response<>(documents);
//        documentDataController
    }

    /**
     * use checklistService.getCheckList instead
     *
     * @param docID 文档ID
     */
    @Override
    @Deprecated
    public Response<List<CheckListItem>> getCheckListById(int docID, String username) {
        throw new UnsupportedOperationException();
    }

    /**
     * 先提取全部文档，乱序排列以后分配给每个组
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
                if (!groupList.get(j).equals(aDocumentList.getOwner())) {
                    Division division = new Division();
                    division.setDocument(aDocumentList);
                    division.setLabor(groupList.get(j));
                    divisioinDataController.add(division);
                    k++;
                }
                if (k == 3) {
                    break;
                }
            }
        }

        return new Response<>(true);
    }

    /**
     * 提交某一文档的checklist
     *
     * @param checkList  文档的checklist
     * @param username   checklist的作者
     * @param documentID checklist所评价的文档
     */
    @Override
    @Deprecated
    public boolean addCheckList(List<CheckListItem> checkList, String username, Integer documentID) {
        throw new UnsupportedOperationException();

    }

    /**
     * 获取文档互评结果
     *
     * @param docID 文档ID
     */
    @Override
    public List<DocResultVO> getDocResult(Integer docID) {
        if (docID == null) {
            throw new NotFoundException("没有有效的docID");
        }
        List<CheckListItem> checkListItems = checklistService.getDocResult(docID);

        List<CheckListItemPrototype> prototypes = checklistService.getChecklistPrototypeByDocID(docID);


        List<DocResultVO> ret = new ArrayList<>(prototypes.size());
        for (CheckListItemPrototype prototype : prototypes) {
            List<DocResultItem> relatedItems = checkListItems.stream()
                    .filter(checkListItem -> checkListItem.getTypeID() == prototype.getPrototypeID())
                    .map(DocResultItem::new)
                    .collect(Collectors.toList());
            DocResultVO result = new DocResultVO(prototype, relatedItems);
            ret.add(result);
        }
        return ret;


    }

    /**
     * 获得自己的文档
     *
     * @param username
     */
    @Override
    public List<Document> getDocByAuthor(String username) {
        User user = userDataController.findByUsername(username);
        if (user == null) {
            throw new NotFoundException("未找到用户");
        }
        Group group = user.getGroup();
        if (group == null) {
            throw new NotFoundException("未找到用户分组信息");
        }
        return documentDataController.findByAuthor(group);
    }


}