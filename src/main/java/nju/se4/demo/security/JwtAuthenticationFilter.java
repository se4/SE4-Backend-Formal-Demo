package nju.se4.demo.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import nju.se4.demo.security.exception.TokenErrorException;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Update:
 * 关于权限鉴别和token生成
 *
 * @author WYM
 * Created on 04/07/2018
 */

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private final String HEADER_PREFIX = "Bearer ";
    private UserDetailsService service;
    private RestAuthorEntry restAuthorEntry;
    private AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource = new WebAuthenticationDetailsSource();

    public JwtAuthenticationFilter(AuthenticationManager authManager, UserDetailsService service, RestAuthorEntry restAuthorEntry) {
        super(authManager);
        this.service = service;
        this.restAuthorEntry = restAuthorEntry;
    }


    @Override
    protected AuthenticationEntryPoint getAuthenticationEntryPoint() {
        return restAuthorEntry;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader("Authorization");

        if (header == null || !header.startsWith(HEADER_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        try {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            chain.doFilter(req, res);
        } catch (RuntimeException e) {
            getAuthenticationEntryPoint().commence(req, res, new BadCredentialsException("非法的个人信息"));
        }

    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            // parse the token.
            try {
                String user = Jwts.parser()
                        .setSigningKey("ymymym")
                        .parseClaimsJws(token.replace("Bearer ", ""))
                        .getBody()
                        .getSubject();

                if (user != null) {
                    UserDetails details = service.loadUserByUsername(user);
//                    request.setAttribute("user", details);
                    UsernamePasswordAuthenticationToken tmp = new UsernamePasswordAuthenticationToken(details, details.getPassword(), details.getAuthorities());
                    Object o = this.authenticationDetailsSource.buildDetails(request);
                    tmp.setDetails(o);
                    return tmp;
                }
            } catch (ExpiredJwtException e) {
                throw new TokenErrorException("时间超出，请重新登录");
            }
            return null;
        }
        return null;
    }
}