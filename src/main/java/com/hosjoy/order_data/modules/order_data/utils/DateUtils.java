package com.hosjoy.order_data.modules.order_data.utils;

import java.util.Calendar;
import java.util.Date;

public final class DateUtils {
    public static Date nextMonthDay(Date date,String type){//获取下个月的第一天获取最后一天
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(cal.MONTH,1);//获取下个月1号
        if(type=="endDay"){
            cal.set(cal.DAY_OF_MONTH, cal.getActualMaximum(cal.DAY_OF_MONTH));//获取下个月最后一天
        }
        return cal.getTime();
    }
}
