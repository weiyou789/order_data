package com.hosjoy.order_data.modules.order_data.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("t_child_order")
@Data
public class MonthOrderBuyMem {

    @TableId
    private String memberCode;
}
