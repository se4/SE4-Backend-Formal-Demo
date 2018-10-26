package nju.se4.demo.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Description:
 * 小组的实体类
 *
 * @author xxz
 * Created on 10/26/2018
 */
@Entity
@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Table(name = "se_group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Column(name = "group_id")
    private Integer id;

    /**
     * 小组类型
     */
    @JsonProperty("type")
    private GroupType type;

    /**
     * 小组的加组链接
     */
    @JsonProperty("shareLink")
    private String shareLink;

    /**
     * 组名
     */
    @JsonProperty("name")
    private String name;

    /**
     * 小组成员
     */
    @JsonProperty("owners")
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private List<User> members;


}
