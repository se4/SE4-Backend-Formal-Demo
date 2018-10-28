package nju.se4.demo.service.Impl;

import nju.se4.demo.data.controller.DocumentDataController;
import nju.se4.demo.data.entity.Document;
import nju.se4.demo.service.DocumentService;
import nju.se4.demo.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentServiceImpl implements DocumentService{
    @Autowired
    private DocumentDataController documentDataController;

    @Override
    public Response<Document> getDocById(int id) {
        return null;
    }
}
