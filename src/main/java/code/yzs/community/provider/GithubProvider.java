package code.yzs.community.provider;


import code.yzs.community.dto.AccessTokenDTO;
import code.yzs.community.dto.GIthubUser;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author yangzhenshan
 * @date 2019/12/11-16:40
 * @deprecated 获取github登录
 */
@Component
@Slf4j
public class GithubProvider {
   public  String  getAccessToken(AccessTokenDTO accessTokenDTO){
       MediaType mediaType = MediaType.get("application/json; charset=utf-8");
       OkHttpClient client = new OkHttpClient();
       RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
       Request request = new Request.Builder()
                   .url("https://github.com/login/oauth/access_token")
                   .post(body)
                   .build();
       try (Response response = client.newCall(request).execute()) {
           String string = response.body().string();
           String token = string.split("&")[0].split("=")[1];
           System.out.println(token);
           return token;
       }catch (Exception e){
           e.printStackTrace();
       }
       return  null;
   }
   public GIthubUser getuser(String accessToken){
       OkHttpClient client = new OkHttpClient();
           Request request = new Request.Builder()
                   .url("https://api.github.com/user?access_token="+accessToken)
                   .build();

       try {
           Response response = client.newCall(request).execute();
           String string = response.body().string();
           GIthubUser gIthubUser = JSON.parseObject(string, GIthubUser.class);
           return  gIthubUser;
       } catch (IOException e) {
           e.printStackTrace();
       }

        return  null;
   }


}
