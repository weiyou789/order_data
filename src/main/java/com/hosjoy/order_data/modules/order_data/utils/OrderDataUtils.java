package com.hosjoy.order_data.modules.order_data.utils;


import com.hosjoy.order_data.modules.order_data.po.BusinessMem;

import java.text.NumberFormat;
import java.util.List;
import java.util.stream.Collectors;

public final class OrderDataUtils {
    public static Integer busMemCount(List<BusinessMem> businessMem , List<String> memberCodeList){//匹配企业会员数
        List<String> _businessMem = businessMem.stream().map(val->val.getCompanyCode()).collect(Collectors.toList());
        Integer buyBusMemListCount = 0;
        for(String memberCode:memberCodeList){
            if(_businessMem.contains(memberCode)){
                buyBusMemListCount = buyBusMemListCount+1;
            }
        }
        return buyBusMemListCount;
    }

    public static String ratePercent(Integer c,Integer m){//转换百分比
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        return numberFormat.format((float) c / (float) m * 100)+ "%";
    }
}
