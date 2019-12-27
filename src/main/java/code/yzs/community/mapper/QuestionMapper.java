package code.yzs.community.mapper;

import code.yzs.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author yangzhenshan
 * @date 2019/12/25-14:20
 */
@Mapper
public interface QuestionMapper {
    //插入问题
    @Insert("INSERT INTO QUESTION(title,description,gmt_Create,gmt_Modified,comment_count,view_count,like_count,tag,creator) VALUES " +
            "(#{title},#{description},#{gmtCreate},#{gmtModified},#{commentCount},#{viewCount},#{likeCount},#{tag},#{creator})")
    public void create(Question question);
    //查询问题
    @Select("select * from question")
    public List<Question> List();
}
