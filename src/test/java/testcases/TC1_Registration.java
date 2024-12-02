package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.RegistrationPage;

/**
 * Test Case: Account Registration
 * 
 * Steps:
 * 1) Navigate to application URL
 * 2) Navigate to 'My Account' and click 'Register'
 * 3) Fill in registration details
 * 4) Agree to Privacy Policy and submit registration
 * 5) Validate confirmation message
 */
public class TC1_Registration extends BaseClass
{
	@Test(groups= {"Sanity","Master"})
	public void testUserRegistration()
	{
	//1) Navigate to application URL
	String applicationURL = properties.getProperty("appURL");
	driver.get(applicationURL);
	logger.info("Login to application URL: "+applicationURL);
	
	//2) Navigate to 'My Account' and click 'Register'
	HomePage homePage = new HomePage(driver);
	homePage.clickMyAccount();
	logger.info("Clicked on 'My Account' link.");
	
	RegistrationPage regstrationPage = homePage.clickRegister();
	logger.info("Clicked on 'Register' link, navigating to the Registration Page.");
	
	//3) Fill in registration details
	
	logger.info("Entering user details for registration...");
	regstrationPage.setFirstName(generateString().toUpperCase());
	logger.info("First Name set successfully.");
	
	regstrationPage.setLastName(generateString().toUpperCase());
	logger.info("Last Name set successfully.");
	
	String email = generateString() +"@gmail.com";
	regstrationPage.setEmail(email);
	logger.info("Email set successfully.");
	
	String phoneNumber = generateNumber();
	regstrationPage.setTelephone(phoneNumber);
	logger.info("Phone number set successfully.");
	
	String password = generateString()+generateNumber();
	regstrationPage.setPassword(password);
	regstrationPage.setConfirmPassword(password);
	logger.info("Password set successfully.");
	
	// 4) Agree to Privacy Policy and submit registration
	
	regstrationPage.setPrivacyPolicy();
	logger.info("Privacy policy get agreed");
	
	regstrationPage.clickContinue();
    logger.info("Clicked 'Continue' to submit the registration form.");

  //  5) Validate confirmation message
       String confirmationMessage = regstrationPage.getConfirmationMsg();
       Assert.assertEquals(confirmationMessage, "Your Account Has Been Created!");
       logger.info("Account registration successful. Confirmation message validated.");

    
	}
}
