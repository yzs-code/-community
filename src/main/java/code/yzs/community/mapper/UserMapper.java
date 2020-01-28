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
    @Insert("INSERT INTO USER(account_Id,name,token,gmt_Create,gmt_Modified,avatar_url) VALUES " +
            "(#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void  insert(User user);

    @Select("select * from USER where token=#{token}")
    User findByToken(@Param("token") String token);

    //根据id查找
    @Select("select * from USER where account_Id=#{account_Id} order by gmt_Create desc limit 1 ")
    User findById(@Param("account_Id") String account_Id);


}
