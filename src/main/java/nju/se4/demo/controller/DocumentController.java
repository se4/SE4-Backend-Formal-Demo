package nju.se4.demo.controller;

import nju.se4.demo.data.entity.Document;
import nju.se4.demo.service.DocumentService;
import nju.se4.demo.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("doc")
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    /**
     * 请xz在documentService加个筛选，下次我学会自己辽来QAQ
     * @param id
     * @return
     */
    @RequestMapping(value = "/{docId}", method = RequestMethod.GET)
    @ResponseBody
    public Response<Document> getDocumentById(@PathVariable int id) {
        return documentService.getDocById(id);
    }

}
