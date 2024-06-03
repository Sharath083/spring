package com.example.springdemo.controller;

import com.example.springdemo.dto.Product;
import com.example.springdemo.redisConfig.RedisHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    RedisHelper redisHelper;
    @PostMapping("/add")
    public Product saveProduct(@RequestBody Product product) {
        return redisHelper.save("product table",product.getId(),product);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") String id) {
        return redisHelper.get("product table",id,Product.class);
    }
}
