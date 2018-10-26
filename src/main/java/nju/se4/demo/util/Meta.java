package nju.se4.demo.util;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

/**
 * Description:
 * 返回给前端的额外数据（目前没什么用）
 *
 * @author panyu
 * Created on 10/26/2018
 */
@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Meta {
    private boolean liked;

    private boolean followed;

    public Meta() {
    }

    public Meta(boolean liked) {
        this.liked = liked;
    }

    public Meta(boolean liked, boolean followed) {
        this.liked = liked;
        this.followed = followed;
    }


}
