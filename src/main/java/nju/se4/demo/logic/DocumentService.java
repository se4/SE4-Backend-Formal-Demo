package nju.se4.demo.logic;

import nju.se4.demo.DocumentVO;
import nju.se4.demo.data.entity.Document;
import nju.se4.demo.util.Response;

import java.util.List;

public interface DocumentService {
    Response<Document> getDocById(int id);

    Response<List<DocumentVO>> getDocByUser(String username);


}
