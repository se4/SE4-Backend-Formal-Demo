package nju.se4.demo.data.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * Description:
 * 文档的实体类
 *
 * @author xxz
 * Created on 10/26/2018
 */
@Entity
@Table(name = "document")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Integer id;

    /**
     * 文件名
     */
    @JsonProperty("filename")
    private String filename;

    /**
     * 文件内容
     */
    @JsonProperty("content")
    private String content;

    /**
     * 文档作者
     */
    @JsonProperty("owner")
    @OneToOne(fetch = FetchType.EAGER)
    private Group owner;

}
