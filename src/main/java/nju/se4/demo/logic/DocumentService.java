package nju.se4.demo.logic;

import nju.se4.demo.data.entity.CheckListItem;
import nju.se4.demo.data.entity.Document;
import nju.se4.demo.util.Response;
import nju.se4.demo.vo.DocResultVO;
import nju.se4.demo.vo.DocumentVO;

import java.util.List;

public interface DocumentService {
    Response<Document> getDocById(int id);

    Response<List<DocumentVO>> getDocToProcessByUser(String username);


    Response<List<CheckListItem>> getCheckListById(int id, String username);

    /**
     * 分发文档
     */
    Response<Boolean> distributeDocs();


    /**
     * 提交某一文档的checklist
     */
    boolean addCheckList(List<CheckListItem> checkList, String username, Integer documentID);


    /**
     * 获取文档互评结果
     *
     * @param docID 文档ID
     */
    List<DocResultVO> getDocResult(Integer docID);

    /**
     * 获得自己的文档
     */
    List<Document> getDocByAuthor(String username);
}
