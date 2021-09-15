package com.hosjoy.order_data.modules.order_data.controller;

import com.hosjoy.order_data.modules.order_data.bo.req.ReqTimeScope;
import com.hosjoy.order_data.modules.order_data.bo.req.ReqMonthScope;
import com.hosjoy.order_data.modules.order_data.bo.resp.OrderNextMonthRepBuyData;
import com.hosjoy.order_data.modules.order_data.bo.resp.OrderCurrentMonthRepBuyData;
import com.hosjoy.order_data.modules.order_data.service.OrderDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*",maxAge = 3600)
public class OrderDataController {
    @Autowired
    private OrderDataService orderDataService;

    @GetMapping("/getOrderNextMonthRepBuyRateAct")
    public OrderNextMonthRepBuyData getOrderNextMonthRepBuyRateAct(ReqTimeScope req){
        OrderNextMonthRepBuyData orderNextMonthRepBuy = orderDataService.getOrderNextMonthRepBuyRate(req);
        return orderNextMonthRepBuy;
    }

    @GetMapping("/getOrderEveryMonthRepBuyRateAct")
    public List<OrderCurrentMonthRepBuyData> getOrderEveryMonthRepBuyRateAct(ReqMonthScope req){
        List<OrderCurrentMonthRepBuyData> orderCurrentMonthRepBuyData = orderDataService.getOrderCurrentEveryMonthRepBuyData(req);
        return orderCurrentMonthRepBuyData;
    }

    @GetMapping("/getOrderNextEveryMonthRepBuyRateAct")
    public List<OrderCurrentMonthRepBuyData> getOrderNextEveryMonthRepBuyRateAct(ReqMonthScope req){
        List<OrderCurrentMonthRepBuyData> orderCurrentMonthRepBuyDataList = orderDataService.getOrderNextEveryMonthRepBuyData(req);
        return orderCurrentMonthRepBuyDataList;
    }
}
