package com.example.springdemo.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("product")
public class Product implements Serializable {
    @Id
    private int id;
    private String name;
    private int quantity;
    private long price;
}