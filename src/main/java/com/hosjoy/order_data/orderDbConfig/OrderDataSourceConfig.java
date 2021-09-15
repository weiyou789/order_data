package com.hosjoy.order_data.orderDbConfig;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Primary;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;


@Configuration
@MapperScan(basePackages = "com.hosjoy.order_data.modules.order_data.mapper.order", sqlSessionFactoryRef = "orderSqlSessionFactory")
public class OrderDataSourceConfig {
    @Bean(name="orderDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.order")
    public HikariDataSource orderDataSource(){
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name = "orderSqlSessionFactory")
    @Primary
    public SqlSessionFactory orderSqlSessionFactory(@Qualifier("orderDataSource") DataSource datasource, PaginationInterceptor paginationInterceptor)throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(datasource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/order/**/**.xml"));
        bean.setPlugins(new Interceptor[]{paginationInterceptor});
        return bean.getObject();
    }
    @Bean(name = "orderSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate masterSqlSessionTemplate(@Qualifier("orderSqlSessionFactory") SqlSessionFactory sessionfactory) {
        return new SqlSessionTemplate(sessionfactory);
    }
}
