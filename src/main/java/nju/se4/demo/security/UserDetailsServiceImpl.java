package nju.se4.demo.security;

import lombok.Data;
import nju.se4.demo.common.UserIdentity;
import nju.se4.demo.data.dao.UserDAO;
import nju.se4.demo.data.entity.User;
import nju.se4.demo.security.exception.InvalidOperationException;
import nju.se4.demo.security.exception.NotFoundException;
import nju.se4.demo.security.others.SecurityUserDAO;
import nju.se4.demo.util.Abilities;
import nju.se4.demo.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * security user
 *
 * @author xushengtao
 */
@Service
@RestController
public class UserDetailsServiceImpl implements UserDetailsService {
    private final SecurityProperties securityProperties;
    private final String NOOP = "{noop}";

    private final SecurityUserDAO securityUserDAO;

    private final UserDAO userDAO;

    @Autowired
    public UserDetailsServiceImpl(SecurityProperties securityProperties, UserDAO userDAO, SecurityUserDAO securityUserDAO) {
        this.securityProperties = securityProperties;
        this.userDAO = userDAO;
        this.securityUserDAO = securityUserDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        SecurityUser securityUser = securityUserDAO.findByUsername(username);

        if (securityUser == null) {
            throw new NotFoundException(username);
        }
        return securityUser;
    }

    /**
     * 注册的时候会在密码前加noop
     */
    @RequestMapping(value = "/authorization/register", method = RequestMethod.POST)
    public Response<UserDTO> signUp(@RequestBody UserDTO userDTO) {
        userDTO.setPassword(NOOP + userDTO.getPassword());

        SecurityUser oriUser = securityUserDAO.findByUsername(userDTO.getUsername());
        if (oriUser != null) {
            throw new InvalidOperationException("此用户已被注册");
        }


        User newUser = new User();
        newUser.setNickName(userDTO.getNickname());
        newUser.setPassword(userDTO.getPassword());
        newUser.setUsername(userDTO.getUsername());
        newUser.setUserIdentity(UserIdentity.STUDENT);
        userDAO.save(newUser);

        SecurityUser securityUser = new SecurityUser(newUser);
        securityUserDAO.save(securityUser);

        Abilities abilities = new Abilities();
        abilities.setUpdate(true);
        return new Response<>(abilities, userDTO);

    }


}

@Data
class UserDTO {
    @Pattern(regexp = "[0-9a-zA-Z_]{4,16}", message = "用户名只能包含大小写字母、数字和下划线，且必须为4-16位！", groups = PostMapping.class)
    @NotNull(message = "请填写用户名！", groups = PostMapping.class)
    String username;
    @NotNull(message = "请填写密码！", groups = PostMapping.class)
    String password;
    String nickname;
}