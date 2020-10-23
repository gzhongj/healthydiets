package com.gzj.healthydiets.dao;

import java.util.LinkedHashSet;

public interface RankDao {
    public void setSale(Integer foodId,Integer sale);
    public LinkedHashSet<String> getRank();
}
