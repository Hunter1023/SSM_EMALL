package com.hunter.ssm_emall.service;

import com.hunter.ssm_emall.bean.Product;
import com.hunter.ssm_emall.bean.User;

import javax.validation.Valid;
import java.util.List;

public interface ProductService {
    List<Product> getProductList(User user, String type);

    Product getProduct(User user, Integer productId);

    void publicProduct(Product product);

    int getCount();

    boolean updateProduct(Product product);

    boolean deleteProdct(Integer id);
}
