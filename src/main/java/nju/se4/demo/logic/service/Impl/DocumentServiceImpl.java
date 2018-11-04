package nju.se4.demo.logic.service.Impl;

import nju.se4.demo.DocumentVO;
import nju.se4.demo.data.controller.DivisioinDataController;
import nju.se4.demo.data.controller.DocumentDataController;
import nju.se4.demo.data.controller.UserDataController;
import nju.se4.demo.data.entity.Division;
import nju.se4.demo.data.entity.Document;
import nju.se4.demo.data.entity.Group;
import nju.se4.demo.data.entity.User;
import nju.se4.demo.logic.DocumentService;
import nju.se4.demo.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DocumentServiceImpl implements DocumentService {
    private final DocumentDataController documentDataController;
    private final DivisioinDataController divisioinDataController;
    private final UserDataController userDataController;

    @Autowired
    public DocumentServiceImpl(DocumentDataController documentDataController, DivisioinDataController divisioinDataController, UserDataController userDataController) {
        this.documentDataController = documentDataController;
        this.divisioinDataController = divisioinDataController;
        this.userDataController = userDataController;
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


}