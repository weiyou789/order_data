package com.hosjoy.order_data.modules.order_data.bo.resp;

import lombok.Data;

@Data
public class OrderNextMonthRepBuyData {
    private String rate;

    private String rateBus;

    private Integer buyMemListCount;

    private Integer buyBusMemListCount;

    private Integer nextBuyMemListCount;

    private Integer nextBuyBusMemListCount;
}
