package com.hosjoy.order_data.modules.order_data.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

// import org.springframework.format.annotation.DateTimeFormat;
// import java.util.Date;


@TableName("t_child_order")
@Data
public class CurrentMonthGroupOrderBuyMem {

    @TableId
    // @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String currentMonth;

    private String currentMonthBuyMem;

    private String currentMonthRepBuyMem;

    private Integer currentMonthBuyMemCount;

    private Integer currentMonthRepBuyMemCount;

    private String memRepBuyRate;
}
