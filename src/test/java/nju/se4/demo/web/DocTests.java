package nju.se4.demo.web;

import com.alibaba.fastjson.JSON;
import nju.se4.demo.util.Request;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DocTests
 * @PackageName nju.se4.demo.web
 * @Author sheen
 * @Date 2018/11/5
 * @Version 1.0
 * @Description //TODO
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DocTests {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Value("${server.servlet.context-path}")
    private String apiVersion;
    @Value("${api_url.loginUrl}")
    private String loginUrl;
    @Value("${api_url.docUrl}")
    private String docUrl;
    @Value("${constV.tokenHeader}")
    private String Authorization;
    private static String USERNAME_PASSWORD="wuXinYu";
    private static String TOKEN;
    @Test
    public void test01_loginToGetToken() {
        String path=apiVersion+loginUrl;
        final String token = Request.getToken(USERNAME_PASSWORD, USERNAME_PASSWORD, path, restTemplate, port);
        assert token!=null;
        TOKEN=token;
    }
    private static Integer docId;
    @Test
    public void test02_GetDocs() {
        String path=apiVersion+docUrl;
        Class<String> responseType=String.class;
        ResponseEntity responseEntity = Request.builder(restTemplate, responseType,port,path)
                .putHeaderVariables(Authorization,TOKEN)
                .sendGET();
        HttpStatus statusCode = responseEntity.getStatusCode();
        System.out.println(Thread.currentThread() .getStackTrace()[1].getMethodName()+"():"+ statusCode);
        System.out.println(responseEntity.toString());
        assert (statusCode.is2xxSuccessful());
        System.out.println("apiVersion:"+apiVersion);

        Map<String,Object> parse = (Map<String,Object>)JSON.parse(responseEntity.getBody().toString());
        final List<Map> DocSimpleSerializers = (List)(parse.get("data"));
        docId=(int)(DocSimpleSerializers.get(0).get("id"));
        assert docId!=0;
    }
    @Test
    public void test02_GetOneDoc() {
        String path=apiVersion+docUrl+"/"+docId;
        Class<String> responseType=String.class;
        ResponseEntity responseEntity = Request.builder(restTemplate, responseType,port,path)
                .putHeaderVariables(Authorization,TOKEN)
                .sendGET();
        HttpStatus statusCode = responseEntity.getStatusCode();
        System.out.println(Thread.currentThread() .getStackTrace()[1].getMethodName()+"():"+ statusCode);

        Map<String,Object> parse = (Map<String,Object>)JSON.parse(responseEntity.getBody().toString());
        assert (statusCode.is2xxSuccessful());
        assert parse.get("id")!=null;
        assert parse.get("filename")!=null;
        assert parse.get("content")!=null;
        assert parse.get("owner")!=null;

        System.out.println("apiVersion:"+apiVersion);
    }
}
