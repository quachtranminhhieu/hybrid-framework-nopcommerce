package com.orangeHRM.data;

public class Employee {
	
	public class Role {
		public static final String ADMIN_USERNAME = "Admin";
		public static final String ADMIN_PASSWORD = "admin123";
	}
	
	public class PersonalDetails {
		// Không kết hợp được với Java Faker
		public static final String EMPLOYEE_FIRST_NAME = "John";
		public static final String EMPLOYEE_LAST_NAME = "Wick";
		public static final String EMPLOYEE_FULL_NAME = EMPLOYEE_FIRST_NAME + " " + EMPLOYEE_LAST_NAME;
		public static final String EMPLOYEE_EMAIL = "automationfc1234@gmail.com";
		public static final String EMPLOYEE_USER_NAME = "automationfc1234";
		public static final String EMPLOYEE_PASSWORD = "123123123";
		public static final String EMPLOYEE_STATUS_VALUE = "Enabled";
	}
	
	public class ContactDetails {
		
	}
	
	public class Job {
		
	}
	
	public class Salary {
		
	}
}
