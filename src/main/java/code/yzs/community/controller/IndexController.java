package code.yzs.community.controller;

import code.yzs.community.dto.QuestionDTO;
import code.yzs.community.mapper.QuestionMapper;
import code.yzs.community.mapper.UserMapper;
import code.yzs.community.model.Question;
import code.yzs.community.model.User;
import code.yzs.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * @author yangzhenshan
 * @date 2019/12/10-14:08
 */
@Controller
public class IndexController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/")
    public String Hello(HttpServletRequest request, Model model
            , @RequestParam(name = "page", defaultValue = "1") Integer page
            , @RequestParam(name="size",defaultValue = "5") Integer size) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        List<QuestionDTO> questionDTOList = questionService.list(page, size);
        model.addAttribute("questionDTOList", questionDTOList);

        return "index";
    }
}


