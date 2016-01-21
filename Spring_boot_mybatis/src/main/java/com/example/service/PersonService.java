package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.Person;
import com.example.dto.mapper.PersonMapper;


@Service
@Transactional(propagation= Propagation.REQUIRED, rollbackFor= Exception.class)
public class PersonService {

	@Autowired
	private PersonMapper personRepo;
	
	public void setPersonList(List<Person> personList)
	{
		for(Person person : personList)
		{
			personRepo.insert(person);
		}
	}
}
