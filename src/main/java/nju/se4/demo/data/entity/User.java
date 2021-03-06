package nju.se4.demo.data.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nju.se4.demo.common.UserIdentity;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * Description:
 * 用户的实体类
 *
 * @author xxz
 * Created on 10/27/2018
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Integer id;

    /**
     * 用户身份的枚举类型
     */
    @JsonProperty("role")
    private UserIdentity userIdentity;

    /**
     * 用户名
     */
    @JsonProperty("username")
    private String username;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 昵称
     */
    @JsonProperty("nickname")
    private String nickName;

    /**
     * 用户自我描述
     */
    @JsonProperty("bio")
    private String bio;

    /**
     * 用户头像url
     */
    @JsonProperty("avatar")
    private String avatar;

    /**
     * 账户创建时间
     */
    @JsonProperty("createdAt")
    private String createTime;

    /**
     * 账户更新时间
     */
    @JsonProperty("updatedAt")
    private String updateTime;

    /**
     * 用户的作业们（作业名）
     */
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<String> assignments;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(referencedColumnName = "group_id")
    private Group group;
}
