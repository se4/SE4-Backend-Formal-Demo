package nju.se4.demo.data.controller;

import nju.se4.demo.data.dao.DocumentDAO;
import nju.se4.demo.data.entity.Document;
import nju.se4.demo.data.entity.Group;
import nju.se4.demo.data.filter.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description:
 *
 * @author xxz
 * Created on 10/27/2018
 */
@Component
public class DocumentDataController implements DataController<Document, Filter> {
    private final DocumentDAO documentDAO;

    @Autowired
    public DocumentDataController(DocumentDAO documentDAO) {
        this.documentDAO = documentDAO;
    }

    /**
     * 添加新实体
     *
     * @param element 需要添加的实例
     * @return 添加的实例
     */
    @Override
    public Document add(Document element) {
        return documentDAO.save(element);
    }

    /**
     * 删除某一实体
     *
     * @param id 实体id
     * @return 被删除的实体
     */
    @Override
    public Document delete(Integer id) {
        Document toDelete = documentDAO.findDocumentById(id);
        documentDAO.deleteById(id);
        return toDelete;
    }

    /**
     * 更新某一实体
     *
     * @param element 需要更新的字段
     * @return 更新后的实体
     */
    @Override
    public Document update(Document element) {
        return documentDAO.save(element);
    }

    /**
     * 多条件查询
     *
     * @param filter 查询条件
     * @return 符合条件的结果
     */
    @Override
    public List<Document> find(Filter filter) {
        throw new UnsupportedOperationException();

    }

    public Document findByID(Integer id) {
        return documentDAO.findDocumentById(id);
    }

    public List<Document> findByAuthor(Group author) {
        return documentDAO.findAllByOwner(author);
    }

    public List<Document> listDocument() {
        return (List<Document>) documentDAO.findAll();
    }


}
