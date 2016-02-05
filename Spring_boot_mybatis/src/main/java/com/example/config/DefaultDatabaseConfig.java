package com.example.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(value = MyBatisProperties.class)
public abstract class DefaultDatabaseConfig {

	@Autowired
	private MyBatisProperties mybatisProperties;

	@Bean
	public abstract DataSource dataSource();

	protected void configureDataSource(
			org.apache.tomcat.jdbc.pool.DataSource dataSource) {
		dataSource.setDriverClassName(mybatisProperties.getDriverClassName());
		dataSource.setUrl(mybatisProperties.getUrl());
		dataSource.setUsername(mybatisProperties.getUserName());
		dataSource.setPassword(mybatisProperties.getPassword());
		dataSource.setMaxActive(mybatisProperties.getMaxActive());
		dataSource.setMaxIdle(mybatisProperties.getMaxIdle());
		dataSource.setMinIdle(mybatisProperties.getMinIdle());
		dataSource.setMaxWait(mybatisProperties.getMaxWait());
		dataSource.setTestOnBorrow(false);
		dataSource.setTestOnReturn(false);
	}
}