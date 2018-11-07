package nju.se4.demo.data.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Description:
 * checklist的原型
 *
 * @author xxz
 * Created on 11/07/2018
 */
@Entity
@Data
public class CheckListItemPrototype {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 文档ID
     */
    private Integer documentID;

    /**
     * 原型ID
     */
    private Integer prototypeID;

    /**
     * checklist内容
     */
    private String content;

    /**
     * 对checklist的解释
     */
    private String explanation;
}
