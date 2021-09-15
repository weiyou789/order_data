package com.hosjoy.order_data.paymentDbconfig;

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
@MapperScan(basePackages = "com.hosjoy.order_data.modules.order_data.mapper.payment", sqlSessionFactoryRef = "paymentSqlSessionFactory")
public class PaymentDataSourceConfig {
    @Bean(name="paymentDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.payment")
    public HikariDataSource paymentDataSource(){
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name = "paymentSqlSessionFactory")
    @Primary
    public SqlSessionFactory paymentSqlSessionFactory(@Qualifier("paymentDataSource") DataSource datasource, PaginationInterceptor paginationInterceptor)throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(datasource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/payment/**/**.xml"));
        bean.setPlugins(new Interceptor[]{paginationInterceptor});
        return bean.getObject();
    }
    @Bean(name = "paymentSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate masterSqlSessionTemplate(@Qualifier("paymentSqlSessionFactory") SqlSessionFactory sessionfactory) {
        return new SqlSessionTemplate(sessionfactory);
    }
}
