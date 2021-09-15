package com.hosjoy.order_data.modules.order_data.mapper.payment;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hosjoy.order_data.modules.order_data.po.BusinessMem;

import java.util.List;


public interface PaymentDataMapper extends BaseMapper<BusinessMem>{
    List<BusinessMem> getBusinessMem();
}
