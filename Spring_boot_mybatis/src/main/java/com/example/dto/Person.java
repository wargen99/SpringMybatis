package com.example.dto;

public class Person {

	private String name;
	private String address;
	private int age;
	private int idx;

	public int getIdx() {
		return idx;
	}

	public void setId(int idx) {
		this.idx = idx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public boolean equals(Object other) {
		if (other != null && other instanceof Person) {
			if (((Person) other).getIdx() == this.idx) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return (name == null ? name.length() : 0);
	}

	@Override
	public String toString() {
		return "id[" + idx + "] userName[" + name + "]";
	}
}
