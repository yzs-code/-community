package code.yzs.community.controller;

import code.yzs.community.dto.AccessTokenDTO;
import code.yzs.community.dto.GIthubUser;
import code.yzs.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yangzhenshan
 * @date 2019/12/11-16:02
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;


    @RequestMapping("/callback")
    public String  callback(@RequestParam(name="code") String  code, @RequestParam(name="state") String  state ){
        AccessTokenDTO accessTokenDTO= new AccessTokenDTO();
        accessTokenDTO.setClient_id("bc09130e5c7da6b7fa08");
        accessTokenDTO.setClient_secret("c7316d90b777377f07f4941ebd27a21d739ba8c6");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri("http://localhost:8087/callback");
        //获取token
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        //返回GIthubUser对象
        GIthubUser user = githubProvider.getuser(accessToken);
        //将信息传递给首页

        return "index";
    }


}
