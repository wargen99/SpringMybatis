/**
 * 
 */
package com.example.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = { "com.example.dto.mapper" }, sqlSessionFactoryRef = "mybatisSqlSessionFactory")
public class MyBatisDatabaseConfig extends DefaultDatabaseConfig {

	@Autowired
	private ApplicationContext applicationContext;

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
		configureDataSource(dataSource);
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(
				dataSource());
		transactionManager.setGlobalRollbackOnParticipationFailure(false);
		return transactionManager;
	}

	@Bean(name = "mybatisSqlSessionFactory")
	public SqlSessionFactory mybatisSqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource());
		sessionFactoryBean.setTypeAliasesPackage("com.example.dto");
		sessionFactoryBean.setConfigLocation(applicationContext
				.getResource("classpath:mybatis-config.xml"));
		sessionFactoryBean.setMapperLocations(applicationContext
				.getResources("classpath:META-INF/mybatis/mapper/*.xml"));
		return sessionFactoryBean.getObject();
	}
}