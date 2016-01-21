package com.example;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.hamcrest.core.Is;

import com.example.dto.Person;
import com.example.dto.mapper.PersonMapper;
import com.example.service.PersonService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootMybatisApplication.class)
@WebAppConfiguration
public class SpringBootMybatisApplicationTests {

	@Autowired
	PersonMapper personRepo;
	
	@Autowired
	private PersonService personService;
	
	/*@Test
	public void mybatisTest() {

		Person person = new Person();
		person.setId(1);
		person.setAge(10);
		person.setAddress("seoul my home");
		person.setName("wargen");

		personRepo.insert(person);

		Person findPerson = personRepo.selectByUserName("wargen");

		Assert.assertThat(findPerson.getName(),
				org.hamcrest.core.Is.is(person.getName()));
	}*/
	
	@Test
	public void mybatisRollbackTest()
	{
		ArrayList<Person> personArray = new ArrayList<Person>();
		Person person = new Person();
		person.setId(1);
		person.setAge(10);
		person.setAddress("seoul my home");
		person.setName("wargen");
		
		personArray.add(person);
		
		Person person2 = new Person();
		person2.setId(1);
		person2.setAge(10);
		person2.setAddress("seoul my home");
		person2.setName("wargen");
		
		personArray.add(person2);
		
		
		personService.setPersonList((List<Person>)personArray);
	}

}
