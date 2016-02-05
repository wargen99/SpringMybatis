package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;






import com.example.mysql.dto.Project;
import com.example.mysql.dto.mapper.ProjectMapper;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProjectService {

	@Autowired
	private ProjectMapper projectMapper;
	
	public void setPersonList(List<Project> projectList) {
		for (Project project : projectList) {
			projectMapper.insert(project);
		}
	}
}
