package code.yzs.community.controller;

import code.yzs.community.dto.AccessTokenDTO;
import code.yzs.community.dto.GIthubUser;
import code.yzs.community.mapper.UserMapper;
import code.yzs.community.model.User;
import code.yzs.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author yangzhenshan
 * @date 2019/12/11-16:02
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private  String clientid;
    @Value("${github.client.secret}")
    private  String clientsecret;
    @Value("${github.redirect.url}")
    private  String redirectUrl;
    @Autowired
    private UserMapper userMapper;


    @RequestMapping("/callback")
    public String  callback(@RequestParam(name="code") String  code,
                            @RequestParam(name="state") String  state, Model model,
                            HttpServletResponse response){
        AccessTokenDTO accessTokenDTO= new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientid);
        accessTokenDTO.setClient_secret(clientsecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirectUrl);
        //获取accessToken
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        //返回GIthubUser对象
        GIthubUser gIthubUser = githubProvider.getuser(accessToken);
        //将信息传递给首页
        model.addAttribute("user",gIthubUser);
        if(gIthubUser!=null){
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(gIthubUser.getName());
            user.setGmtCreat(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreat());
            user.setAccountId(gIthubUser.getId());
            userMapper.insert(user);
             //登录成功
            Cookie cookie = new Cookie("token",token);
            response.addCookie(cookie);
            return  "redirect:/";
        }else {
            //登录失败
            return  "redirect:/";
        }
    }
}
