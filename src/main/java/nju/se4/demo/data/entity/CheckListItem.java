package nju.se4.demo.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Update:
 * 增加字段：填写此checklist的组和checklist所评价的文档id
 *
 * @author xxz
 * Created on 11/05/2018
 * <p>
 * Update:
 * 更改类名为checkListItem（checklist表项）
 * @author xxz
 * Created on 11/06/2018
 */
@Entity
@Table(name = "check_list")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckListItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * 该项评价的内容,
     */
    private String content;

    /**
     * 用户的评级,Number (1~5)
     */
    private int level;

    /**
     * 用户的备注
     */
    private String comment;

    /**
     * 老师的说明
     */
    private String explanation;

    /**
     * 作出评价的组
     */
    @OneToOne
    private Group commentGroup;

    /**
     * 所评价的文档
     */
    @OneToOne
    private Document document;

}
