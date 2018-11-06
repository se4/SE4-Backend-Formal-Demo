package nju.se4.demo.web;

import com.alibaba.fastjson.JSON;
import nju.se4.demo.Mock;
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
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
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
    @PostConstruct
    @Transactional
    void init() {
        try {
            Mock.initDB();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
        assert (statusCode.is2xxSuccessful());
        System.out.println(responseEntity.toString());
        System.out.println("apiVersion:"+apiVersion);

        Map<String,Object> parse = (Map<String,Object>)JSON.parse(responseEntity.getBody().toString());
        final List<Map> DocSimpleSerializers = (List)(parse.get("data"));
        docId=(int)(DocSimpleSerializers.get(0).get("id"));
        assert docId!=0;
    }
//    获得文档详情
//    get /:docId
//            Response
//    {
//        data:DocSerializer
//    }
    @Test
    public void test02_GetOneDoc() {
        String path=apiVersion+docUrl+"/"+docId;
        Class<String> responseType=String.class;
        ResponseEntity responseEntity = Request.builder(restTemplate, responseType,port,path)
                .putHeaderVariables(Authorization,TOKEN)
                .sendGET();
        HttpStatus statusCode = responseEntity.getStatusCode();
        System.out.println(Thread.currentThread() .getStackTrace()[1].getMethodName()+"():"+ statusCode);
        assert (statusCode.is2xxSuccessful());
        System.out.println("res.body-"+responseEntity.getBody().getClass().getName()+":"+responseEntity.getBody());
        Map<String,Object> body = (Map<String,Object>)JSON.parse(responseEntity.getBody().toString());
        Map<String,Object> parse=(Map)(body.get("data"));
        assert parse.get("id")!=null;
        assert parse.get("filename")!=null;
        assert parse.get("content")!=null;
        assert parse.get("owner")!=null;

        System.out.println("apiVersion:"+apiVersion);
    }
    private static List<Map> CHECKLISTS;
    /*
        获得某一文档的CheckList
        get /:docId/checklist
        Response{   data:CheckList  }
        CheckList
        [{
        "content":"该项评价的内容, String",
        "level":"用户的评级,Number (1~5)",
        "comment":"用户的备注, String",
        //未来会添加
        "explain":"老师的说明,String"
        },...]
     */
    @Test
    public void test03_GetOneDocCheckList() {
        String path=apiVersion+docUrl+"/"+docId+"/checklist";
        Class<String> responseType=String.class;
        ResponseEntity responseEntity = Request.builder(restTemplate, responseType,port,path)
                .putHeaderVariables(Authorization,TOKEN)
                .sendGET();
        HttpStatus statusCode = responseEntity.getStatusCode();
        System.out.println(Thread.currentThread() .getStackTrace()[1].getMethodName()+"():"+ statusCode);
        assert (statusCode.is2xxSuccessful());
        System.out.println("res.body-"+responseEntity.getBody().getClass().getName()+":"+responseEntity.getBody());
        Map<String,Object> body = ((Map<String,Object>)JSON.parse(responseEntity.getBody().toString()));
        List<Map> parse=(List)(body.get("data"));
        assert parse.size()>0;
        for(Map<String,Object> checkList:parse){
            assert checkList.get("content")!=null;
            assert checkList.get("level")!=null;
            assert checkList.get("comment")!=null;
            assert checkList.get("explain")!=null;
        }

        CHECKLISTS=parse;
        System.out.println("apiVersion:"+apiVersion);
    }
    /*
        提交某一文档的CheckList
        post /:docId/checklist
        POST Body{data:CheckList}//checkList见上
     */
    @Test
    public void test04_PostOneDocCheckList() {
        assert CHECKLISTS!=null;
        for(Map<String,Object> checkList:CHECKLISTS){
            checkList.put("level",((int)(100 * Math.random())) % 2);
            checkList.put("comment","commentCommentComment");
        }
        Map<String,Object> body=new HashMap<>();
        body.put("data",CHECKLISTS);
        String path=apiVersion+docUrl+"/"+docId+"/checklist";
        Class<String> responseType=String.class;
        ResponseEntity responseEntity = Request.builder(restTemplate, responseType,port,path)
                .putHeaderVariables(Authorization,TOKEN)
                .setBody(body)
                .sendPOST();
        HttpStatus statusCode = responseEntity.getStatusCode();
        System.out.println(Thread.currentThread() .getStackTrace()[1].getMethodName()+"():"+ statusCode);
        assert (statusCode.is2xxSuccessful());
    }
}
