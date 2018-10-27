package nju.se4.demo.security.exception;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author xushengtao
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class RestErrorInformation {
    @JsonProperty(value = "error")
    private String cause;

    public RestErrorInformation(String cause) {
        this.cause = cause;
    }
}