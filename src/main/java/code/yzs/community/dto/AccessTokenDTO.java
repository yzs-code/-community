package code.yzs.community.dto;

import lombok.Data;

/**
 * @author yangzhenshan
 * @date 2019/12/11-16:58
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
