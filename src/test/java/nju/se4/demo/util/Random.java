package nju.se4.demo.util;
/**
 * @ClassName RegisterLoginTest
 * @PackageName nju.se4.demo.web
 * @Author sheen
 * @Date 2018/10/28
 * @Version 1.0
 * @Description //TODO
 **/
public class Random {
    private static final String CHARMODE="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERMODE="0123456789";

    public static String getRandomString(int length){
        StringBuilder ret= new StringBuilder();
        for(int i=0;i<length;++i){
            ret.append(CHARMODE.charAt((int) (Math.random() * CHARMODE.length())));
        }
        return ret.toString();
    }
    public static String getRandomNumber(int length){
        StringBuilder ret= new StringBuilder();
        for(int i=0;i<length;++i){
            ret.append(NUMBERMODE.charAt((int) (Math.random() * NUMBERMODE.length())));
        }
        return ret.toString();
    }

    public static String getRandomNumberAndCharacter(int length){
        StringBuilder ret= new StringBuilder();
        String numberAndCharacter=CHARMODE+NUMBERMODE;
        for(int i=0;i<length;++i){
            ret.append(numberAndCharacter.charAt((int) (Math.random() * numberAndCharacter.length())));
        }
        return ret.toString();
    }
}
