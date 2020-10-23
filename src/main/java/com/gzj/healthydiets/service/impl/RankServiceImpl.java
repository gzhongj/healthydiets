package com.gzj.healthydiets.service.impl;

import com.gzj.healthydiets.dao.RankDaoImpl;
import com.gzj.healthydiets.service.RankServic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;

@Service
public class RankServiceImpl implements RankServic {
    @Autowired
    private RankDaoImpl rankDao;
    @Override
    public void setSale(Integer foodId, Integer sale) {
        rankDao.setSale(foodId,sale);
    }

    @Override
    public LinkedHashSet<String> getRank() {
        LinkedHashSet<String> rank = rankDao.getRank();
        return rank;
    }
}
