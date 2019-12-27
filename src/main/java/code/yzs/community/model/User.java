package code.yzs.community.model;

import lombok.Data;

/**
 * @author yangzhenshan
 * @date 2019/12/13-16:27
 */
@Data
public class User {
    private  Integer id;
    private  String accountId;
    private  String  name;
    private  String token;
    private  Long gmtCreate;
    private  Long gmtModified;
    private  String  avatarUrl;
}
