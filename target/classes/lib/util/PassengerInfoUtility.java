package lib.util;

import java.awt.Robot;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.xpath.operations.Mod;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

//import com.thoughtworks.selenium.webdriven.commands.KeyEvent;

import lib.cisco.util.PropertiesFileReader;
import lib.cisco.util.Report;

public class PassengerInfoUtility {

	Report report=new Report();
	WrapperActionTest mondeeInc=new WrapperActionTest();
	ApplicationUtility appUtility=null;
	String ScreenshotName="";
	public WebDriver driver=null;
	public BufferedWriter writer=null;
	public AppTestDTO appDto=null;
	private final static Logger LOGGER = Logger.getLogger(PassengerInfoUtility.class.getName());
	private static final long MAX_WAIT_TIME = 0;

	PassengerInfoUtility(WebDriver driver,AppTestDTO appDto,WrapperActionTest mondeeInc, Report report,BufferedWriter writer,ApplicationUtility appUtility)
	{
		this.driver = driver;
		this.appDto = appDto;
		this.mondeeInc = mondeeInc;
		this.appUtility = appUtility;
		this.report = report;
		this.writer = writer;
	}


	public void waitInSeconds(int time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			LOGGER.log(Level.SEVERE, "Exception :",e);

		}
	}

	public static  WebDriver getDriver(String Browser){
		WebDriver driver = null;
		if(Browser.equalsIgnoreCase("Firefox")){
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("network.automatic-ntlm-auth.trusted-uris", "wwwin-sso-nprd.cisco.com");
			driver = new FirefoxDriver(profile);
		}
		return driver; 

		//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

	}

	/********************************************************************************************
	 *  
	 * @Function_Name :  logMessage
	 * @Description : This method is used write the steps into html report
	 * @version 1.0
	 * @param string 
	 ********************************************************************************************/

	public void logMessage(String strDescr,String strExpected,String Actual,String status)
	{
		try{
			ScreenshotName=appDto.getTestCaseName();
			report.logMessage(driver,writer,ScreenshotName,strDescr,strExpected,Actual, status);
		}
		catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception found in Initialization of ApplicationUtility class for report log message :",e);
		}
	}



	/********************************************************************************************
	 * @Function_Name :  exploreTrip_Enter_PassengerInfo
	 * @Description : RMA Simplification: 1st Serial Number should entered Successfully
	 * @author KalyanGumpa
	 * @param TestCase_Name - Name of the TestCase
	 * @param TestData - testData
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws Exception
	 * @return boolean
	 * @version 1.0
	 * ********************************************************************************************/

	public synchronized void exploreTrip_Enter_PassengerInfo(){

		try{
			Robot robot = new Robot();
			robot.keyPress(java.awt.event.KeyEvent.VK_PAGE_DOWN);
			robot.keyRelease(java.awt.event.KeyEvent.VK_PAGE_DOWN);
			mondeeInc.waitInSeconds(3);


			//Verify that Part Information Landing page is loaded Successfully ***** 
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("exploreTrip_Verify_Landing_PartInfo"))){
				String verfyPartInfoPage= mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Verify_Landing_PartInfo")).getText(); 
				System.out.println("Passerinfo:" + verfyPartInfoPage );
				if(verfyPartInfoPage.contains("Passenger Information")){
					logMessage("Verify that the Part Information Landing page is loaded", "Part Information Landing page should be loaded Successfully ", "You have Successfully Landed to: " + verfyPartInfoPage, "Passed");
					mondeeInc.waitInSeconds(2);
				}else {
					logMessage("Verify that the Part Information Landing page is loaded", "Part Information Landing page should be loaded Successfully ", "Part Information Landing page is NOT loaded Successfully", "Failed");
					Assert.assertTrue(false);
				}
			}

			//Verify & Enter Passenger's Title ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("exploreTrip_Select_Title"))){
				logMessage("Verify that the Passenger's Title drop box is avaialabe", "Passenger's Title drop box should be avaialabe Successfully ", "Passenger's Title drop box is avaialabe Successfully", "Passed");
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Select_Title")).click();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Select_Title")).sendKeys("Mr");
				logMessage("Verify that the Passenger's Title is selected", "Passenger's Title should be selected Successfully ", "Passenger's Title is selected Successfully", "Passed");
				mondeeInc.waitInSeconds(2);
			}else {
				logMessage("Verify that the Passenger's Title is selected", "Passenger's Title should be selected Successfully ", "Passenger's Title is NOT selected Successfully", "Failed");
				Assert.assertTrue(false);
			}

			//Verify & Enter First Name ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("exploreTrip_Entr_FirstName"))){
				logMessage("Verify that the Passenger's First Name is avaialabe", "Passenger's First Name should be avaialabe Successfully ", "First Name is avaialabe Successfully", "Passed");
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Entr_FirstName")).sendKeys("Kalyan");
				logMessage("Verify that the Passenger's First Name is entered", "Passenger's First Name should be entered Successfully ", "Passenger's First Name is entered Successfully", "Passed");
				mondeeInc.waitInSeconds(2);
			}else {
				logMessage("Verify that the Passenger's First Name is entered", "Passenger's First Name should be entered Successfully ", "Passenger's First Name is NOT entered Successfully", "Failed");
				Assert.assertTrue(false);
			}

			//Verify & Enter Last Name ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("exploreTrip_Entr_LastName"))){
				logMessage("Verify that the Passenger's Last Name is avaialabe", "Passenger's Last Name should be avaialabe Successfully ", "Last Name is avaialabe Successfully", "Passed");
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Entr_LastName")).sendKeys("Gumpa");
				logMessage("Verify that the Passenger's Last Name is entered", "Passenger's Last Name should be entered Successfully ", "Passenger's Last Name is entered Successfully", "Passed");
				mondeeInc.waitInSeconds(2);
			}else {
				logMessage("Verify that the Passenger's Last Name is entered", "Passenger's Last Name should be entered Successfully ", "Passenger's Last Name is NOT entered Successfully", "Failed");
				Assert.assertTrue(false);
			}

			//Verify & selected Gender ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("exploreTrip_Select_Gender"))){
				logMessage("Verify that the Passenger's Gender is avaialabe", "Passenger's Gender should be avaialabe Successfully ", "Gender is avaialabe Successfully", "Passed");
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Select_Gender")).click();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Select_Gender")).sendKeys("Female");
				logMessage("Verify that the Passenger's Gender is selected", "Passenger's Gender should be selected Successfully ", "Passenger's Gender is selected Successfully", "Passed");
				mondeeInc.waitInSeconds(2);
			}else {
				logMessage("Verify that the Passenger's Gender is selected", "Passenger's Gender should be selected Successfully ", "Passenger's Gender is NOT selected Successfully", "Failed");
				Assert.assertTrue(false);
			}

			//Verify & selected Passenger's DOB Month ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("exploreTrip_Select_Month"))){
				logMessage("Verify that the Passenger's DOB Month is avaialabe", "Passenger's DOB Month should be avaialabe Successfully ", "DOB Month is avaialabe Successfully", "Passed");
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Select_Month")).click();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Select_Month")).sendKeys("MAY");
				logMessage("Verify that the Passenger's DOB Month is selected", "Passenger's DOB Month should be selected Successfully ", "Passenger's DOB Month is selected Successfully", "Passed");
				mondeeInc.waitInSeconds(2);
			}else {
				logMessage("Verify that the Passenger's DOB Month is selected", "Passenger's DOB Month should be selected Successfully ", "Passenger's DOB Month is NOT selected Successfully", "Failed");
				Assert.assertTrue(false);
			}

			//Verify & selected Passenger's DOB Day ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("exploreTrip_Select_Date"))){
				logMessage("Verify that the Passenger's DOB Day is avaialabe", "Passenger's DOB Day should be avaialabe Successfully ", "DOB Day is avaialabe Successfully", "Passed");
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Select_Date")).click();
				mondeeInc.waitInSeconds(2);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Select_Date")).sendKeys("25");
				logMessage("Verify that the Passenger's DOB Day is selected", "Passenger's DOB Day should be selected Successfully ", "Passenger's DOB Day is selected Successfully", "Passed");
				mondeeInc.waitInSeconds(2);
			}else {
				logMessage("Verify that the Passenger's DOB Day is selected", "Passenger's DOB Day should be selected Successfully ", "Passenger's DOB Day is NOT selected Successfully", "Failed");
				Assert.assertTrue(false);
			}

			//Verify & selected Passenger's DOB Year ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("exploreTrip_Select_Year"))){
				logMessage("Verify that the Passenger's DOB Year is avaialabe", "Passenger's DOB Year should be avaialabe Successfully ", "DOB Year is avaialabe Successfully", "Passed");
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Select_Year")).click();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Select_Year")).sendKeys("1984");
				logMessage("Verify that the Passenger's DOB Year is selected", "Passenger's DOB Year should be selected Successfully ", "Passenger's DOB Year is selected Successfully", "Passed");
				mondeeInc.waitInSeconds(2);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Entr_LastName")).click();
				mondeeInc.waitInSeconds(1);
			}else {
				logMessage("Verify that the Passenger's DOB Year is selected", "Passenger's DOB Year should be selected Successfully ", "Passenger's DOB Year is NOT selected Successfully", "Failed");
				Assert.assertTrue(false);
			}

		}
		catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	/********************************************************************************************
	 * @Function_Name :  exploreTrip_Enter_PaymentInfo
	 * @Description : RMA Simplification: 1st Serial Number should entered Successfully
	 * @author KalyanGumpa
	 * @param TestCase_Name - Name of the TestCase
	 * @param TestData - testData
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws Exception
	 * @return boolean
	 * @version 1.0
	 * ********************************************************************************************/

	public synchronized void exploreTrip_Enter_PaymentInfo(){

		try{

			Robot robot = new Robot();
			robot.keyPress(java.awt.event.KeyEvent.VK_PAGE_DOWN);
			robot.keyRelease(java.awt.event.KeyEvent.VK_PAGE_DOWN);
			mondeeInc.waitInSeconds(3);


			//Verify & Select Card Type ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("exploreTrip_Select_CardType"))){
				logMessage("Verify that the Card Type is avaialabe", "Card Type should be avaialabe Successfully ", "Card Type is avaialabe Successfully", "Passed");
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Select_CardType")).click();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Select_CardType")).sendKeys("Visa");
				logMessage("Verify that the Card Type is selected", "Card Type should be selected Successfully ", "Card Type is selected Successfully", "Passed");
				mondeeInc.waitInSeconds(2);
			}else {
				logMessage("Verify that the Card Type is selected", "Card Type should be selected Successfully ", "Card Type is NOT selected Successfully", "Failed");
				Assert.assertTrue(false);
			}

			//Verify & Enter Card Holder's Name ***
			//driver.findElement(By.xpath(".//*[@id='CardHolder Name___input']")).sendKeys("Kalya");

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("exploreTrip_Entr_PayCardHolderName"))){
				logMessage("Verify that the Card Holder's Name is avaialabe", "Card Holder's Name should be avaialabe Successfully ", "Card Holder's Name is avaialabe Successfully", "Passed");
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Entr_PayCardHolderName")).sendKeys("Kalyan Gumpa");
				logMessage("Verify that the Card Holder's Name is entered", "Card Holder's Name should be entered Successfully ", "Card Holder's Name is entered Successfully", "Passed");
				mondeeInc.waitInSeconds(2);
			}else {
				logMessage("Verify that the Card Holder's Name is entered", "Card Holder's Name should be entered Successfully ", "Card Holder's Name is NOT entered Successfully", "Failed");
				Assert.assertTrue(false);
			}

			//Verify & Enter Card Number ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("exploreTrip_Entr_CardNumber"))){
				logMessage("Verify that the Card Number is avaialabe", "Card Number should be avaialabe Successfully ", "Card Number is avaialabe Successfully", "Passed");
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Entr_CardNumber")).sendKeys("1234567890123456");
				logMessage("Verify that the Card Number is selected", "Card Number should be selected Successfully ", "Card Number is selected Successfully", "Passed");
				mondeeInc.waitInSeconds(2);
			}else {
				logMessage("Verify that the Card Number is selected", "Card Number should be selected Successfully ", "Card Number is NOT selected Successfully", "Failed");
				Assert.assertTrue(false);
			}

			//Verify & selected Card Expiry Month ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("exploreTrip_Select_CardExpiryMonth"))){
				logMessage("Verify that the Card Expiry Month is avaialabe", "Card Expiry Month should be avaialabe Successfully ", "Card Expiry Month is avaialabe Successfully", "Passed");
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Select_CardExpiryMonth")).click();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Select_CardExpiryMonth")).sendKeys("MAY");
				logMessage("Verify that the Card Expiry Month is selected", "Card Expiry Month should be selected Successfully ", "Card Expiry Month is selected Successfully", "Passed");
				mondeeInc.waitInSeconds(2);
			}else {
				logMessage("Verify that the Card Expiry Month is selected", "Card Expiry Month should be selected Successfully ", "Card Expiry Month is NOT selected Successfully", "Failed");
				Assert.assertTrue(false);
			}

			//Verify & selected Card Expiry Year ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("exploreTrip_Select_CardExpiryYear"))){
				logMessage("Verify that the Card Expiry Year is avaialabe", "Card Expiry Year should be avaialabe Successfully ", "Card Expiry Year is avaialabe Successfully", "Passed");
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Select_CardExpiryYear")).click();
				mondeeInc.waitInSeconds(2);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Select_CardExpiryYear")).sendKeys("2019");
				logMessage("Verify that the Card Expiry Year is selected", "Card Expiry Year should be selected Successfully ", "Card Expiry Year is selected Successfully", "Passed");
				mondeeInc.waitInSeconds(2);
			}else {
				logMessage("Verify that the Card Expiry Year is selected", "Card Expiry Year should be selected Successfully ", "Card Expiry Year is NOT selected Successfully", "Failed");
				Assert.assertTrue(false);
			}

			//Verify & Enter Card Secruity Code ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("exploreTrip_Entr_CardPinCode"))){
				logMessage("Verify that the Card Secruity Code is avaialabe", "Card Secruity Code should be avaialabe Successfully ", "Card Secruity Code is avaialabe Successfully", "Passed");
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Entr_CardPinCode")).sendKeys("123");
				logMessage("Verify that the Card Secruity Code is entered", "Card Secruity Code should be entered Successfully ", "Card Secruity Code is entered Successfully", "Passed");
				mondeeInc.waitInSeconds(3);
			}else {
				logMessage("Verify that the Card Secruity Code is entered", "Card Secruity Code should be entered Successfully ", "Card Secruity Code is NOT entered Successfully", "Failed");
				Assert.assertTrue(false);
			}

		}
		catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	/********************************************************************************************
	 * @Function_Name :  exploreTrip_Enter_PaymentInfo
	 * @Description : RMA Simplification: 1st Serial Number should entered Successfully
	 * @author KalyanGumpa
	 * @param TestCase_Name - Name of the TestCase
	 * @param TestData - testData
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws Exception
	 * @return boolean
	 * @version 1.0
	 * ********************************************************************************************/

	public synchronized void exploreTrip_Enter_BillingInfo(){

		try{

			//Verify & Enter Card Holder's Name ***
//			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("exploreTrip_Entr_BCardHolderName"))){
//				logMessage("Verify that the Card Holder's Name is avaialabe", "Card Holder's Name should be avaialabe Successfully ", "Card Holder's Name is avaialabe Successfully", "Passed");
//				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Entr_BCardHolderName")).sendKeys("Kalyan Gumpa1");
//				logMessage("Verify that the Card Holder's Name is entered", "Card Holder's Name should be entered Successfully ", "Card Holder's Name is entered Successfully", "Passed");
//				mondeeInc.waitInSeconds(2);
//			}else {
//				logMessage("Verify that the Card Holder's Name is entered", "Card Holder's Name should be entered Successfully ", "Card Holder's Name is NOT entered Successfully", "Failed");
//				Assert.assertTrue(false);
//			}

			//Verify & Enter Passenger Address1 ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("exploreTrip_Entr_PassengerAddres1"))){
				logMessage("Verify that the Passenger Address1 is avaialabe", "Passenger Address1 should be avaialabe Successfully ", "Passenger Address1 is avaialabe Successfully", "Passed");
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Entr_PassengerAddres1")).sendKeys("300 Foster Blvd");
				logMessage("Verify that the Passenger Address1 is entered", "Passenger Address1 should be entered Successfully ", "Passenger Address1 is entered Successfully", "Passed");
				mondeeInc.waitInSeconds(2);
			}else {
				logMessage("Verify that the Passenger Address1 is entered", "Passenger Address1 should be entered Successfully ", "Passenger Address1 is NOT entered Successfully", "Failed");
				Assert.assertTrue(false);
			}

			//Verify & Enter Passenger Address2 ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("exploreTrip_Entr_PassengerAddres2"))){
				logMessage("Verify that the Passenger Address2 is avaialabe", "Passenger Address2 should be avaialabe Successfully ", "Passenger Address2 is avaialabe Successfully", "Passed");
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Entr_PassengerAddres2")).sendKeys("3rd Avanu");
				logMessage("Verify that the Passenger Address2 is entered", "Passenger Address2 should be entered Successfully ", "Passenger Address2 is entered Successfully", "Passed");
				mondeeInc.waitInSeconds(2);
			}else {
				logMessage("Verify that the Passenger Address2 is entered", "Passenger Address2 should be entered Successfully ", "Passenger Address2 is NOT entered Successfully", "Failed");
				Assert.assertTrue(false);
			}

			//Verify & selected Passenger Country ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("exploreTrip_Select_PassengerCountry"))){
				logMessage("Verify that the Passenger Country is avaialabe", "Passenger Country should be avaialabe Successfully ", "Passenger Country is avaialabe Successfully", "Passed");
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Select_PassengerCountry")).click();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Select_PassengerCountry")).sendKeys("United States");
				logMessage("Verify that the Passenger Country is selected", "Passenger Country should be selected Successfully ", "Passenger Country is selected Successfully", "Passed");
				mondeeInc.waitInSeconds(2);
			}else {
				logMessage("Verify that the Passenger Country is selected", "Passenger Country should be selected Successfully ", "Passenger Country is NOT selected Successfully", "Failed");
				Assert.assertTrue(false);
			}

			//Verify & selected Passenger State ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("exploreTrip_Select_PassengerState"))){
				logMessage("Verify that the Passenger State is avaialabe", "Passenger State should be avaialabe Successfully ", "Passenger State is avaialabe Successfully", "Passed");
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Select_PassengerState")).click();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Select_PassengerState")).sendKeys("CALIFORNIA");
				logMessage("Verify that the Passenger State is selected", "Passenger State should be selected Successfully ", "Passenger State is selected Successfully", "Passed");
				mondeeInc.waitInSeconds(2);
			}else {
				logMessage("Verify that the Passenger State is selected", "Passenger State should be selected Successfully ", "Passenger State is NOT selected Successfully", "Failed");
				Assert.assertTrue(false);
			}

			//Verify & Enter Passenger city ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("exploreTrip_Entr_PassengerCity"))){
				logMessage("Verify that the Passenger city is avaialabe", "Passenger city should be avaialabe Successfully ", "Passenger city is avaialabe Successfully", "Passed");
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Entr_PassengerCity")).sendKeys("Foster City");
				logMessage("Verify that the Passenger city is entered", "Passenger city should be entered Successfully ", "Passenger city is entered Successfully", "Passed");
				mondeeInc.waitInSeconds(2);
			}else {
				logMessage("Verify that the Passenger city is entered", "Passenger city should be entered Successfully ", "Passenger city is NOT entered Successfully", "Failed");
				Assert.assertTrue(false);
			}

			//Verify & Enter Passenger city zip code ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("exploreTrip_Entr_PassengerZipCode"))){
				logMessage("Verify that the Passenger city zip code is avaialabe", "Passenger city zip code should be avaialabe Successfully ", "Passenger city zip code is avaialabe Successfully", "Passed");
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Entr_PassengerZipCode")).sendKeys("94404");
				logMessage("Verify that the Passenger city zip code is entered", "Passenger city zip code should be entered Successfully ", "Passenger city zip code is entered Successfully", "Passed");
				mondeeInc.waitInSeconds(2);
			}else {
				logMessage("Verify that the Passenger city zip code is entered", "Passenger city zip code should be entered Successfully ", "Passenger city zip code is NOT entered Successfully", "Failed");
				Assert.assertTrue(false);
			}

		}
		catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	/********************************************************************************************
	 * @Function_Name :  exploreTrip_Enter_PaymentInfo
	 * @Description : RMA Simplification: 1st Serial Number should entered Successfully
	 * @author KalyanGumpa
	 * @param TestCase_Name - Name of the TestCase
	 * @param TestData - testData
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws Exception
	 * @return boolean
	 * @version 1.0
	 * ********************************************************************************************/

	public synchronized void exploreTrip_Enter_ContactInfo(){

		try{

			//Verify & Enter Passenger Phone # ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("exploreTrip_Entr_PassengerPhoneNum"))){
				logMessage("Verify that the Passenger Phone # is avaialabe", "Passenger Phone # should be avaialabe Successfully ", "Passenger Phone # is avaialabe Successfully", "Passed");
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Entr_PassengerPhoneNum")).sendKeys("6507894563");
				logMessage("Verify that the Passenger Phone # is entered", "Passenger Phone # should be entered Successfully ", "Passenger Phone # is entered Successfully", "Passed");
				mondeeInc.waitInSeconds(2);
			}else {
				logMessage("Verify that the Passenger Phone # is entered", "Passenger Phone # should be entered Successfully ", "Passenger Phone # is NOT entered Successfully", "Failed");
				Assert.assertTrue(false);
			}

			//Verify & Enter Passenger Alternate Phone # ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("exploreTrip_Entr_AlternatePassengerPhoneNum"))){
				logMessage("Verify that the Passenger Alternate Phone # is avaialabe", "Passenger Alternate Phone # should be avaialabe Successfully ", "Passenger Alternate Phone # is avaialabe Successfully", "Passed");
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Entr_AlternatePassengerPhoneNum")).sendKeys("7507894563");
				logMessage("Verify that the Passenger Alternate Phone # is entered", "Passenger Alternate Phone # should be entered Successfully ", "Passenger Alternate Phone # is entered Successfully", "Passed");
				mondeeInc.waitInSeconds(2);
			}else {
				logMessage("Verify that the Passenger Alternate Phone # is entered", "Passenger Alternate Phone # should be entered Successfully ", "Passenger Alternate Phone # is NOT entered Successfully", "Failed");
				Assert.assertTrue(false);
			}

			//Verify & Enter Passenger email ID ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("exploreTrip_Entr_PassengerEmail"))){
				logMessage("Verify that the Passenger email ID is avaialabe", "Passenger email ID should be avaialabe Successfully ", "Passenger email ID is avaialabe Successfully", "Passed");
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Entr_PassengerEmail")).sendKeys("kalyan.gumpa@gmail.com");
				logMessage("Verify that the Passenger email ID is entered", "Passenger email ID should be entered Successfully ", "Passenger email ID is entered Successfully", "Passed");
				mondeeInc.waitInSeconds(2);
			}else {
				logMessage("Verify that the Passenger email ID is entered", "Passenger email ID should be entered Successfully ", "Passenger email ID is NOT entered Successfully", "Failed");
				Assert.assertTrue(false);
			}

			//Verify & Enter Passenger Confirm email ID ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("exploreTrip_Entr_PassengerConfirmEmail"))){
				logMessage("Verify that the Passenger confirm email ID is avaialabe", "Passenger confirm email ID should be avaialabe Successfully ", "Passenger confirm email ID is avaialabe Successfully", "Passed");
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Entr_PassengerConfirmEmail")).sendKeys("kalyan.gumpa@gmail.com");
				logMessage("Verify that the Passenger confirm email ID is entered", "Passenger confirm email ID should be entered Successfully ", "Passenger confirm email ID is entered Successfully", "Passed");
				mondeeInc.waitInSeconds(2);
			}else {
				logMessage("Verify that the Passenger confirm email ID is entered", "Passenger confirm email ID should be entered Successfully ", "Passenger confirm email ID is NOT entered Successfully", "Failed");
				Assert.assertTrue(false);
			}

		}
		catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	/********************************************************************************************
	 * @Function_Name :  exploreTrip_Enter_PaymentInfo
	 * @Description : RMA Simplification: 1st Serial Number should entered Successfully
	 * @author KalyanGumpa
	 * @param TestCase_Name - Name of the TestCase
	 * @param TestData - testData
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws Exception
	 * @return boolean
	 * @version 1.0
	 * ********************************************************************************************/

	public synchronized void exploreTrip_Clk_ConfirmBooking(){

		try{

			//Verify & Click Confirm Booking ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("exploreTrip_Clk_ConfimBooking"))){
				logMessage("Verify that the Passenger Confirm Booking button is avaialabe", "Passenger Confirm Booking button should be avaialabe Successfully ", "Passenger Confirm Booking button is avaialabe Successfully", "Passed");
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("exploreTrip_Clk_ConfimBooking")).click();
				logMessage("Verify that the Passenger Confirm Booking button is entered", "Passenger Confirm Booking button should be entered Successfully ", "Passenger Confirm Booking button is entered Successfully", "Passed");
				mondeeInc.waitInSeconds(2);
			}else {
				logMessage("Verify that the Passenger Confirm Booking button is entered", "Passenger Confirm Booking button should be entered Successfully ", "Passenger Confirm button Booking is NOT entered Successfully", "Failed");
				Assert.assertTrue(false);
			}
		}
		catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}





}
