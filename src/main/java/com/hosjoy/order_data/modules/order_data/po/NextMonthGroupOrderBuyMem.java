package com.hosjoy.order_data.modules.order_data.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
// import org.springframework.format.annotation.DateTimeFormat;

// import java.util.Date;
import java.util.List;

@TableName("t_child_order")
@Data
public class NextMonthGroupOrderBuyMem {

    @TableId
    // @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String currentMonth;

    private String currentMonthBuyMem;

    private String nextMonthRepBuyMem;

    private Integer currentMemberCount;

    private Integer nextMonthRepBuyMemCount;

    private String memRepBuyRate;
}
