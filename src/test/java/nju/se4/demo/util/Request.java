package nju.se4.demo.util;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.client.RestClientException;

import java.util.HashMap;
import java.util.Map;
/**
 * @ClassName RegisterLoginTest
 * @PackageName nju.se4.demo.web
 * @Author sheen
 * @Date 2018/10/28
 * @Version 1.0
 * @Description //TODO
 **/
public class Request<T> {
    private Integer port;
    private String path;
    private TestRestTemplate restTemplate;
    private Class<T> responseType;
    private Map<String, String> urlVariables;
    private int fullArgs;
    private Object body;
    private HttpHeaders headers = new HttpHeaders();

    public ResponseEntity<T> sendGET() throws RestClientException {
        if(fullArgs<4){ throw new RuntimeException("not fill");}
        String url=URL(port,path,urlVariables);
        HttpEntity formEntity=new HttpEntity(headers);
        return restTemplate.exchange(url, HttpMethod.GET,formEntity,responseType,urlVariables);
    }

    public ResponseEntity<T> sendPOST() throws RestClientException {
        if(fullArgs<4){ throw new RuntimeException("not fill");}
        String url=URL(port,path,urlVariables);
        HttpEntity<Object> formEntity=new HttpEntity<>(body,headers);
        return restTemplate.exchange(url, HttpMethod.POST,formEntity,responseType,urlVariables);
    }
    private Request(TestRestTemplate restTemplate, Class<T> responseType) {
        this.restTemplate = restTemplate;
        this.responseType = responseType;
        urlVariables=new HashMap<>();
        headers=new HttpHeaders();
        fullArgs=2;
    }


    public static<C> Request<C> builder(TestRestTemplate restTemplate, Class<C> responseType,int port,String path){
        Request<C> request=new Request<>(restTemplate,responseType);
        request.setPath(path);
        request.setPort(port);
        return request;
    }

    public Request<T> setPort(int port) {
        this.port = port;
        fullArgs++;
        return this;
    }

    public Request<T> setBody(Object body) {
        this.body = body;
        return this;
    }

    public Request<T> setPath(String path) {
        this.path = path;
        fullArgs++;
        return this;
    }

    public Request<T> putUrlVariables(String key, String value) {
        urlVariables.put(key, value);
        return this;
    }
    public Request<T> putHeaderVariables(String key, String value) {
        headers.set(key, value);
        return this;
    }
    private static String URL(@NonNull Integer port, @NonNull String path,@NonNull Map<String, ?> urlVariables){
        StringBuilder parameters= new StringBuilder("?");
        for(String paraName:urlVariables.keySet()){
            parameters.append(paraName).append("={").append(paraName).append("}&");
        }
        if(!parameters.toString().equals("?")){
            parameters = new StringBuilder(parameters.substring(0, parameters.length() - 1));
        }
        return "http://localhost:" + port +path+parameters;
    }
}
