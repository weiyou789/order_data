package com.hosjoy.order_data.modules.order_data.mapper.order;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
// import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.hosjoy.order_data.modules.order_data.po.MonthOrderBuyMem;
import com.hosjoy.order_data.modules.order_data.po.NextMonthGroupOrderBuyMem;
import com.hosjoy.order_data.modules.order_data.po.CurrentMonthGroupOrderBuyMem;
import com.hosjoy.order_data.modules.order_data.bo.req.ReqTimeScope;
import com.hosjoy.order_data.modules.order_data.bo.req.ReqMonthScope;

import java.util.List;


public interface OrderDataMapper extends BaseMapper<MonthOrderBuyMem>{
    // List<MonthOrderBuyMem> getMonthOrderBuyMem(@Param("createTimeStart") Date createTimeStart,@Param("createTimeEnd") Date createTimeEnd,@Param("memberCodes") List<MonthOrderBuyMem> memberCodes);
    List<MonthOrderBuyMem> getMonthOrderBuyMem(@Param("req") ReqTimeScope reqTimeScope,@Param("memberCodeList") List<MonthOrderBuyMem> memberCodeList);

    List<CurrentMonthGroupOrderBuyMem> getMonthGroupOrderBuyMem(@Param("req") ReqMonthScope reqMonthScope);

    List<NextMonthGroupOrderBuyMem> getNextMonthGroupOrderBuyMem(@Param("req") ReqMonthScope reqMonthScope);
}
