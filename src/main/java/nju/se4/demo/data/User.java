package nju.se4.demo.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Description:
 * 用户的实体类
 *
 * @author xxz
 * Created on 10/27/2018
 */
@Data
@Entity
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


}
