package nju.se4.demo.controller;

import nju.se4.demo.data.entity.CheckListItem;
import nju.se4.demo.data.entity.Document;
import nju.se4.demo.logic.ChecklistService;
import nju.se4.demo.logic.DocumentService;
import nju.se4.demo.security.SecurityUser;
import nju.se4.demo.util.Response;
import nju.se4.demo.vo.DocResultVO;
import nju.se4.demo.vo.DocumentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

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
     * 获取文档列表
     * @param self true:自己的文档 false:待处理文档
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Response<List<DocumentVO>> getDocumentList(@AuthenticationPrincipal SecurityUser user, @PathParam(value = "self") Boolean self) {
        String username = user.getUsername();
        if (self) {
            List<Document> docs = documentService.getDocByAuthor(username);
            List<DocumentVO> documentVOS = docs.stream().map(DocumentVO::new).collect(Collectors.toList());
            return new Response<>(documentVOS);
        } else {
            return documentService.getDocToProcessByUser(username);
        }
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
    public Response<String> submitChecklist(@AuthenticationPrincipal String username,
                                            @PathVariable Integer docId,
                                            @RequestBody Response<List<CheckListItem>> checklist) {
        checklistService.addCheckList(checklist.getData(), username, docId);
        return new Response<>("垃圾小白！！！！");
    }

    @RequestMapping(value = "/{docId}/result", method = RequestMethod.GET)
    public Response<List<DocResultVO>> getDocResult(@PathVariable Integer docId) {
        return new Response<>(documentService.getDocResult(docId));
    }
}
