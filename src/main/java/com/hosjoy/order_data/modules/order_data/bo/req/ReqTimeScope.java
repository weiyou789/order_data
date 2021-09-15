package com.hosjoy.order_data.modules.order_data.bo.req;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ReqTimeScope {
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeEnd;
}
