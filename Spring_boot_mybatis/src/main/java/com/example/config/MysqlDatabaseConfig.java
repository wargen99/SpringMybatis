package com.example.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.dto.SecondDatabase;


@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = { "com.example.mysql.dto.mapper" }, sqlSessionFactoryRef = "mysqlSqlSessionFactory", annotationClass=SecondDatabase.class)
@EnableConfigurationProperties(MysqlProperties.class)
public class MysqlDatabaseConfig  {

	@Autowired
	private ApplicationContext applicationContext;
	
	
	@Autowired
	private MysqlProperties mysqlProperties;

	protected void configureDataSource(
			org.apache.tomcat.jdbc.pool.DataSource dataSource) {
		dataSource.setDriverClassName(mysqlProperties.getDriverClassName());
		dataSource.setUrl(mysqlProperties.getUrl());
		dataSource.setUsername(mysqlProperties.getUserName());
		dataSource.setPassword(mysqlProperties.getPassword());
		dataSource.setMaxActive(mysqlProperties.getMaxActive());
		dataSource.setMaxIdle(mysqlProperties.getMaxIdle());
		dataSource.setMinIdle(mysqlProperties.getMinIdle());
		dataSource.setMaxWait(mysqlProperties.getMaxWait());
		dataSource.setTestOnBorrow(false);
		dataSource.setTestOnReturn(false);
	}
	
	
	@Bean(destroyMethod = "close",name ="mysqlDatasource")
	public DataSource dataSource() {
		org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
		configureDataSource(dataSource);
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager(@Qualifier("mysqlDatasource")DataSource datasource) {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(
				datasource);
		transactionManager.setGlobalRollbackOnParticipationFailure(false);
		return transactionManager;
	}

	@Bean(name = "mysqlSqlSessionFactory")
	public SqlSessionFactory mybatisSqlSessionFactory(@Qualifier("mysqlDatasource")DataSource datasource) throws Exception {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(datasource);
		sessionFactoryBean.setTypeAliasesPackage("com.example.mysql.dto");
		sessionFactoryBean.setConfigLocation(applicationContext
				.getResource("classpath:mybatis-config.xml"));
		sessionFactoryBean.setMapperLocations(applicationContext
				.getResources("classpath:META-INF/mybatis/mapper/*.xml"));
		return sessionFactoryBean.getObject();
	}
}