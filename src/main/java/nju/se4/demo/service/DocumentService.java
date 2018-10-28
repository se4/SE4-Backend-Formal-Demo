package nju.se4.demo.service;

import nju.se4.demo.data.entity.Document;
import nju.se4.demo.util.Response;

public interface DocumentService {
    public Response<Document> getDocById(int id);
}
