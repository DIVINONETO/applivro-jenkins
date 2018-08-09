package com.domain.applivro;



import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.domain.applivro.model.Profile;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplivroApplicationTests {	
	
	private String patternPostalCode = "[0-9]{5}[\\-]?[0-9]{3}";  /** apenas números com ou sem hifen */	
	private String patternEmail = "[a-zA-Z0-9_.]+@[a-zA-Z0-9]+.[a-zA-Z]{2,3}[.] {0,1}[a-zA-Z]+";
	private String patternName = "[\\s\\wÀ-ú0-9]+";
	private String patternDateOfBirth = "(0[1-9]|1[0-9]|2[0-9]|3[01]).(0[1-9]|1[012]).[0-9]{4}";
	private String patternIPAddress = "((^|\\.)((25[0-5])|(2[0-4]\\d)|(1\\d\\d)|([1-9]?\\d))){4}$";
		

	@Test	
	public void testNameValid() {
		String name = "Janaina Militão";
		Profile profile = new Profile();
		profile.setName(name);
		boolean resultName = name.matches(patternName);
		assertTrue(resultName);
	}
	
	@Test	
	public void testNameInvalid() {
		String name = "Janaina / Militão";
		Profile profile = new Profile();
		profile.setName(name);
		boolean resultName = name.matches(patternName);
		assertFalse(resultName);
	}
	
	@Test	
	public void testDateOfBirthValid() {
		Date dateOfBirth = new Date();
		Profile profile = new Profile();
		profile.setDateOfBirth(dateOfBirth);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String date = sdf.format(dateOfBirth);	
		boolean resultDateOfBirth = date.matches(patternDateOfBirth);
		assertTrue(resultDateOfBirth);
	}
	
	@Test	
	public void testDateOfBirthInvalid(){
		String dateOfBirth = "20180502";  
		Profile profile = new Profile();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			date = sdf.parse(dateOfBirth);
		} catch (ParseException e) {
			assertFalse(false);
			return;
		}
		
		profile.setDateOfBirth(date);
		boolean resultDateOfBirth = dateOfBirth.matches(patternDateOfBirth);
		assertTrue(resultDateOfBirth);
	}
	

	@Test	
	public void testIPAddressValid() {
		String ipAddress = "192.168.0.1";
		Profile profile = new Profile();
		profile.setIpAddress(ipAddress);
		boolean resultIPAddress = ipAddress.matches(patternIPAddress);
		assertTrue(resultIPAddress);
	}
	
	@Test	
	public void testIPAddressInvalid() {
		String ipAddress = "256.256.256.256";
		Profile profile = new Profile();
		profile.setIpAddress(ipAddress);
		boolean resultIPAddress = ipAddress.matches(patternIPAddress);
		assertFalse(resultIPAddress);
	}
			
	
	
	@Test
	public void testEmailValid() {
		String email = "janaina.militao@gmail.com";
		Profile profile = new Profile();
		profile.setEmail(email);
		
		boolean resultEmail = email.matches(patternEmail);		
		assertTrue(resultEmail);
	}	
	
	@Test
	public void testEmailInvalid() {
		String email = "www.janainamilitao.com.br";
		Profile profile = new Profile();
		profile.setEmail(email);
		
		boolean resultEmail = email.matches(patternEmail);		
		assertFalse(resultEmail);
	}	
	
	@Test
	public void testPostalCodeValid() {	
		
		String postalCode = "58123-000";
		
		Profile profile = new Profile();		
		profile.setPostalCode(postalCode);
		
		boolean resultPostalCode =  postalCode.matches(patternPostalCode);
		
		assertTrue(resultPostalCode);
	}
	
	@Test
	public void testPostalCodeInvalid() {
			
		String postalCode = "aaaaaa";
		
		Profile profile = new Profile();		
		profile.setPostalCode(postalCode);
		
		boolean resultPostalCode =  postalCode.matches(patternPostalCode);
		
		assertFalse(resultPostalCode);
	}

}
