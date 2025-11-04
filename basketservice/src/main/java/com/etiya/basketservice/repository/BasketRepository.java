package com.etiya.basketservice.repository;

import com.etiya.basketservice.domain.Basket;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import java.util.Map;
import java.util.UUID;

@Repository
public class BasketRepository {

    public  static final String Key = "BASKET";

    private final RedisTemplate<String,Object> redisTemplate;
    private final HashOperations<String,String, Basket> basketHashOperations;


    public BasketRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.basketHashOperations = redisTemplate.opsForHash();
    }

    public void add(Basket basket){
        this.basketHashOperations.put(Key,basket.getId()+"_"+basket.getCustomerId(),basket);
    }

    public Basket getBasketByCustomerId(UUID customerId){
        return basketHashOperations.entries(Key).values().stream().
                filter(basket -> customerId.equals(basket.getCustomerId())).findFirst().orElse(null);
    }

    public Map<String,Basket> getAll(){
        return this.basketHashOperations.entries(Key);
    }


}