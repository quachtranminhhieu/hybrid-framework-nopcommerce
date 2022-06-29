package utilities;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;

public class DataUtil {
	private Faker faker;
	
	public DataUtil(){
		faker = new Faker();
	}
	
	public static DataUtil getData() {
		return new DataUtil();
	}
	
	public String getFirstName() {
		return faker.name().firstName();
	}
	
	public String getLastName() {
		return faker.name().lastName();
	}
	
	public String getFullName() {
		return faker.name().fullName();
	}
	
	public String getEmailAddress() {
		return faker.internet().emailAddress();
	}
	
	public String getUserName() {
		return faker.name().username();
	}
	
	public String getPassword() {
		return faker.internet().password(9, 10);
	}
	
	public String getGender() {
		return faker.demographic().sex();
	}
	
	public String getBirthDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM");
		return dateFormat.format(faker.date().birthday());
	}
	
	public String getPastDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM");
		return dateFormat.format(faker.date().past(365, TimeUnit.DAYS));
	}
	
	public String getFutureDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM");
		return dateFormat.format(faker.date().future(365, TimeUnit.DAYS));
	}
	
	public String getAddress() {
		return faker.address().streetAddress();
	}
	
	public String getCity() {
		return faker.address().city();
	}
	
	public String getState() {
		return faker.address().state();
	}
	
	public String getPostalCode() {
		return faker.address().zipCode();
	}
	
	public String getRandomNumber(int min, int max) {
		return String.valueOf(faker.number().numberBetween(min, max));
	}
	
	public String getRandomDigit() {
		return String.valueOf(faker.number().digit());
	}
	
	public String getRandomDouble(int maxNumberOfDecimals, int min, int max) {
		return String.valueOf(faker.number().randomDouble(maxNumberOfDecimals, min, max));
	}
}
