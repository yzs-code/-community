package code.yzs.community.mapper;

import code.yzs.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author yangzhenshan
 * @date 2019/12/13-16:24
 */
@Mapper
public interface UserMapper {
    @Insert("INSERT INTO USER(account_Id,name,token,gmt_Creat,gmt_Modified) VALUES " +
            "(#{accountId},#{name},#{token},#{gmtCreat},#{gmtModified})")
    void  insert(User user);

    @Select("select * from user where token=#{token}")
    User findByToken(@Param("token") String token);
}
