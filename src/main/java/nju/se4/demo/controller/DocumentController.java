package nju.se4.demo.controller;

import nju.se4.demo.DocumentVO;
import nju.se4.demo.data.entity.Document;
import nju.se4.demo.logic.DocumentService;
import nju.se4.demo.util.Response;
import nju.se4.demo.util.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author py
 */
@Controller
@RequestMapping("doc")
public class DocumentController {
    private final DocumentService documentService;


    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    /**
     * 按id查找文档
     * @param docId 需要获得详情的文档id
     */
    @RequestMapping(value = "/{docId}", method = RequestMethod.GET)
    @ResponseBody
    public Response<Document> getDocumentById(@PathVariable Integer docId) {
        return documentService.getDocById(docId);
    }

    /**
     * 获取待处理文档列表
     */
    @RequestMapping("/")
    public Response<List<DocumentVO>> getDocumentList() {
        String userName = SecurityUtility.getUserName(SecurityContextHolder.getContext());
        return documentService.getDocByUser(userName);
    }

}
