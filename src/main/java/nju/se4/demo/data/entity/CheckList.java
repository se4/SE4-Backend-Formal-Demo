package nju.se4.demo.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "check_list")
@Data
@AllArgsConstructor
public class CheckList {

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

}
