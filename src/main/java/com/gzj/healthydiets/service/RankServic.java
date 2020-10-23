package com.gzj.healthydiets.service;

import java.util.LinkedHashSet;

public interface RankServic {
    public void setSale(Integer foodId,Integer sale);
    public LinkedHashSet<String> getRank();
}
