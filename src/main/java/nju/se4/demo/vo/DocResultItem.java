package nju.se4.demo.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import nju.se4.demo.data.entity.CheckListItem;

/**
 * Description:
 *
 * @author xxz
 * Created on 11/13/2018
 */
@Data
public class DocResultItem {

    @JsonProperty("level")
    private Integer level;

    @JsonProperty("comment")
    private String comment;

    public DocResultItem(CheckListItem checkListItem) {
        this.level = checkListItem.getLevel();
        this.comment = checkListItem.getComment();
    }

    public DocResultItem() {
    }


}
