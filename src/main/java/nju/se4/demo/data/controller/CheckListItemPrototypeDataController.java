package nju.se4.demo.data.controller;

import nju.se4.demo.data.dao.CheckListItemPrototypeDAO;
import nju.se4.demo.data.entity.CheckListItemPrototype;
import nju.se4.demo.data.filter.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description:
 *
 * @author xxz
 * Created on 11/07/2018
 */
@Component
public class CheckListItemPrototypeDataController implements DataController<CheckListItemPrototype, Filter> {
    private final CheckListItemPrototypeDAO prototypeDAO;

    @Autowired
    public CheckListItemPrototypeDataController(CheckListItemPrototypeDAO prototypeDAO) {
        this.prototypeDAO = prototypeDAO;
    }

    /**
     * 添加新实体
     *
     * @param element 需要添加的实例
     * @return 添加的实例
     */
    @Override
    public CheckListItemPrototype add(CheckListItemPrototype element) {
        return prototypeDAO.save(element);
    }

    /**
     * 删除某一实体
     *
     * @param id 实体id
     * @return 被删除的实体
     */
    @Override
    public CheckListItemPrototype delete(Integer id) {
        throw new UnsupportedOperationException();

    }

    /**
     * 更新某一实体
     *
     * @param element 需要更新的字段
     * @return 更新后的实体
     */
    @Override
    public CheckListItemPrototype update(CheckListItemPrototype element) {
        prototypeDAO.deleteById(element.getId());
        return prototypeDAO.save(element);
    }

    public List<CheckListItemPrototype> findByDocId(Integer documentID) {
        return prototypeDAO.findAllByDocumentID(documentID);
    }

    /**
     * 多条件查询
     *
     * @param filter 查询条件
     * @return 符合条件的结果
     */
    @Override
    public List<CheckListItemPrototype> find(Filter filter) {
        throw new UnsupportedOperationException();
    }
}
