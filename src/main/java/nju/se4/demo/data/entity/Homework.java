package nju.se4.demo.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Description:
 * 一次作业
 *
 * @author xxz
 * Created on 10/30/2018
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Homework {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hw_id")
    private Integer id;

    /**
     * 作业名
     */
    private String name;
    /**
     * 分工
     */
    @OneToMany
    private List<Division> divisions;

}
