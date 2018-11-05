package nju.se4.demo.controller;

import nju.se4.demo.DocumentVO;
import nju.se4.demo.data.entity.CheckListItem;
import nju.se4.demo.data.entity.Document;
import nju.se4.demo.logic.DocumentService;
import nju.se4.demo.util.Response;
import nju.se4.demo.util.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author py
 */
@RestController
@RequestMapping("doc")
public class DocumentController {
    private final DocumentService documentService;


    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
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
     * todo:其实获得用户名可以这样写'public Response<List<DocumentVO>> getDocumentList(@AuthenticationPrincipal String username)'/by sheen
     * todo:还有我有一个问题,显然Response应该是用来做通讯的VO封装,直接从service返回上来好吗?,这样controller如果要调用两个service然后组装岂不是还要拆包/by sheen
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Response<List<DocumentVO>> getDocumentList() {
        String userName = SecurityUtility.getUserName(SecurityContextHolder.getContext());
        return documentService.getDocByUser(userName);
    }

    /**
     * 获取对应文档id的checkList
     */
    @RequestMapping(value = "/{docId}/checklist", method = RequestMethod.GET)
    @ResponseBody
    public Response<List<CheckListItem>> getCheckListById(@PathVariable Integer docId) {
        return documentService.getCheckListById(docId);
    }


    /**
     * 提交某一文档的checklist
     */
    @RequestMapping(value = "/{docId}/checklist", method = RequestMethod.POST)
    public void submitChecklist(@AuthenticationPrincipal String username,
                                @PathVariable Integer docId,
                                @RequestBody Response<List<CheckListItem>> checklist) {
        documentService.addCheckList(checklist.getData(), username, docId);
    }
}
