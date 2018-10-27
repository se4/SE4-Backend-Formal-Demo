package nju.se4.demo.data.controller;

import java.util.List;

/**
 * Description:
 * 数据层controller的接口
 *
 * @author xxz
 * Created on 10/27/2018
 */
public interface DataController<T, U> {
    /**
     * 添加新实体
     *
     * @param element 需要添加的实例
     * @return 添加的实例
     */
    T add(T element);

    /**
     * 删除某一实体
     *
     * @param id 实体id
     * @return 被删除的实体
     */
    T delete(Integer id);

    /**
     * 更新某一实体
     *
     * @param element 需要更新的字段
     * @return 更新后的实体
     */
    T update(T element);

    /**
     * 多条件查询
     *
     * @param filter 查询条件
     * @return 符合条件的结果
     */
    List<T> find(U filter);
}
