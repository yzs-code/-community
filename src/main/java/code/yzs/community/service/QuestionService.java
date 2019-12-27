package code.yzs.community.service;

import code.yzs.community.dto.QuestionDTO;
import code.yzs.community.mapper.QuestionMapper;
import code.yzs.community.mapper.UserMapper;
import code.yzs.community.model.Question;
import code.yzs.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author yangzhenshan
 * @date 2019/12/26-21:55
 */
@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public List<QuestionDTO> list(){
        List<QuestionDTO> list=new ArrayList<QuestionDTO>();
        List<Question> questions=questionMapper.List();
        for (Question question : questions) {
            QuestionDTO questionDTO=new QuestionDTO();
            User user =userMapper.findById(question.getCreator().toString());
            questionDTO.setUser(user);
            BeanUtils.copyProperties(question,questionDTO);//copyquestion 到questionDTO里去
            list.add(questionDTO);
        }

        return list;
    }



}
