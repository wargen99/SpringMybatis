package com.example.dto.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.example.dto.FirstDatabase;
import com.example.dto.Person;

@FirstDatabase
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
