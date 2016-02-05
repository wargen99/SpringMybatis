package com.example.mysql.dto.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.stereotype.Component;

import com.example.dto.SecondDatabase;
import com.example.mysql.dto.Project;



@SecondDatabase
public interface ProjectMapper {

	public List<Project> selectList();

	public Project selectOne(int id);

	public Project selectByProjectName(@Param("projectName") String projectName);

	public void insert(Project project);

	public void update(Project project);

	public void delete(Project project);

	public void createTable();

	public void dropTable();
}
