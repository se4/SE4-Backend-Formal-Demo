package nju.se4.demo.data.dao;

import nju.se4.demo.data.entity.Document;
import nju.se4.demo.data.entity.Group;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Description:
 *
 * @author xxz
 * Created on 10/27/2018
 */
@SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
public interface DocumentDAO extends JpaSpecificationExecutor<Document>, CrudRepository<Document, Integer> {

    Document findDocumentById(Integer id);

    List<Document> findAllByOwner(Group group);
//    Document findAllBy

}
