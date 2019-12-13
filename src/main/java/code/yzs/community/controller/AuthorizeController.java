package code.yzs.community.controller;

import code.yzs.community.dto.AccessTokenDTO;
import code.yzs.community.dto.GIthubUser;
import code.yzs.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

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




    @RequestMapping("/callback")
    public String  callback(@RequestParam(name="code") String  code, HttpServletRequest request,
                            @RequestParam(name="state") String  state, Model model){
        AccessTokenDTO accessTokenDTO= new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientid);
        accessTokenDTO.setClient_secret(clientsecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirectUrl);
        //获取token
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        //返回GIthubUser对象
        GIthubUser user = githubProvider.getuser(accessToken);
        //将信息传递给首页
        model.addAttribute("user",user);
        if(user!=null){
             //登录成功
            request.getSession().setAttribute("user",user);
            return  "redirect:/";
        }else {
            //登录失败

            return  "redirect:/";
        }
    }


}
