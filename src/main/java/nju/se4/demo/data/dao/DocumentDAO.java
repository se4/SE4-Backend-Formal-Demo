package nju.se4.demo.data.dao;

import nju.se4.demo.data.entity.Document;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * Description:
 *
 * @author xxz
 * Created on 10/27/2018
 */
@SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
public interface DocumentDAO extends JpaSpecificationExecutor<Document>, CrudRepository<Document, Integer> {

    Document findDocumentById(Integer id);

//    Document findAllBy

}
