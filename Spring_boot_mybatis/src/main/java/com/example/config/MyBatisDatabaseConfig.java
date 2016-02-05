/**
 * 
 */
package com.example.config;

import java.lang.annotation.Annotation;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.dto.FirstDatabase;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = { "com.example.dto.mapper" }, sqlSessionFactoryRef = "mybatisSqlSessionFactory", annotationClass = FirstDatabase.class)
public class MyBatisDatabaseConfig extends DefaultDatabaseConfig {

	@Autowired
	private ApplicationContext applicationContext;

	@Bean(destroyMethod = "close", name = "derbyDatasource")
	public DataSource dataSource() {
		org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
		configureDataSource(dataSource);
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager(
			@Qualifier("derbyDatasource") DataSource datasource) {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(
				datasource);
		transactionManager.setGlobalRollbackOnParticipationFailure(false);
		return transactionManager;
	}

	@Bean(name = "mybatisSqlSessionFactory")
	public SqlSessionFactory mybatisSqlSessionFactory(
			@Qualifier("derbyDatasource") DataSource datasource)
			throws Exception {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(datasource);
		sessionFactoryBean.setTypeAliasesPackage("com.example.dto");
		sessionFactoryBean.setConfigLocation(applicationContext
				.getResource("classpath:mybatis-config.xml"));
		sessionFactoryBean.setMapperLocations(applicationContext
				.getResources("classpath:META-INF/mybatis/mapper/*.xml"));
		return sessionFactoryBean.getObject();
	}
}