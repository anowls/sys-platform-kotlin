package org.anowls.sys.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder
import org.apache.ibatis.mapping.VendorDatabaseIdProvider
import org.apache.ibatis.session.SqlSessionFactory
import org.mybatis.spring.SqlSessionFactoryBean
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import tk.mybatis.mapper.autoconfigure.MybatisProperties
import java.util.*
import javax.sql.DataSource

/**
 * <p>Title: sys_platform</p>
 * <p>Description: Mybatis 配置</p>
 * <p>Copyright: Copyright © 2017-2020 汉博德信息技术有限公司 All Rights Reserved</p>
 * <p>Company: http://www.hanboard.com</p>
 *
 * @author haopeisong
 * @date 2018/1/26 0026
 */
@Configuration
open class MyBatisConfiguration {

    companion object {
        private const val  MAPPER_LOCATION : String = "classpath:mapper/*Mapper.xml"
        private const val  MY_BATIS_CONFIG_LOCATION : String = "classpath:mybatis/mybatis-config.xml"
    }


    @Bean(name = ["dataSource"])
    @Primary
    @ConfigurationProperties("spring.datasource")
    open fun dataSource() : DataSource {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = ["transactionManager"])
    @Primary
    open fun transactionManager() : DataSourceTransactionManager {
        return DataSourceTransactionManager(dataSource())
    }

    @Bean(name = ["sqlSessionFactory"])
    @Primary
    open fun sqlSessionFactory(@Qualifier("dataSource") dataSource : DataSource) : SqlSessionFactory {
        val sessionFactory = SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        sessionFactory.setMapperLocations(PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION))
        sessionFactory.setConfigLocation(PathMatchingResourcePatternResolver().getResource(MY_BATIS_CONFIG_LOCATION))
        val vendorDatabaseIdProvider = VendorDatabaseIdProvider()
        val properties = Properties()
        properties.setProperty("Oracle", "Oracle");
        properties.setProperty("MySQL", "MySQL");
        vendorDatabaseIdProvider.setProperties(properties);
        sessionFactory.databaseIdProvider = vendorDatabaseIdProvider;
        return sessionFactory.`object`
    }

    @Bean
    @Primary
    open fun mybatisProperties() : MybatisProperties {
        val p = MybatisProperties()
        val config : org.apache.ibatis.session.Configuration = org.apache.ibatis.session.Configuration()
        config.setMapUnderscoreToCamelCase(true)
        p.configuration = config
        return p
    }

}
