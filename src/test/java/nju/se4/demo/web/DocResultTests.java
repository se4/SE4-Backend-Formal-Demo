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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DocResultTests
 * @PackageName nju.se4.demo.web
 * @Author sheen
 * @Date 2018/11/11
 * @Version 1.0
 * @Description 第三周api测试
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DocResultTests {
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
    private static Integer DOCID;
    @PostConstruct
    @Transactional
    void init() {
        try {
            //初始化文档
            Mock.initDB();
            // todo 增加checklist
            assert false;
            // todo 合并
            assert false;
            //得到测试token
            TOKEN=getSelfToken();
            assert TOKEN!=null;

            assert DOCID!=0 &&DOCID!=null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String getSelfToken(){
        String path=apiVersion+loginUrl;
        final String token = Request.getToken(USERNAME_PASSWORD, USERNAME_PASSWORD, path, restTemplate, port);
        return token;
    }
    private Integer getSelfDocId(){
        String path=apiVersion+docUrl;
        Class<String> responseType=String.class;
        ResponseEntity responseEntity = Request.builder(restTemplate, responseType,port,path)
                .putHeaderVariables(Authorization,TOKEN)
                .sendGET();
        assert (responseEntity.getStatusCode().is2xxSuccessful());
        Map<String,Object> parse = (Map<String,Object>) JSON.parse(responseEntity.getBody().toString());
        final List<Map> DocSimpleSerializers = (List)(parse.get("data"));
        int docId=(int)(DocSimpleSerializers.get(0).get("id"));

        return docId;
    }
    /*
        用户获得自己的已被评价的文档列表
        get /self
        Response {data:Array<DocSimpleSerializer>}
    */
    @Test
    public void test01_GetCheckedDoc() {
        Map<String,Object> body=new HashMap<>();
        String path=apiVersion+docUrl+"/self";
        Class<String> responseType=String.class;
        ResponseEntity responseEntity = Request.builder(restTemplate, responseType,port,path)
                .putHeaderVariables(Authorization,TOKEN)
                .sendGET();
        HttpStatus statusCode = responseEntity.getStatusCode();
        System.out.println(Thread.currentThread() .getStackTrace()[1].getMethodName()+"():"+ statusCode);
        assert (responseEntity.getStatusCode().is2xxSuccessful());
        Map<String,Object> parse = (Map<String,Object>) JSON.parse(responseEntity.getBody().toString());
        final List<Map> DocSimpleSerializers = (List)(parse.get("data"));

        assert DocSimpleSerializers!=null&&DocSimpleSerializers.size()!=0;//有返回
        final Map Doc = DocSimpleSerializers.get(0);
        assert parse.get("id")!=null;
        assert parse.get("filename")!=null;
        assert parse.get("owner")!=null;


        //得到测试docId
        DOCID=(int)(DocSimpleSerializers.get(0).get("id"));
    }
    /*
        查看某一文档结果
        get /:docId/result
        Response {data:Result}

        Result（文档的评价结果）
        [{
            "content":String,
            "result": [
                        {
                            "level":Number,
                            "comment":String
                        },...
                      ]
          },...
        ]
    */
    @Test
    public void test02_GetDocResult() {
        assert DOCID!=null;
        Map<String,Object> body=new HashMap<>();
        String path=apiVersion+docUrl+"/"+DOCID+"/result";
        Class<String> responseType=String.class;
        ResponseEntity responseEntity = Request.builder(restTemplate, responseType,port,path)
                .putHeaderVariables(Authorization,TOKEN)
                .sendGET();
        HttpStatus statusCode = responseEntity.getStatusCode();
        System.out.println(Thread.currentThread() .getStackTrace()[1].getMethodName()+"():"+ statusCode);
        assert (statusCode.is2xxSuccessful());
        System.out.println(responseEntity.toString());
        System.out.println("apiVersion:"+apiVersion);

        Map<String,Object> parse = (Map<String,Object>) JSON.parse(responseEntity.getBody().toString());
        final List<Map> BodyResults = (List)(parse.get("data"));
        assert BodyResults!=null&&BodyResults.size()!=0;//有返回
        Map<String,Object> oneBodyResult = BodyResults.get(0);//取第一个
        assert oneBodyResult.get("content")!=null;
        assert oneBodyResult.get("result")!=null;
        final List<Map> results = (List<Map>)oneBodyResult.get("result");//沙雕小白,搞那么多嵌套
        assert results!=null&&results.size()!=0;//"result"字段有内容
        Map<String,Object> oneResult = results.get(0);//取第一个
        assert oneResult.get("level")!=null;
        assert oneResult.get("comment")!=null;

    }


}

