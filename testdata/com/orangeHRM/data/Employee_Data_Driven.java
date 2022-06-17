package com.orangeHRM.data;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import commons.GlobalConstans;

public class Employee_Data_Driven {
	public static Employee_Data_Driven getEmployee() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(new File(GlobalConstans.PROJECT_PATH + "\\testdata\\com\\orangeHRM\\data\\Employee.json"), Employee_Data_Driven.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@JsonProperty("firstname")
	private String firstName;
	
	@JsonProperty("lastname")
	private String lastName;
	
	
	@JsonProperty("fullname")
	private String fullName;
	
	
	@JsonProperty("username")
	private String userName;
	
	
	@JsonProperty("password")
	private String password;
	
	@JsonProperty("email")
	private String emailAddress;
	
	@JsonProperty("status")
	private String statusValue;

	@JsonProperty("ContactDetails")
	ContactDetails contactDetails;
	
	@JsonProperty("Role")
	Role role;
	
	public class ContactDetails {
		@JsonProperty("editfirstname")
		private String editFirstName;
		
		@JsonProperty("editlastname")
		private String editLastName;
	}
	
	public class Role {
		@JsonProperty("adminusername")
		private String adminUsername;

		@JsonProperty("adminpassword")
		private String adminPassword;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFullName() {
		return fullName;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}

	public String getStatusValue() {
		return statusValue;
	}
	
	public String getEditFirstName() {
		return contactDetails.editFirstName;
	}

	public String getEditLastName() {
		return contactDetails.editLastName;
	}
	
	public String getAdminUsername() {
		return role.adminUsername;
	}

	public String getAdminPassword() {
		return role.adminPassword;
	}
	
}
