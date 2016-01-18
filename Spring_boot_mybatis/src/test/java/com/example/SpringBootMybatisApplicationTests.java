package com.example;

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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootMybatisApplication.class)
@WebAppConfiguration
public class SpringBootMybatisApplicationTests {

	@Autowired
	PersonMapper personRepo;

	@Test
	public void MybatisTest() {

		Person person = new Person();
		person.setId(1);
		person.setAge(10);
		person.setAddress("seoul my home");
		person.setName("wargen");

		personRepo.insert(person);

		Person findPerson = personRepo.selectByUserName("wargen");

		Assert.assertThat(findPerson.getName(),
				org.hamcrest.core.Is.is(person.getName()));
	}

}
