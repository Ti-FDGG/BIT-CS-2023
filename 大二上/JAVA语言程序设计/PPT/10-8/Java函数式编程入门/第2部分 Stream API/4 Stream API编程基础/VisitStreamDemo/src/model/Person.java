package model;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class Person {
	// 枚举，表示性别
	public static enum Gender {
		MALE, FEMALE
	}

	private long id;
	private String name;
	private Gender gender;
	private LocalDate birthday;
	private double income;

	public Person(long id, String name, Gender gender, LocalDate dob,
                  double income) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.birthday = dob;
		this.income = income;
	}

//region "getter and setter"
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public boolean isMale() {
		return this.gender == Gender.MALE;
	}

	public boolean isFemale() {
		return this.gender == Gender.FEMALE;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public LocalDate getDob() {
		return birthday;
	}

	public void setDob(LocalDate dob) {
		this.birthday = dob;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}
//endregion

	//返回一个Person对象集合，用于演示
	public static List<Person> persons() {
		Person zhang = new Person(1, "张三", Gender.MALE, LocalDate.of(1970,
				Month.MAY, 4), 6000.0);
		Person li = new Person(2, "李四", Gender.MALE, LocalDate.of(1970,
				Month.JULY, 15), 7100.0);
		Person wang = new Person(3, "王五", Gender.FEMALE, LocalDate.of(1962,
				Month.JULY, 29), 8700.0);
		Person zhao = new Person(4, "赵六", Gender.MALE, LocalDate.of(1993,
				Month.DECEMBER, 16), 1800.0);
		Person huang = new Person(5, "黄七", Gender.FEMALE, LocalDate.of(
				2012, Month.DECEMBER, 13), 0.0);
		Person lu = new Person(6, "陆八", Gender.MALE, LocalDate.of(2001,
				Month.MAY, 9), 2400.0);
		// Create a list of persons
		List<Person> persons = Arrays.asList(zhang, li, wang, zhao, huang,
				lu);
		return persons;
	}

	//重写基类的toString()方法，以便给出有意义的输出信息
	@Override
	public String toString() {
		String str = String.format("(%s, %s, %s, %s, %.2f)", id, name, gender,
				birthday, income);
		return str;
	}
}
