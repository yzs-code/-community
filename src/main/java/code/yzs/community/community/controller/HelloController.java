package code.yzs.community.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

/**
 * @author yangzhenshan
 * @date 2019/12/10-14:08
 */
@Controller
public class HelloController {
    @GetMapping("/hello")
    public String Hello(@RequestParam(name="name")String  name,Model model){
        model.addAttribute("name",name);
        return "hello";
    }




}


