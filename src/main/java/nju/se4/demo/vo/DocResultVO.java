package nju.se4.demo.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import nju.se4.demo.data.entity.CheckListItemPrototype;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * @author xxz
 * Created on 11/13/2018
 */
@Data
public class DocResultVO {

    @JsonProperty("wtfID")
    private Integer prototypeID;

    @JsonProperty("content")
    private String content;

    @JsonProperty("pass")
    private Boolean pass;

    @JsonProperty("result")
    private List<DocResultItem> resultItems;

    public DocResultVO() {
    }

    public DocResultVO(CheckListItemPrototype prototype, List<DocResultItem> resultItems) {
        final int FAIL = 0;
        this.content = prototype.getContent();
        this.resultItems = resultItems;
        List<DocResultItem> unpassed = resultItems.stream()
                .filter(docResultItem -> docResultItem.getLevel() == FAIL)
                .collect(Collectors.toList());
        this.pass = !resultItems.isEmpty() && unpassed.isEmpty();
        this.prototypeID = prototype.getPrototypeID();

    }
}
