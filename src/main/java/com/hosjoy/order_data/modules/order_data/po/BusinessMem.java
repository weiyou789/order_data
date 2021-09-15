package com.hosjoy.order_data.modules.order_data.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("t_authentication_info")
@Data
public class BusinessMem {
    @TableId
    private String companyCode;
}
