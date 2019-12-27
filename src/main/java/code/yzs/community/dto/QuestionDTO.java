package code.yzs.community.dto;

import code.yzs.community.model.User;
import lombok.Data;

/**
 * @author yangzhenshan
 * @date 2019/12/26-22:12
 */
@Data
public class QuestionDTO {
    private Integer id;
    private String  title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Long creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
}
