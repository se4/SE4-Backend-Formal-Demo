package nju.se4.demo;

import nju.se4.demo.common.GroupType;
import nju.se4.demo.common.UserIdentity;
import nju.se4.demo.data.dao.*;
import nju.se4.demo.data.entity.*;
import nju.se4.demo.logic.DocumentService;
import nju.se4.demo.security.SecurityUser;
import nju.se4.demo.security.others.SecurityUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Mock
 * @PackageName nju.se4.demo
 * @Author sheen
 * @Date 2018/11/4
 * @Version 1.0
 * @Description 测试数据:有一堆学生，已经组成了小组，（至少要有四个小组，每组至少两个人）；有一次作业，然后每个小组提交了一份文档
 **/
@Component
public class Mock {
    private static UserDAO userDAO;
    private static GroupDAO groupDAO;
    private static DocumentDAO documentDAO;
    private static DivisionDAO divisionDAO;
    private static HomeworkDAO homeworkDAO;
    private static SecurityUserDAO securityUserDAO;
    private static DocumentService documentService;
    @Autowired
    public Mock(UserDAO userDAO, GroupDAO groupDAO, DocumentDAO documentDAO, DivisionDAO divisionDAO,
                HomeworkDAO homeworkDAO, SecurityUserDAO securityUserDAO,DocumentService documentService) {
        Mock.userDAO = userDAO;
        Mock.groupDAO = groupDAO;
        Mock.documentDAO = documentDAO;
        Mock.divisionDAO = divisionDAO;
        Mock.homeworkDAO = homeworkDAO;
        Mock.securityUserDAO=securityUserDAO;
        Mock.documentService=documentService;
    }

//    @Value("${constV.encryption}")
//    public void setEncryption(String encryption) {
//        Mock.ENCY = encryption;
//    }

    private static String ENCY;
    private static String hwName="第二周作业测试";
    private static Homework testHomeWork=new Homework(null,hwName,new ArrayList<>());

    private static Group wuGroup=GB("weAreWu").build();
    private static Document wuDoc=new Document(null,"wu小组用例文档.md","",wuGroup);
    private static User wuXinYu=UB("wuXinYu").assignments(combineList(hwName)).build();
    private static User wuJiuYu=UB("wuJiuYu").assignments(combineList(hwName)).build();

    private static Group youGroup=GB("weAreYou").build();
    private static Document youDoc=new Document(null,"you小组用例文档.md","",youGroup);
    private static User youXinYu=UB("youXinYu").assignments(combineList(hwName)).build();
    private static User youJiuYu=UB("youJiuYu").assignments(combineList(hwName)).build();

    private static Group baiGroup=GB("weAreBai").build();
    private static Document baiDoc=new Document(null,"bai小组用例文档.md","",baiGroup);
    private static User xiaoBai=UB("xiaoBai").assignments(combineList(hwName)).build();
    private static User daBai=UB("daBai").assignments(combineList(hwName)).build();

    private static Group kunduinGroup=GB("weAreKunduin").build();
    private static Document kunduinDoc=new Document(null,"kunduin小组用例文档.md","",kunduinGroup);
    private static User kunduin=UB("kunduin").assignments(combineList(hwName)).build();
    private static User kunduin2=UB("kunduin2").assignments(combineList(hwName)).build();
    private static User kunduin3=UB("kunduin3").assignments(combineList(hwName)).build();
    private static User kunduin4=UB("kunduin4").assignments(combineList(hwName)).build();
    public static void initDB() throws IOException {
//        System.out.println(ENCY);
        if(homeworkDAO.findHomeworkByName(hwName)!=null){
            System.out.println(hwName+"相关测试数据已经加载");
            return;
        }
        homeworkDAO.save(testHomeWork);
        groupDAO.save(wuGroup);
        groupDAO.save(youGroup);
        groupDAO.save(baiGroup);
        groupDAO.save(kunduinGroup);
//      ----------------addGroupAndSaveUser--------------------
        addGroup(wuGroup,wuXinYu,wuJiuYu);
        addGroup(youGroup,youXinYu,youJiuYu);
        addGroup(baiGroup,xiaoBai,daBai);
        addGroup(kunduinGroup,kunduin,kunduin2,kunduin3,kunduin4);
//      ------------------------------------
        Resource resource = new ClassPathResource("case.md");
        File file = resource.getFile();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String doc=ReaderToString(br);
        wuDoc.setContent(doc);      documentDAO.save(wuDoc);
        youDoc.setContent(doc);     documentDAO.save(youDoc);
        baiDoc.setContent(doc);     documentDAO.save(baiDoc);
        kunduinDoc.setContent(doc); documentDAO.save(kunduinDoc);
//      ------------------------------------
        documentService.distributeDocs();
    }

    private static void addGroup(Group group,User... users){
        for(User u:users){
            group.getMembers().add(u);
            u.setGroup(group);
            userDAO.save(u);
            securityUserDAO.save(new SecurityUser(u));
        }

        groupDAO.save(group);

    }
    private static void initData(){

    }
    private static User.UserBuilder UB(String username){
        return User.builder().userIdentity(UserIdentity.STUDENT).bio("我是小白名叫"+username+"的一个分身")
                .username(username).password("{noop}"+username).nickName(username).createTime("2018-11-04").updateTime("2018-11-04")
                .avatar("http://qlogo1.store.qq.com/qzone/648985752/648985752/100?1525621350");
    }
    private static Group.GroupBuilder GB(String name){
        return Group.builder().name(name).shareLink(name).type(GroupType.GROUP).members(new ArrayList<>());
    }
    private static List<String> combineList(String content){
        ArrayList<String> ret=new ArrayList<>();
        ret.add(content);
        return ret;
    }
    private static String ReaderToString(BufferedReader reader)throws IOException{
        //对一串字符进行操作
        StringBuffer fileData = new StringBuffer();
        char[] buf = new char[1024];
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
        }
        //缓冲区使用完必须关掉
        reader.close();
        return fileData.toString();
    }
}
