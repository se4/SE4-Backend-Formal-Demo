package nju.se4.demo.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import nju.se4.demo.common.UserIdentity;
import nju.se4.demo.data.entity.User;
import nju.se4.demo.util.Random;
import nju.se4.demo.util.Request;
import org.junit.BeforeClass;
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

import java.io.IOException;
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


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegisterLoginTest {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    private static User STUDENT_USER;
    private static HashMap<String,String> POST_MAP;
    private static String USERNAME;
    private static String PASSWORD;
    private static String NICKNAME;
//    private UserIdentity userIdentity;
    private static String TOKEN;
    @Value("${server.servlet.context-path}")
    private String apiVersion;
    @Value("${api_url.loginUrl}")
    private String loginUrl;

    @Value("${api_url.registerUrl}")
    private String registerUrl;

    @BeforeClass
    public static void setUp() throws Exception {
        USERNAME= Random.getRandomString(10);
        PASSWORD=Random.getRandomNumberAndCharacter(8);
        NICKNAME= Random.getRandomString(6);
    }
    @Test
    public void test01_userRegister() {
        String path=apiVersion+registerUrl;
        POST_MAP=new HashMap<>();POST_MAP.put("username",USERNAME);POST_MAP.put("password",PASSWORD);POST_MAP.put("nickname",NICKNAME);
        Class<String> responseType=String.class;
        ResponseEntity responseEntity = Request.builder(restTemplate, responseType,port,path)
                .setBody(POST_MAP)
                .sendPOST();
        HttpStatus statusCode = responseEntity.getStatusCode();
        System.out.println(Thread.currentThread() .getStackTrace()[1].getMethodName()+"():"+ statusCode);
        System.out.println(responseEntity.toString());
        assert (statusCode.is2xxSuccessful());
        System.out.println("apiVersion:"+apiVersion);
    }
    @Test
    public void test02_login() {
        String path=apiVersion+loginUrl;
        POST_MAP=new HashMap<>();POST_MAP.put("username",USERNAME);POST_MAP.put("password",PASSWORD);
        Class<String> responseType=String.class;
        ResponseEntity responseEntity = Request.builder(restTemplate, responseType,port,path)
                .setBody(POST_MAP)
                .sendPOST();
        HttpStatus statusCode = responseEntity.getStatusCode();
        System.out.println(Thread.currentThread() .getStackTrace()[1].getMethodName()+"():"+ statusCode);

        assert (statusCode.is2xxSuccessful());
        TOKEN=responseEntity.getHeaders().get("Authorization").get(0);
        System.out.println(TOKEN);
    }

}
