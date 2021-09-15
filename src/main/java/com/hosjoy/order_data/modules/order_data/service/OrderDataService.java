package com.hosjoy.order_data.modules.order_data.service;

import com.hosjoy.order_data.modules.order_data.bo.resp.OrderNextMonthRepBuyData;
import com.hosjoy.order_data.modules.order_data.bo.resp.OrderCurrentMonthRepBuyData;
import com.hosjoy.order_data.modules.order_data.mapper.order.OrderDataMapper;
import com.hosjoy.order_data.modules.order_data.mapper.payment.PaymentDataMapper;
import com.hosjoy.order_data.modules.order_data.bo.req.ReqTimeScope;
import com.hosjoy.order_data.modules.order_data.bo.req.ReqMonthScope;
import com.hosjoy.order_data.modules.order_data.po.MonthOrderBuyMem;
import com.hosjoy.order_data.modules.order_data.po.BusinessMem;
import com.hosjoy.order_data.modules.order_data.po.NextMonthGroupOrderBuyMem;
import com.hosjoy.order_data.modules.order_data.po.CurrentMonthGroupOrderBuyMem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hosjoy.order_data.modules.order_data.utils.OrderDataUtils;
import com.hosjoy.order_data.modules.order_data.utils.DateUtils;

import net.sf.json.JSONArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderDataService {

    @Autowired
    OrderDataMapper orderDataMapper;

    @Autowired
    PaymentDataMapper paymentDataMapper;

    public OrderNextMonthRepBuyData getOrderNextMonthRepBuyRate(ReqTimeScope req){
        ReqTimeScope nextMonth = new ReqTimeScope();
        nextMonth.setCreateTimeStart(DateUtils.nextMonthDay(req.getCreateTimeStart(),"startDay"));
        nextMonth.setCreateTimeEnd(DateUtils.nextMonthDay(req.getCreateTimeEnd(),"endDay"));
        List<MonthOrderBuyMem> monthOrderBuyMem = orderDataMapper.getMonthOrderBuyMem(req,null);//获取当月会员购买数
        List<MonthOrderBuyMem> nextMonthOrderBuyMem = orderDataMapper.getMonthOrderBuyMem(nextMonth,null);//获取次月会员购买数
        List<MonthOrderBuyMem> monthOrderRepBuyMem = orderDataMapper.getMonthOrderBuyMem(req,nextMonthOrderBuyMem);//获取这个月在次月又购买的会员数
        List<BusinessMem> businessMem = paymentDataMapper.getBusinessMem();//获取企业会员数
        List<String> monthOrderRepBuyMemArr = monthOrderRepBuyMem.stream().map(item->item.getMemberCode()).collect(Collectors.toList());
        List<String> monthOrderBuyMemArr = monthOrderBuyMem.stream().map(item->item.getMemberCode()).collect(Collectors.toList());
        OrderNextMonthRepBuyData orderNextMonthRepBuyData = new OrderNextMonthRepBuyData();
        orderNextMonthRepBuyData.setRate(OrderDataUtils.ratePercent(monthOrderRepBuyMem.size(),monthOrderBuyMem.size()));
        orderNextMonthRepBuyData.setRateBus(OrderDataUtils.ratePercent(OrderDataUtils.busMemCount(businessMem,monthOrderRepBuyMemArr),OrderDataUtils.busMemCount(businessMem,monthOrderBuyMemArr)));
        orderNextMonthRepBuyData.setBuyMemListCount(monthOrderBuyMem.size());
        orderNextMonthRepBuyData.setBuyBusMemListCount(OrderDataUtils.busMemCount(businessMem,monthOrderBuyMemArr));
        orderNextMonthRepBuyData.setNextBuyMemListCount(monthOrderRepBuyMem.size());
        orderNextMonthRepBuyData.setNextBuyBusMemListCount(OrderDataUtils.busMemCount(businessMem,monthOrderRepBuyMemArr));
        return orderNextMonthRepBuyData;
    }

    public List<OrderCurrentMonthRepBuyData> getOrderCurrentEveryMonthRepBuyData(ReqMonthScope req){
        List<CurrentMonthGroupOrderBuyMem> monthGroupOrderBuyMem = orderDataMapper.getMonthGroupOrderBuyMem(req);//获取每月购买会员列表,会员数,日期
        List<BusinessMem> businessMem = paymentDataMapper.getBusinessMem();//获取企业会员数
        List OrderCurrentMonthRepBuyDataList = new ArrayList<OrderCurrentMonthRepBuyData>();
        for(CurrentMonthGroupOrderBuyMem currentMonthOrderBuyMem:monthGroupOrderBuyMem){
            JSONArray jsonCurrentMonthBuyMem = JSONArray.fromObject(currentMonthOrderBuyMem.getCurrentMonthBuyMem());//把字符串转换成JSONArray
            JSONArray jsonCurrentMonthRepBuyMem = JSONArray.fromObject(currentMonthOrderBuyMem.getCurrentMonthRepBuyMem());//把字符串转换成JSONArray
            List<String> currentMonthBuyMem = JSONArray.toList(jsonCurrentMonthBuyMem);//把JSONArray转换成List
            List<String> currentMonthRepBuyMem = JSONArray.toList(jsonCurrentMonthRepBuyMem);//把JSONArray转换成List
            Integer buyBusMemListCount = OrderDataUtils.busMemCount(businessMem,currentMonthBuyMem);//获取企业会员购买数
            Integer buyRepBusMemListCount = OrderDataUtils.busMemCount(businessMem,currentMonthRepBuyMem);//获取复购企业会员购买数
            currentMonthRepBuyMem.removeAll(Collections.singleton(null));//去null
            OrderCurrentMonthRepBuyData orderCurrentMonthRepBuyData = new OrderCurrentMonthRepBuyData();
            orderCurrentMonthRepBuyData.setBuyBusMemListCount(buyBusMemListCount);
            orderCurrentMonthRepBuyData.setBuyMemListCount(currentMonthOrderBuyMem.getCurrentMonthBuyMemCount());
            orderCurrentMonthRepBuyData.setBuyRepBusMemListCount(buyRepBusMemListCount);
            orderCurrentMonthRepBuyData.setBuyRepMemListCount(currentMonthOrderBuyMem.getCurrentMonthRepBuyMemCount());
            orderCurrentMonthRepBuyData.setCurrentMonth(currentMonthOrderBuyMem.getCurrentMonth());
            orderCurrentMonthRepBuyData.setRate(currentMonthOrderBuyMem.getMemRepBuyRate());
            orderCurrentMonthRepBuyData.setRateBus(OrderDataUtils.ratePercent(buyRepBusMemListCount,buyBusMemListCount));
            OrderCurrentMonthRepBuyDataList.add(orderCurrentMonthRepBuyData);
        }
        return OrderCurrentMonthRepBuyDataList;
    }

    public List<OrderCurrentMonthRepBuyData> getOrderNextEveryMonthRepBuyData(ReqMonthScope req){
        List<NextMonthGroupOrderBuyMem> nextMonthGroupOrderBuyMem = orderDataMapper.getNextMonthGroupOrderBuyMem(req);
        List<BusinessMem> businessMem = paymentDataMapper.getBusinessMem();//获取企业会员数
        List OrderCurrentMonthRepBuyDataList = new ArrayList<OrderCurrentMonthRepBuyData>();
        for(NextMonthGroupOrderBuyMem nextMonthOrderBuyMem:nextMonthGroupOrderBuyMem){
            JSONArray jsonCurrentMonthBuyMem = JSONArray.fromObject(nextMonthOrderBuyMem.getCurrentMonthBuyMem());//把字符串转换成JSONArray
            JSONArray jsonNextMonthRepBuyMem = JSONArray.fromObject(nextMonthOrderBuyMem.getNextMonthRepBuyMem());//把字符串转换成JSONArray
            List<String> currentMonthBuyMem = JSONArray.toList(jsonCurrentMonthBuyMem);//把JSONArray转换成List
            List<String> nextMonthRepBuyMem = JSONArray.toList(jsonNextMonthRepBuyMem);//把JSONArray转换成List
            Integer buyBusMemListCount = OrderDataUtils.busMemCount(businessMem,currentMonthBuyMem);//获取企业会员购买数
            Integer buyRepBusMemListCount = OrderDataUtils.busMemCount(businessMem,nextMonthRepBuyMem);//获取复购企业会员购买数
            nextMonthRepBuyMem.removeAll(Collections.singleton(null));//去null
            OrderCurrentMonthRepBuyData orderCurrentMonthRepBuyData = new OrderCurrentMonthRepBuyData();
            orderCurrentMonthRepBuyData.setRate(nextMonthOrderBuyMem.getMemRepBuyRate());
            orderCurrentMonthRepBuyData.setRateBus(OrderDataUtils.ratePercent(buyRepBusMemListCount,buyBusMemListCount));
            orderCurrentMonthRepBuyData.setCurrentMonth(nextMonthOrderBuyMem.getCurrentMonth());
            orderCurrentMonthRepBuyData.setBuyMemListCount(nextMonthOrderBuyMem.getCurrentMemberCount());
            orderCurrentMonthRepBuyData.setBuyBusMemListCount(buyBusMemListCount);
            orderCurrentMonthRepBuyData.setBuyRepMemListCount(nextMonthOrderBuyMem.getNextMonthRepBuyMemCount());
            orderCurrentMonthRepBuyData.setBuyRepBusMemListCount(buyRepBusMemListCount);
            OrderCurrentMonthRepBuyDataList.add(orderCurrentMonthRepBuyData);
        }
        return OrderCurrentMonthRepBuyDataList;
    }

}
