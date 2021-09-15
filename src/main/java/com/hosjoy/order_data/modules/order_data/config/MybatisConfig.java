package com.hosjoy.order_data.modules.order_data.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisConfig {

    @Bean
     public PaginationInterceptor paginationInterceptor(){
         PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
         paginationInterceptor.setOverflow(true);
         return paginationInterceptor;
     }
}
