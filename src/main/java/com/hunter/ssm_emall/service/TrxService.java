package com.hunter.ssm_emall.service;

import com.hunter.ssm_emall.bean.Product;
import com.hunter.ssm_emall.bean.User;

import java.util.List;

public interface TrxService {

    List<Product> getBuyList(Integer userId);

    boolean buy(User user, List<Product> productList, Long buyTime);
}
