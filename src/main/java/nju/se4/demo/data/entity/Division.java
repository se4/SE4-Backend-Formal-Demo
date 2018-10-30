package nju.se4.demo.data.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Description:
 * 一次分工
 *
 * @author xxz
 * Created on 10/30/2018
 */
@Data
@Entity
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "group_id")
    private Group labor;

    @ManyToOne
    @JoinColumn(referencedColumnName = "doc_id")
    private Document document;

    @ManyToOne
    @JoinColumn(referencedColumnName = "hw_id")
    private Homework homework;


}
