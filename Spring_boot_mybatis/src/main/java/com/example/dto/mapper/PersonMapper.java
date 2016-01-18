package com.example.dto.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.dto.Person;

public interface PersonMapper {

	public List<Person> selectList();

	public Person selectOne(Long id);

	public Person selectByUserName(@Param("name") String name);

	public void insert(Person user);

	public void update(Person user);

	public void delete(Person user);
	
	public void createTable();
	
	public void dropTable();
}
