package com.gzj.healthydiets.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashSet;

@Repository
public class RankDaoImpl implements RankDao {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Override
    public void setSale(Integer foodId,Integer sale) {

        redisTemplate.opsForZSet().incrementScore("saleRank",String.valueOf(foodId),sale);
    }

    @Override
    public LinkedHashSet<String> getRank() {
        LinkedHashSet<String> saleRank = (LinkedHashSet<String>) redisTemplate.opsForZSet().reverseRange("saleRank",0,9 );
        return saleRank;
    }
}
