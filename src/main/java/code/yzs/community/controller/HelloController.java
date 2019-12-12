package code.yzs.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author yangzhenshan
 * @date 2019/12/10-14:08
 */
@Controller
public class HelloController {
    @GetMapping("/")
    public String Hello(){

        return "index";
    }




}


