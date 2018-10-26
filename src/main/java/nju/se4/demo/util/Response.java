package nju.se4.demo.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

/**
 * 返回给前端的回应消息
 *
 * @param <T> 有效数据data的类型
 * @author panyu
 */
@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Response<T> {

    private Abilities abilities;

    private T data;

    private Meta meta;

    public Response() {
    }

    public Response(Abilities abilities, T data, Meta meta) {
        this.abilities = abilities;
        this.data = data;
        this.meta = meta;
    }

    public Response(T data, Meta meta) {
        this.data = data;
        this.meta = meta;
    }

    public Response(Abilities abilities, T data) {
        this.abilities = abilities;
        this.data = data;
    }

}