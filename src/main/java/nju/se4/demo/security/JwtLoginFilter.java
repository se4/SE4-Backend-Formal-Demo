package nju.se4.demo.security;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import nju.se4.demo.data.dao.UserDAO;
import nju.se4.demo.data.entity.User;
import nju.se4.demo.security.exception.NotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

/**
 * 用户登陆
 *
 * @author xuxiangzhe
 */
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final String NOOP = "{noop}";
    private AuthenticationManager authenticationManager;
    private UserDAO userDAO;

    public JwtLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            SecurityUser securityUser = new ObjectMapper()
                    .readValue(req.getInputStream(), SecurityUser.class);
            //这里的security user是从前端的包生成的，所以密码里不含noop，因此要在这里手动加上
            //不！！！Spring security在解析密码的时候会自动去掉noop，所以登录不用加noop
            securityUser.setPassword(securityUser.getPassword());
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            securityUser.getUsername(),
                            securityUser.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) {
        String username = ((UserDetails) auth.getPrincipal()).getUsername();
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
                .signWith(SignatureAlgorithm.HS512, "ymymym")
                .compact();
        assert username != null;
        assert userDAO != null;

        User userByUsername
                = userDAO
                .findUserByUsername
                        (username);


        User user;
        if (userByUsername != null) {
            user = userByUsername;
        } else {
            throw new NotFoundException(username + " not found");
        }
        res.setContentType("application/json; charset=utf-8");
        res.addHeader("Authorization", "Bearer " + token);
        res.addHeader("Roles", "STUDENT");

        try {
            PrintWriter writer = res.getWriter();
            writer.print(JSONObject.toJSONString(new Data(user), SerializerFeature.WriteMapNullValue,
                    SerializerFeature.WriteDateUseDateFormat));
            writer.close();
            res.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

@lombok.Data
class Data {
    Object data;

    Data(Object data) {
        this.data = data;
    }
}