package nju.se4.demo.data.controller;

import nju.se4.demo.data.dao.DivisionDAO;
import nju.se4.demo.data.entity.Division;
import nju.se4.demo.data.entity.Group;
import nju.se4.demo.data.filter.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description:
 *
 * @author xxz
 * Created on 10/30/2018
 */
@Component
public class DivisioinDataController implements DataController<Division, Filter> {
    private final DivisionDAO divisionDAO;

    @Autowired
    public DivisioinDataController(DivisionDAO divisionDAO) {
        this.divisionDAO = divisionDAO;
    }


    /**
     * 添加新实体
     *
     * @param element 需要添加的实例
     * @return 添加的实例
     */
    //todo:add之前应该主动将id置为null再插入(防止用来做update-虽然一般都是现场组装entity插入的,id都是null,但是add和update的代码总要有点差别)
    @Override
    public Division add(Division element) {
        return divisionDAO.save(element);
    }

    /**
     * 删除某一实体
     *
     * @param id 实体id
     * @return 被删除的实体
     */
    @Override
    public Division delete(Integer id) {
        throw new UnsupportedOperationException();
    }

    /**
     * 更新某一实体
     *
     * @param element 需要更新的字段
     * @return 更新后的实体
     */
    @Override
    public Division update(Division element) {
        throw new UnsupportedOperationException();
    }

    /**
     * 多条件查询
     *
     * @param filter 查询条件
     * @return 符合条件的结果
     */
    @Override
    public List<Division> find(Filter filter) {
        throw new UnsupportedOperationException();
    }

    /**
     * 按小组查找分工
     */
    public List<Division> findByGroup(Group group) {
        return divisionDAO.findAllByLabor(group);
    }
}
