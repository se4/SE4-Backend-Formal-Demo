package nju.se4.demo.controller;

import nju.se4.demo.DocumentVO;
import nju.se4.demo.data.entity.CheckListItem;
import nju.se4.demo.data.entity.Document;
import nju.se4.demo.logic.ChecklistService;
import nju.se4.demo.logic.DocumentService;
import nju.se4.demo.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author py
 */
@RestController
@RequestMapping("doc")
public class DocumentController {
    private final DocumentService documentService;
    private final ChecklistService checklistService;

    @Autowired
    public DocumentController(DocumentService documentService, ChecklistService checklistService) {
        this.documentService = documentService;
        this.checklistService = checklistService;
    }

    /**
     * 按id查找文档
     *
     * @param docId 需要获得详情的文档id
     */
    @RequestMapping(value = "/{docId}", method = RequestMethod.GET)
    @ResponseBody
    public Response<Document> getDocumentById(@PathVariable Integer docId) {
        return documentService.getDocById(docId);
    }

    /**
     * 获取待处理文档列表
     * todo:还有我有一个问题,显然Response应该是用来做通讯的VO封装,直接从service返回上来好吗?,这样controller如果要调用两个service然后组装岂不是还要拆包/by sheen
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Response<List<DocumentVO>> getDocumentList(@AuthenticationPrincipal String username) {
        return documentService.getDocByUser(username);
    }

    /**
     * 获取对应文档id的checkList
     */
    @RequestMapping(value = "/{docId}/checklist", method = RequestMethod.GET)
    @ResponseBody
    public Response<List<CheckListItem>> getCheckListById(@AuthenticationPrincipal String username, @PathVariable Integer docId) {
        List<CheckListItem> checkList = checklistService.getChecklistByDocumentIDAndUsername(docId, username);
        return new Response<>(checkList);
    }


    /**
     * 提交某一文档的checklist
     */
    @RequestMapping(value = "/{docId}/checklist", method = RequestMethod.POST)
    public void submitChecklist(@AuthenticationPrincipal String username,
                                @PathVariable Integer docId,
                                @RequestBody Response<List<CheckListItem>> checklist) {
        checklistService.addCheckList(checklist.getData(), username, docId);
    }
}
