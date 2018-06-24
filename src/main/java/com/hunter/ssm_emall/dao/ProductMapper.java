package com.hunter.ssm_emall.dao;

import com.hunter.ssm_emall.bean.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {

    List<Product> getProductList();

    Product getProduct(@Param("id") Integer id);

    @Insert("INSERT INTO content(price, title, num, icon, abstract, text) " +
            "VALUE (#{price}, #{title}, #{num}, #{image}, #{summary}, #{detail})")
    @Options(useGeneratedKeys = true)
    void publicProduct(Product product);

    int getCount();

    @Insert("UPDATE content SET price=#{price}, title=#{title}, " +
            "icon=#{image}, abstract= #{summary}, text=#{detail} " +
            "WHERE id=#{id}")
    boolean updateProduct(Product product);

    //如果已经发生过交易，则不能删除
    @Delete("DELETE FROM content WHERE id=#{id} " +
            "AND NOT EXISTS(SELECT * FROM trx WHERE contentId=#{id})")
    boolean deleteProduct(@Param("id") Integer id);
}
