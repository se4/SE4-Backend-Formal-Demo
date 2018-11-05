package nju.se4.demo.data.controller;

import nju.se4.demo.data.dao.CheckListItemDAO;
import nju.se4.demo.data.entity.CheckListItem;
import nju.se4.demo.data.filter.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description:
 *
 * @author xxz
 * Created on 11/06/2018
 */
@Component
public class ChecklistItemController implements DataController<CheckListItem, Filter> {
    private final CheckListItemDAO checkListItemDAO;

    @Autowired
    public ChecklistItemController(CheckListItemDAO checkListItemDAO) {
        this.checkListItemDAO = checkListItemDAO;
    }

    /**
     * 添加新实体
     *
     * @param element 需要添加的实例
     * @return 添加的实例
     */
    @Override
    public CheckListItem add(CheckListItem element) {
        return checkListItemDAO.save(element);
    }

    /**
     * 删除某一实体
     *
     * @param id 实体id
     * @return 被删除的实体
     */
    @Override
    public CheckListItem delete(Integer id) {
        throw new UnsupportedOperationException();

    }

    /**
     * 更新某一实体
     *
     * @param element 需要更新的字段
     * @return 更新后的实体
     */
    @Override
    public CheckListItem update(CheckListItem element) {
        return checkListItemDAO.save(element);
    }

    /**
     * 多条件查询
     *
     * @param filter 查询条件
     * @return 符合条件的结果
     */
    @Override
    public List<CheckListItem> find(Filter filter) {
        throw new UnsupportedOperationException();
    }
}
