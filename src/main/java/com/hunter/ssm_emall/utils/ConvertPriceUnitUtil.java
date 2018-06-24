package com.hunter.ssm_emall.utils;

import java.math.BigDecimal;

public class ConvertPriceUnitUtil {

    /**
     * 从单位元转为分
     * @param priceWithY 上传的价格
     * @return 单位为分的价格
     */
    public static String convertY2F(String priceWithY) {
        //获取小数点的位置
        int index = priceWithY.indexOf(".");
        //获取价格的长度
        int length = priceWithY.length();
        //创建 以元为单位的价格
        String priceWithF = "0";

        if (index == -1) {//如果不存在小数点，末尾添加两个0
            priceWithF = priceWithY + "00";
        } else if (length - index >= 3) {//如果是两位小数以上，截取至小数点后两位，去掉小数点
            priceWithF = (priceWithY.substring(0, index + 3)).replace(".", "");
        } else if (length - index == 2) {//如果是一位小数，去掉小数点并在末尾添加一个0
            priceWithF = priceWithY.replace(".", "") + "0";
        } else {//如果 价格的最后一位是小数点，去掉小数点，加上两个零
            priceWithF = priceWithY.replace(".", "") + "00";
        }

        return priceWithF;
    }

    /**
     *从单位分转为元
     */
    public static String convertF2Y(String priceWithF){

        return BigDecimal.valueOf(Integer.valueOf(priceWithF))
                .divide(new BigDecimal(100)).toString();
    }

}
