package code.yzs.community.model;

import lombok.Data;

/**
 * @author yangzhenshan
 * @date 2019/12/25-14:30
 */
@Data
public class Question {
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
}
