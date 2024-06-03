package com.example.springdemo.redisConfig;

import com.example.springdemo.contants.Constants;
import com.example.springdemo.dto.Product;
import com.example.springdemo.exception.CommonException;
import com.example.springdemo.helper.Crypto;
import com.example.springdemo.jwt.JwtRequest;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RedisHelper {
    Gson gson =new Gson();
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private Crypto crypto;

    public <T> T save(String key, int id, T value) {
        redisTemplate.<String, String>opsForHash()
                .put(key, String.valueOf(id).trim(), gson.toJson(value).trim());
        redisTemplate.expire(key,60, TimeUnit.MINUTES);
        return value;
    }
    public Product save(String key, Product value){

        redisTemplate.opsForHash().put(key.trim(),value.getId(), value);
        return value;
    }
    public <T> T get(String key, String id, Class<T> clazz) {
        String json = (String) redisTemplate.opsForHash().get(key, id);
        return json != null ? gson.fromJson(json, clazz) : null;
    }
    public String delete(String key,String id){
        redisTemplate.opsForHash().delete(key,id);
        return "Product Deleted Successfully";
    }
    public void set(String key,String value){
        redisTemplate.opsForValue().set(key,value,60,TimeUnit.MINUTES);
    }
    public String get(String key){
        return (String) redisTemplate.opsForValue().get(key);
    }
//    public JwtRequest validateSession(String session){
//        var data=crypto.decrypt(session).split("//");
//        var key= Constants.REDIS_KEY+data[0];
//        var value=get(key);
//        if(value==null){
//            throw new CommonException("001","Session Expired");
//        }else if(!value.equals(session)){
//            throw new CommonException("001","Invalid session key");
//        }
//        return new JwtRequest(data[0],data[1]);
//    }
}
