package nju.se4.demo.vo;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;
import nju.se4.demo.data.entity.Document;

/**
 * Description:
 * DocSimpleSerializer
 *
 * @author xxz
 * Created on 10/29/2018
 */
@Data
public class DocumentVO {
    @JsonUnwrapped
    private Document document;

    public DocumentVO() {

    }

    public DocumentVO(Document document) {
        this.document = document;
    }
}
