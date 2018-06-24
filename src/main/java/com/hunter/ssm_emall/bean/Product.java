package com.hunter.ssm_emall.bean;

import javax.validation.constraints.Size;

public class Product {

    //商品id
    private Integer id;
    //商品名称
    @Size(min = 2, max = 80, message = "标题长度在[2,80]字符内")
    private String title;
    //商品图片的地址
    private String image;
    //商品价格
    private String price;
    //数量
    private Integer num;
    //摘要
    @Size(min = 2, max = 140, message = "摘要长度在[2,140]字符内")
    private String summary;
    //正文
    @Size(min = 2, max = 1000, message = "正文长度在[2,1000]字符内")
    private String detail;

    //非数据库内容
    //是否已购买
    private boolean isBuy;
    //已卖出
    private boolean isSell;
    //销量
    private Integer trxCount;
    //购买时价格
    private String buyPrice;
    //购买数量
    private Integer buyNum;
    //购买时间
    private long buyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }


    public boolean getIsBuy() {
        return isBuy;
    }

    public void setBuy(boolean buy) {
        isBuy = buy;
    }

    public boolean getIsSell() {
        return isSell;
    }

    public void setSell(boolean sell) {
        isSell = sell;
    }

    public Integer getTrxCount() {
        return trxCount;
    }

    public void setTrxCount(Integer trxCount) {
        this.trxCount = trxCount;
    }

    public String getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(String buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Integer getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    public long getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(long buyTime) {
        this.buyTime = buyTime;
    }
}
