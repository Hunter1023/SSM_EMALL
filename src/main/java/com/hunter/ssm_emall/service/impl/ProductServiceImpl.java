package com.hunter.ssm_emall.service.impl;

import com.hunter.ssm_emall.bean.Product;
import com.hunter.ssm_emall.bean.User;
import com.hunter.ssm_emall.dao.ProductMapper;
import com.hunter.ssm_emall.service.ProductService;
import com.hunter.ssm_emall.utils.ConvertPriceUnitUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper productMapper;

    //获取商品列表
    @Override
    public List<Product> getProductList(User user, String type) {

        List<Product> productList = productMapper.getProductList();

        if (user != null) {//如果是用户登录状态
            Iterator<Product> iterator = productList.iterator();
            while (iterator.hasNext()) {
                Product product = iterator.next();
                //数据库用分为单位存储价格，转成以元为单位
                product.setPrice(ConvertPriceUnitUtil
                        .convertF2Y(product.getPrice()));
                if (product.getTrxCount() > 0) {
                    product.setSell(true);
                    product.setBuy(true);
                }

                if ("1".equals(type)) {//如果要求只显示未购买内容
                    if (product.getIsBuy()) {
                        iterator.remove();
                    }
                }
            }
        }
        return productList;
    }

    //获取某个商品
    @Override
    public Product getProduct(User user, Integer productId) {

        Product product = productMapper.getProduct(productId);

        if (product != null) {//如果存在对应的商品
            //数据库用分为单位存储价格，转成以元为单位
            product.setPrice(ConvertPriceUnitUtil
                    .convertF2Y(product.getPrice()));
            if (user != null) {//如果用户已登录
                if (product.getTrxCount() > 0) {
                    product.setBuy(true);
                }
            }
        }
        return product;
    }

    //发布商品
    @Override
    public void publicProduct(Product product) {
        //将价格转为分为单位
        product.setPrice(ConvertPriceUnitUtil
                .convertY2F(product.getPrice()));
        //前端没有对应库存的展示，为了省事后端自行传输,库存值统一为100
        product.setNum(100);
        productMapper.publicProduct(product);
    }

    //查询商品总数
    @Override
    public int getCount() {
        return productMapper.getCount();
    }

    //修改商品
    @Override
    public boolean updateProduct(Product product) {
        //将价格转为分为单位
        product.setPrice(ConvertPriceUnitUtil
                .convertY2F(product.getPrice()));
        return productMapper.updateProduct(product);
    }

    //删除商品
    @Override
    public boolean deleteProdct(Integer id) {
        return productMapper.deleteProduct(id);
    }

}
