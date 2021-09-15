package com.hosjoy.order_data.modules.order_data.bo.req;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ReqMonthScopeBak {
    @DateTimeFormat(pattern = "yyyy-MM")
    private Date createMonthStart;

    @DateTimeFormat(pattern = "yyyy-MM")
    private Date createMonthEnd;
}
