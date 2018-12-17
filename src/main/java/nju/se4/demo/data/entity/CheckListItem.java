package nju.se4.demo.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

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
 *
 * Update:
 * 增加typeID
 * @author xxz
 * Created on 11/07/2018
 *
 * Update:
 * 将document改为documentid
 * @author xxz
 * Created on 11/07/2018
 */
@Entity
@Table(name = "check_list")
@Data
@AllArgsConstructor
public class CheckListItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * checklist 的编号，如某一文档一共有10个checklistItem，每一组拿到的都应该是checklistItem 0-9
     */
    @JsonProperty("fid")
    private int typeID;

    /**
     * 该项评价的内容,
     */
    @Column(columnDefinition = "MEDIUMTEXT")
    private String content;

    /**
     * 用户的评级,Number (1~5)
     */
    private int level;

    /**
     * 用户的备注
     */
    @Column(columnDefinition = "MEDIUMTEXT")
    private String comment;

    /**
     * 老师的说明
     */
    @Column(columnDefinition = "MEDIUMTEXT")
    private String explanation;

    /**
     * 作出评价的组
     */
    @OneToOne
    private Group commentGroup;

    /**
     * 所评价的文档id
     */
    private Integer documentID;

    public CheckListItem(CheckListItemPrototype prototype) {
        this.typeID = prototype.getPrototypeID();
        this.content = prototype.getContent();
        this.explanation = prototype.getExplanation();
    }

    public CheckListItem() {
    }
}
