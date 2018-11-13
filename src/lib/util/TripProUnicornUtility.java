package lib.util;

import java.awt.RenderingHints.Key;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.interactions.touch.Flick;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.gargoylesoftware.htmlunit.javascript.host.Symbol;

import org.openqa.selenium.support.ui.ExpectedConditions;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;

//import com.thoughtworks.selenium.webdriven.commands.IsElementPresent;

import lib.cisco.util.PropertiesFileReader;
import lib.cisco.util.Report;

public class TripProUnicornUtility {

	Report report=new Report();
	WrapperActionTest mondeeInc=new WrapperActionTest();
	ApplicationUtility appUtility=null;
	String ScreenshotName="";
	public WebDriver driver=null;
	public BufferedWriter writer=null;
	public AppTestDTO appDto=null;
	private Object itinerarylist;
	private final static Logger LOGGER = Logger.getLogger(TripProUnicornUtility.class.getName());
	private static final long MAX_WAIT_TIME = 0;

	TripProUnicornUtility(WebDriver driver,AppTestDTO appDto,WrapperActionTest mondeeInc, Report report,BufferedWriter writer,ApplicationUtility appUtility)
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

	/*
	 ********************************************************************************************************************************************************
								@@@@@@@@@@@@@@@@@@@@@@@@@@  TripPro Unicorn  @@@@@@@@@@@@@@@@@@@@@@@@@@ 
	 ********************************************************************************************************************************************************/

	public synchronized void tripProUnicorn_menus(){

		try{
			//Verify that Username is clicked successfully ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("mondeeInc_Username"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("mondeeInc_Username")).sendKeys("sam@pge.com.drmsuat");
				logMessage("Verify that the Username is available", "Username should be available", "Username is available & entered Successfully", "Passed");
				mondeeInc.waitInSeconds(1);
			}else {
				logMessage("Verify that the Username is available", "Username should be available", "Username is NOT available & NOT entered Successfully", "Failed");
				Assert.assertTrue(false);
			}			

			//Verify that Password is clicked successfully ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("mondeeInc_Password"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("mondeeInc_Password")).sendKeys("Test!234");
				logMessage("Verify that the Password is available", "Password should be available", "Password is available & entered Successfully", "Passed");
				mondeeInc.waitInSeconds(2);
			}else {
				logMessage("Verify that the Password is available", "Password should be available", "Password is NOT available & NOT enteredSuccessfully", "Failed");
				Assert.assertTrue(false);
			}						

			//Verify that SignIn is clicked successfully ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("mondeeInc_Clk_Login"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("mondeeInc_Clk_Login")).click();
				mondeeInc.waitInSeconds(10);
				logMessage("Verify that SignIn is available & clicked", "SignIn is available & Clicked successfully","SignIn button is available & clicked successfully", "Passed");
				mondeeInc.waitInSeconds(2);
				logMessage("Verify that Dashboard is loaded", "Dashboard should be loaded successfully","Dashboard is loaded successfully", "Passed");

			}else {
				logMessage("Verify that the SignIn option is clicked", "SignIn option should be clicked Successfully ", "SignIn is NOT clicked Successfully", "Failed");
				Assert.assertTrue(false);
			}	

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	/********************************************************************************************
	 * @Function_Name :  TripProUnicorn enrollment
	 * @Description : TripProUnicorn login
	 * @author Kalyan Gumpa
	 * @param TestCase_Name - Name of the TestCase
	 * @param TestData - testData
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws Exception
	 * @return boolean
	 * @version 1.0
	 * ********************************************************************************************/

	public synchronized void tripProUnicorn_enrollment (){

		try{
			//Click on Get Started button ****
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Enrollment_Clk_GetStarted"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Enrollment_Clk_GetStarted")).click();
				logMessage("Verify that the Get Started is available & clicked", "Get Started should be available & clicked Sucessfully", "Get Started is available & clicked Successfully", "Passed");
				mondeeInc.waitInSeconds(3);
			}else {
				logMessage("Verify that the Get Started is available & clicked", "Get Started should be available & clicked Sucessfully", "Get Started is NOT available & NOT clicked Successfully", "Failed");
				Assert.assertTrue(false);
			}

			//Click on Sign up button ****
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Enrollment_Clk_Signup"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Enrollment_Clk_Signup")).click();
				logMessage("Verify that the  Sign up is available & clicked", " Sign up should be available & clicked Sucessfully", " Sign up is available & clicked Successfully", "Passed");
				mondeeInc.waitInSeconds(3);
			}else {
				logMessage("Verify that the  Sign up is available & clicked", " Sign up should be available & clicked Sucessfully", " Sign up is NOT available & NOT clicked Successfully", "Failed");
				Assert.assertTrue(false);
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	/********************************************************************************************
	 * @Function_Name :  TripProUnicorn login
	 * @Description : TripProUnicorn login
	 * @author Kalyan Gumpa
	 * @param TestCase_Name - Name of the TestCase
	 * @param TestData - testData
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws Exception
	 * @return boolean
	 * @version 1.0
	 * ********************************************************************************************/

	public synchronized void tripProUnicorn_login (){

		try{

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Login"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Login")).click();
				logMessage("Verify that the Login is available & clicked", "Login should be available & clicked Sucessfully", "Login is available & clicked Successfully", "Passed");
				mondeeInc.waitInSeconds(3);
			}else {
				logMessage("Verify that the Login is available & clicked", "Login should be available & clicked Sucessfully", "Login is NOT available & NOT clicked Successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Entr_Username"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Entr_Username")).clear();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Entr_Username")).sendKeys(appDto.getUserName());
				logMessage("Verify that the Username is available", "Username should be available", "Username is available & entered as: "+appDto.getUserName(), "Passed");
				mondeeInc.waitInSeconds(1);
			}else {
				logMessage("Verify that the Username is available", "Username should be available", "Username is NOT available & NOT entered Successfully", "Failed");
				Assert.assertTrue(false);
			}			

			//Verify that Password is clicked successfully ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Entr_Password"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Entr_Password")).clear();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Entr_Password")).sendKeys(appDto.getPassword());
				logMessage("Verify that the Password is available", "Password should be available", "Password is available & entered as: " + appDto.getPassword(), "Passed");
				mondeeInc.waitInSeconds(1);
			}else {
				logMessage("Verify that the Password is available", "Password should be available", "Password is NOT available & NOT enteredSuccessfully", "Failed");
				Assert.assertTrue(false);
			}						

			//Verify that SignIn is clicked successfully ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Clk_SignIn"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Clk_SignIn")).click();
				logMessage("Verify that SignIn is available & clicked", "Signin should be available & Clicked successfully","SignIn button is available & clicked successfully", "Passed");
				mondeeInc.waitInSeconds(20);
				driver.findElement(By.id("dashbaordBtnTab-dashboardData")).click();
				logMessage("Verify that Dashboard is loaded", "Dashboard should be loaded successfully","Dashboard is loaded successfully", "Passed");
			}else {
				logMessage("Verify that the SignIn option is clicked", "SignIn option should be clicked Successfully ", "SignIn is NOT clicked Successfully", "Failed");
				Assert.assertTrue(false);
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}



	public synchronized void tripProUnicorn_selectSabre(){

		try{
			//Verify & select Sabre option ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Menu_Sabre"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Menu_Sabre")).click();
				logMessage("Verify that select Sabre option is available & clicked", "Sabre option should be available & Clicked successfully","Sabre option is be available & clicked successfully", "Passed");
				mondeeInc.waitInSeconds(3);
			}else {
				logMessage("Verify that select Sabre option is available & clicked", "Sabre option should be available & Clicked successfully","Sabre option is NOT be available & NOT clicked successfully", "Failed");
				Assert.assertTrue(false);
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}


	public synchronized void tripProUnicorn_selectAmadeus(){

		try{

			//Verify & select Amadeus option ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Menu_Amadeus"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Menu_Amadeus")).click();
				logMessage("Verify that select Amadeus option is available & clicked", "Amadeus option should be available & Clicked successfully","Amadeus option is be available & clicked successfully", "Passed");
				mondeeInc.waitInSeconds(3);
			}else {
				logMessage("Verify that select Amadeus option is available & clicked", "Amadeus option should be available & Clicked successfully","Amadeus option is NOT be available & NOT clicked successfully", "Failed");
				Assert.assertTrue(false);
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}


	public synchronized void tripProUnicorn_selectFlights(){

		try{

			//Verify & select Flights option ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Menu_Flights"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Menu_Flights")).click();
				logMessage("Verify that select Flights option is available & clicked", "Flights option should be available & Clicked successfully","Flights option is be available & clicked successfully", "Passed");
				mondeeInc.waitInSeconds(3);
			}else {
				logMessage("Verify that select Flights option is available & clicked", "Flights option should be available & Clicked successfully","Flights option is NOT be available & NOT clicked successfully", "Failed");
				Assert.assertTrue(false);
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_selectGroupTravel(){

		try{

			//Verify & select Group Travel option ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_selectGroupTravel"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_selectGroupTravel")).click();
				logMessage("Verify that select Group Travel option is available & clicked", "Group Travel option should be available & Clicked successfully","Group Travel option is be available & clicked successfully", "Passed");
				mondeeInc.waitInSeconds(4);
			}else {
				logMessage("Verify that select Group Travel option is available & clicked", "Group Travel option should be available & Clicked successfully","Group Travel option is NOT be available & NOT clicked successfully", "Failed");
				Assert.assertTrue(false);
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}


	public synchronized void tripProUnicorn_selectHotles(){

		try{

			//Verify & select Hotels option ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Menu_Hotels"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Menu_Hotels")).click();
				logMessage("Verify that select Hotels option is available & clicked", "Hotels option should be available & Clicked successfully","Hotels option is be available & clicked successfully", "Passed");
				mondeeInc.waitInSeconds(3);
			}else {
				logMessage("Verify that select Hotels option is available & clicked", "Hotels option should be available & Clicked successfully","Hotels option is NOT be available & NOT clicked successfully", "Failed");
				Assert.assertTrue(false);
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}


	public synchronized void tripProUnicorn_selectUserManagement(){

		try{

			//Verify & select UserManagement option ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Menu_UserManagement"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Menu_UserManagement")).click();
				logMessage("Verify that select UserManagement option is available & clicked", "UserManagement option should be available & Clicked successfully","UserManagement option is be available & clicked successfully", "Passed");
				mondeeInc.waitInSeconds(3);
				driver.findElement(By.id("dashbaordBtnTab-dashboardData")).click();
				mondeeInc.waitInSeconds(2);
			}else {
				logMessage("Verify that select UserManagement option is available & clicked", "UserManagement option should be available & Clicked successfully","UserManagement option is NOT be available & NOT clicked successfully", "Failed");
				Assert.assertTrue(false);
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_selectTrips(){

		try{

			//Verify & select Trips option ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Menu_Trips"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Menu_Trips")).click();
				logMessage("Verify that select Trips option is available & clicked", "Trips option should be available & Clicked successfully","Trips option is be available & clicked successfully", "Passed");
				mondeeInc.waitInSeconds(4);
			}else {
				logMessage("Verify that select Trips option is available & clicked", "Trips option should be available & Clicked successfully","Trips option is NOT be available & NOT clicked successfully", "Failed");
				Assert.assertTrue(false);
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_selectFinance(){

		try{

			//Verify & select Finance option ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_selectFinance"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_selectFinance")).click();
				logMessage("Verify that select Finance option is available & clicked", "Finance option should be available & Clicked successfully","Finance option is be available & clicked successfully", "Passed");
				mondeeInc.waitInSeconds(4);
			}else {
				logMessage("Verify that select Finance option is available & clicked", "Finance option should be available & Clicked successfully","Finance option is NOT be available & NOT clicked successfully", "Failed");
				Assert.assertTrue(false);
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_selectFinanceDispute(){

		try{

			//Verify & select Finance Dispute option ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_selectFinanceCustomerStatement"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_selectFinanceCustomerStatement")).click();
				logMessage("Verify that select Finance Dispute option is available & clicked", "Finance Dispute option should be available & Clicked successfully","Finance Dispute option is be available & clicked successfully", "Passed");
				mondeeInc.waitInSeconds(4);
			}else {
				logMessage("Verify that select Finance Dispute option is available & clicked", "Finance Dispute option should be available & Clicked successfully","Finance Dispute option is NOT be available & NOT clicked successfully", "Failed");
				Assert.assertTrue(false);
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_selectFinanceCustomerStatement(){

		try{

			//Verify & select Customer Statement option ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_selectFinanceCustomerStatement"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_selectFinanceCustomerStatement")).click();
				logMessage("Verify that select Customer Statement option is available & clicked", "Customer Statement option should be available & Clicked successfully","Customer Statement option is be available & clicked successfully", "Passed");
				mondeeInc.waitInSeconds(4);
			}else {
				logMessage("Verify that select Customer Statement option is available & clicked", "Customer Statement option should be available & Clicked successfully","Customer Statement option is NOT be available & NOT clicked successfully", "Failed");
				Assert.assertTrue(false);
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_selectSites(){

		try{

			//Verify & select Sites option ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_selectSites"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_selectSites")).click();
				logMessage("Verify that select Sites option is available & clicked", "Sites option should be available & Clicked successfully","Sites option is be available & clicked successfully", "Passed");
				mondeeInc.waitInSeconds(4);
			}else {
				logMessage("Verify that select Sites option is available & clicked", "Sites option should be available & Clicked successfully","Sites option is NOT be available & NOT clicked successfully", "Failed");
				Assert.assertTrue(false);
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_selectBuild(){

		try{

			//Verify & select Build option ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_selectBuild"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_selectBuild")).click();
				logMessage("Verify that select Build option is available & clicked", "Build option should be available & Clicked successfully","Build option is be available & clicked successfully", "Passed");
				mondeeInc.waitInSeconds(4);
			}else {
				logMessage("Verify that select Build option is available & clicked", "Build option should be available & Clicked successfully","Build option is NOT be available & NOT clicked successfully", "Failed");
				Assert.assertTrue(false);
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_selectPages(){

		try{

			//Verify & select Pages option ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_selectPages"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_selectPages")).click();
				logMessage("Verify that select Pages option is available & clicked", "Pages option should be available & Clicked successfully","Pages option is be available & clicked successfully", "Passed");
				mondeeInc.waitInSeconds(4);
			}else {
				logMessage("Verify that select Pages option is available & clicked", "Pages option should be available & Clicked successfully","Pages option is NOT be available & NOT clicked successfully", "Failed");
				Assert.assertTrue(false);
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_selectFlightBooking(){

		try{

			//Verify & select Flight Booking option ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_selectFlightBooking"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_selectFlightBooking")).click();
				logMessage("Verify that select Flight Booking option is available & clicked", "Flight Booking option should be available & Clicked successfully","Flight Booking option is be available & clicked successfully", "Passed");
				mondeeInc.waitInSeconds(4);
			}else {
				logMessage("Verify that select Flight Booking option is available & clicked", "Flight Booking option should be available & Clicked successfully","Flight Booking option is NOT be available & NOT clicked successfully", "Failed");
				Assert.assertTrue(false);
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_selectMarkupManagement(){

		try{

			//Verify & select Markup Management option ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_selectMarkupManagement"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_selectMarkupManagement")).click();
				logMessage("Verify that select Markup Management option is available & clicked", "Markup Management option should be available & Clicked successfully","Markup Management option is be available & clicked successfully", "Passed");
				mondeeInc.waitInSeconds(4);
			}else {
				logMessage("Verify that select Markup Management option is available & clicked", "Markup Management option should be available & Clicked successfully","Markup Management option is NOT be available & NOT clicked successfully", "Failed");
				Assert.assertTrue(false);
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_selectHotelBookingReport(){

		try{

			//Verify & select Hotel Booking Report option ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_selectHotelBookingReport"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_selectHotelBookingReport")).click();
				logMessage("Verify that select Hotel Booking Report option is available & clicked", "Hotel Booking Report option should be available & Clicked successfully","Hotel Booking Report option is be available & clicked successfully", "Passed");
				mondeeInc.waitInSeconds(4);
			}else {
				logMessage("Verify that select Hotel Booking Report option is available & clicked", "Hotel Booking Report option should be available & Clicked successfully","Hotel Booking Report option is NOT be available & NOT clicked successfully", "Failed");
				Assert.assertTrue(false);
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_selectProfile(){

		try{

			//Verify & select Profile option ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_selectProfile"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_selectProfile")).click();
				logMessage("Verify that select Profile option is available & clicked", "Profile option should be available & Clicked successfully","Profile option is be available & clicked successfully", "Passed");
				mondeeInc.waitInSeconds(4);
			}else {
				logMessage("Verify that select Profile option is available & clicked", "Profile option should be available & Clicked successfully","Profile option is NOT be available & NOT clicked successfully", "Failed");
				Assert.assertTrue(false);
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_Signout(){

		try{

			//Verify & select Signout option ***

			driver.switchTo().defaultContent();
			mondeeInc.waitInSeconds(2);

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_ProfileSignout"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_ProfileSignout")).click();
				mondeeInc.waitInSeconds(3);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Signout")).click();
				logMessage("Verify that select Signout option is available & clicked", "Signout option should be available & Clicked successfully","Signout option is be available & clicked successfully", "Passed");
				mondeeInc.waitInSeconds(4);
			}else {
				logMessage("Verify that select Signout option is available & clicked", "Signout option should be available & Clicked successfully","Signout option is NOT be available & NOT clicked successfully", "Failed");
				Assert.assertTrue(false);
			}

			driver.quit();

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void mondeeInc_selectCustomerCare(){

		try{

			//Verify & select CustomerCare option ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Menu_CustomerCare"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Menu_CustomerCare")).click();
				logMessage("Verify that select CustomerCare option is available & clicked", "CustomerCare option should be available & Clicked successfully","CustomerCare option is be available & clicked successfully", "Passed");
				mondeeInc.waitInSeconds(3);
			}else {
				logMessage("Verify that select CustomerCare option is available & clicked", "CustomerCare option should be available & Clicked successfully","CustomerCare option is NOT be available & NOT clicked successfully", "Failed");
				Assert.assertTrue(false);
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}


	public synchronized void mondeeInc_selectDebitMemos(){

		try{
			//Verify & select DebitMemos option ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Menu_DebitMemos"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Menu_DebitMemos")).click();
				logMessage("Verify that select DebitMemos option is available & clicked", "DebitMemos option should be available & Clicked successfully","DebitMemos option is be available & clicked successfully", "Passed");
				mondeeInc.waitInSeconds(3);
			}else {
				logMessage("Verify that select DebitMemos option is available & clicked", "DebitMemos option should be available & Clicked successfully","DebitMemos option is NOT be available & NOT clicked successfully", "Failed");
				Assert.assertTrue(false);
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}


	public synchronized void tripProUnicorn_selectReports(){

		try{

			//Verify & select Reports option ***
			if (driver.findElement(By.xpath("//a[@id='menu-items-reports']/i/img")) != null){
				driver.findElement(By.xpath("//a[@id='menu-items-reports']/i/img")).click();
				logMessage("Verify that select Reports option is available & clicked", "Reports option should be available & Clicked successfully","Reports option is be available & clicked successfully", "Passed");
				mondeeInc.waitInSeconds(4);
			}else {
				logMessage("Verify that select Reports option is available & clicked", "Reports option should be available & Clicked successfully","Reports option is NOT be available & NOT clicked successfully", "Failed");
				Assert.assertTrue(false);
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_selectFlightBookingReports(){

		try{

			//Verify & select Flight Booking Reports option ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Menu_FlightBookingReports"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Menu_FlightBookingReports")).click();
				logMessage("Verify that select Flight Booking Reports option is available & clicked", "Flight Booking Reports option should be available & Clicked successfully","Flight Booking Reports option is be available & clicked successfully", "Passed");
				mondeeInc.waitInSeconds(5);
			}else {
				logMessage("Verify that select Flight Booking Reports option is available & clicked", "Flight Booking Reports option should be available & Clicked successfully","Flight Booking Reports option is NOT be available & NOT clicked successfully", "Failed");
				Assert.assertTrue(false);
			}

			appUtility.isAlertPresent();

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_Menu_HotelBookingReports(){

		try{

			//Verify & select Hotel Booking Reports option ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Menu_HotelBookingReports"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Menu_HotelBookingReports")).click();
				logMessage("Verify that select Hotel Booking Reports option is available & clicked", "Hotel Booking Reports option should be available & Clicked successfully","Hotel Booking Reports option is be available & clicked successfully", "Passed");
				mondeeInc.waitInSeconds(3);
			}else {
				logMessage("Verify that select Hotel Booking Reports option is available & clicked", "Hotel Booking Reports option should be available & Clicked successfully","Hotel Booking Reports option is NOT be available & NOT clicked successfully", "Failed");
				Assert.assertTrue(false);
			}

			appUtility.isAlertPresent();

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_Menu_BookingFailureReports(){

		try{

			//Verify & select Booking Failure Reports option ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Menu_BookingFailureReports"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Menu_BookingFailureReports")).click();
				logMessage("Verify that select Booking Failure Reports option is available & clicked", "Booking Failure Reports option should be available & Clicked successfully","Booking Failure Reports option is be available & clicked successfully", "Passed");
				mondeeInc.waitInSeconds(3);
			}else {
				logMessage("Verify that select Booking Failure Reports option is available & clicked", "Booking Failure Reports option should be available & Clicked successfully","Booking Failure Reports option is NOT be available & NOT clicked successfully", "Failed");
				Assert.assertTrue(false);
			}

			appUtility.isAlertPresent();

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}


	public synchronized void tripProUnicorn_selectMarketing(){

		try{

			//Verify & select Marketing option ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Menu_Marketing"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Menu_Marketing")).click();
				logMessage("Verify that select Marketing option is available & clicked", "Marketing option should be available & Clicked successfully","Marketing option is be available & clicked successfully", "Passed");
				mondeeInc.waitInSeconds(3);
			}else {
				logMessage("Verify that select Marketing option is available & clicked", "Marketing option should be available & Clicked successfully","Marketing option is NOT be available & NOT clicked successfully", "Failed");
				Assert.assertTrue(false);
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}


	public synchronized void tripProUnicorn_selectMarketingResources(){

		try{

			//Verify & select Resources Marketing option ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_selectMarketingResources"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_selectMarketingResources")).click();
				logMessage("Verify that select Resources Marketing option is available & clicked", "Resources Marketing option should be available & Clicked successfully"," Resources Marketing option is be available & clicked successfully", "Passed");
				mondeeInc.waitInSeconds(3);
			}else {
				logMessage("Verify that select Resources Marketing option is available & clicked", "Resources Marketing option should be available & Clicked successfully","Resources Marketing option is NOT be available & NOT clicked successfully", "Failed");
				Assert.assertTrue(false);
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_selectMarketingCampaign(){

		try{

			//Verify & select Campaign Marketing option ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_selectMarketingCampaign"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_selectMarketingCampaign")).click();
				logMessage("Verify that select Campaign Marketing option is available & clicked", "Campaign Marketing option should be available & Clicked successfully"," Campaign Marketing option is be available & clicked successfully", "Passed");
				mondeeInc.waitInSeconds(3);
			}else {
				logMessage("Verify that select Campaign Marketing option is available & clicked", "Campaign Marketing option should be available & Clicked successfully","Campaign Marketing option is NOT be available & NOT clicked successfully", "Failed");
				Assert.assertTrue(false);
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_selectMarketingContacts(){

		try{

			//Verify & select Contacts Marketing option ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_selectMarketingContacts"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_selectMarketingContacts")).click();
				logMessage("Verify that select Contacts Marketing option is available & clicked", "Contacts Marketing option should be available & Clicked successfully"," Contacts Marketing option is be available & clicked successfully", "Passed");
				mondeeInc.waitInSeconds(3);
			}else {
				logMessage("Verify that select Contacts Marketing option is available & clicked", "Contacts Marketing option should be available & Clicked successfully","Contacts Marketing option is NOT be available & NOT clicked successfully", "Failed");
				Assert.assertTrue(false);
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_selectMarketingEmailTemplates(){

		try{

			//Verify & select Email Templates Marketing option ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_selectMarketingEmailTemplates"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_selectMarketingEmailTemplates")).click();
				logMessage("Verify that select Email Templates Marketing option is available & clicked", "Email Templates Marketing option should be available & Clicked successfully"," Email Templates Marketing option is be available & clicked successfully", "Passed");
				mondeeInc.waitInSeconds(4);
			}else {
				logMessage("Verify that select Email Templates Marketing option is available & clicked", "Email Templates Marketing option should be available & Clicked successfully","Email Templates Marketing option is NOT be available & NOT clicked successfully", "Failed");
				Assert.assertTrue(false);
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_selectTutorials(){

		try{

			//Verify & select Tutorials option ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_selectTutorials"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_selectTutorials")).click();
				logMessage("Verify that select Tutorials option is available & clicked", "Tutorials option should be available & Clicked successfully"," Tutorials option is be available & clicked successfully", "Passed");
				mondeeInc.waitInSeconds(3);
			}else {
				logMessage("Verify that select Tutorials option is available & clicked", "Tutorials option should be available & Clicked successfully","Tutorials option is NOT be available & NOT clicked successfully", "Failed");
				Assert.assertTrue(false);
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_selectQuickStartGuide(){

		try{

			//Verify & select Quick Start Guide option ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_selectQuickStartGuide"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_selectQuickStartGuide")).click();
				logMessage("Verify that select Quick Start Guide option is available & clicked", "Quick Start Guide option should be available & Clicked successfully"," Quick Start Guide option is be available & clicked successfully", "Passed");
				mondeeInc.waitInSeconds(3);
			}else {
				logMessage("Verify that select Quick Start Guide option is available & clicked", "Quick Start Guide option should be available & Clicked successfully","Quick Start Guide option is NOT be available & NOT clicked successfully", "Failed");
				Assert.assertTrue(false);
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_searchSabre(){

		try{
			//Search in Sabre ***
			driver.switchTo().frame("SabreEmulator");
			mondeeInc.waitInSeconds(1);

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Sabre_Entr_search"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Sabre_Entr_search")).sendKeys("120DEcSFODEL");
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Sabre_Entr_search")).sendKeys(Keys.ENTER);
				logMessage("Verify that Sabre cammand is entered", "Sabre cammand should be entered & searched successfully","Sabre cammand is entred & searched successfully", "Passed");
				mondeeInc.waitInSeconds(3);
			}else {
				logMessage("Verify that Sabre cammand is entered", "Sabre cammand should be entered successfully","Sabre cammand is NOT entred & NOT searched successfully", "Failed");
				Assert.assertTrue(false);
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_Flight_Search(){

		try{

			//Search in Flights Search ***
			driver.switchTo().frame("Flights");
			mondeeInc.waitInSeconds(1);

			//Verify that One Way option is available & clicked  successfully ****
			if(mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_OneWay"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_OneWay")).click();
				mondeeInc.waitInSeconds(2);
				logMessage("Verify One Way is available & click", "One Way should be available & click","One Way is available & click successfully", "Passed");
			}else{
				logMessage("Verify One Way is available & click", "One Way should be available & click","One Way is NOT available & NOT click successfully", "Failed");
			}

			//Verify that Multi-city option is available & clicked  successfully ****
			if(mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_MultiCity"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_MultiCity")).click();
				mondeeInc.waitInSeconds(2);
				logMessage("Verify Multi-city is available & click", "Multi-city should be available & click","Multi-city is available & click successfully", "Passed");
			}else{
				logMessage("Verify Multi-city is available & click", "Multi-city should be available & click","Multi-city is NOT available & NOT click successfully", "Failed");
			}

			//Verify that Round Trip option is available & clicked  successfully ****
			if(mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Roundtrip"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Roundtrip")).click();
				mondeeInc.waitInSeconds(2);
				logMessage("Verify Round Trip is available & click", "Round Trip should be available & click","Round Trip is available & click successfully", "Passed");
			}else{
				logMessage("Verify Round Trip is available & click", "Round Trip should be available & click","Round Trip is NOT available & NOT click successfully", "Failed");
			}


			mondeeInc.waitInSeconds(2);
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Orgin"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Orgin")).clear();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Orgin")).sendKeys("SFO, San Francisco,US");
				logMessage("Verify that Origin is entered in Flight search", "Origin should be entred successfully","Origin is entred successfully", "Passed");
			}else {
				logMessage("Verify that Origin is entered in Flight search", "Origin should be entred successfully","Origin is NOT entred successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Destination"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Destination")).clear();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Destination")).sendKeys("HYD, Hyderabad,IN");
				logMessage("Verify that Destination is entered in Flight search", "Destination should be entred successfully","Destination is entred successfully", "Passed");
			}else {
				logMessage("Verify that Destination is entered in Flight search", "Destination should be entred successfully","Destination is NOT entred successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_StartDate"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_StartDate")).click();
				mondeeInc.waitInSeconds(2);
				driver.findElement(By.xpath("html/body/div[23]/div[1]/table/thead/tr[1]/th[3]")).click();
				mondeeInc.waitInSeconds(1);
				driver.findElement(By.xpath("html/body/div[23]/div[1]/table/thead/tr[1]/th[3]")).click();
				mondeeInc.waitInSeconds(1);
				driver.findElement(By.xpath("html/body/div[23]/div[1]/table/thead/tr[1]/th[3]")).click();
				mondeeInc.waitInSeconds(1);
				driver.findElement(By.xpath("html/body/div[23]/div[1]/table/tbody/tr[1]/td[4]")).click();
				mondeeInc.waitInSeconds(2);
				driver.findElement(By.xpath("html/body/div[23]/div[1]/table/tbody/tr[1]/td[2]")).click();
				logMessage("Verify that start date is selected in Flight search", "Start date should be selected successfully","Start date is selected successfully", "Passed");
				mondeeInc.waitInSeconds(1);
			}else {
				logMessage("Verify that start date is selected in Flight search", "Start date should be selected successfully","Start date is NOT selected successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Return"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Return")).click();
				mondeeInc.waitInSeconds(1);
				driver.findElement(By.xpath("html/body/div[23]/div[1]/table/tbody/tr[5]/td[5]")).click();
				logMessage("Verify that return date is selected in Flight search", "Return date should be selected successfully","Return date is selected successfully", "Passed");
			}else {
				logMessage("Verify that return date is selected in Flight search", "Return date should be selected successfully","Retrun date is NOT selected successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Travelers"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Travelers")).click();
				mondeeInc.waitInSeconds(2);
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("Business");
				logMessage("Verify that Business is selected search", "Business should be selected successfully","Business  class is selected successfully", "Passed");
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("Premium");
				logMessage("Verify that Premium is selected search", "Premium should be selected successfully","Premium  class is selected successfully", "Passed");
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("First");
				logMessage("Verify that First is selected search", "First should be selected successfully","First  class is selected successfully", "Passed");
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("Economy");
				logMessage("Verify that Economy is selected search", "Economy should be selected successfully","Economy  class is selected successfully", "Passed");
				mondeeInc.waitInSeconds(1);
			}else {
				logMessage("Verify that Class is selected search", "Class should be selected successfully","Class is NOT selected successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Search"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Search")).click();
				logMessage("Verify that Search button is clicked in Flight search", "Search button should be clicked successfully","Search button is clicked successfully", "Passed");
				mondeeInc.waitInSeconds(30);
				/*WebDriverWait wait = new WebDriverWait(driver, 2600);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("stopsCount_1")));*/
			}else {
				logMessage("Verify that Search button is clicked in Flight search", "Search button should be clicked successfully","Search button is NOT clicked successfully", "Failed");
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_FlightsFilters_1Stop"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_FlightsFilters_1Stop")).getText();
				System.out.println("\n");
				logMessage("Verify that One Stop filter is available", "One Stop filter is available successfully","Search results loaded in '30' Secs  successfully", "Passed");
				System.out.println("\n");
			}else {
				System.out.println("\n");
				logMessage("Verify that One Stop filter is available", "One Stop filter is available successfully","'FAILED':: Search results are 'NOT'  loaded in '30' Secs  successfully", "Failed");
				System.out.println("\n");
				Assert.assertTrue(false);
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}


	public synchronized void tripProUnicorn_Flight_MultipleSearch_1(){

		try{

			//Multiple Flight searchI ***
			driver.switchTo().frame("Flights");
			mondeeInc.waitInSeconds(1);
			logMessage("Verify 1st search & search resutlts", "1st search & search resutlts should be available","Executing 1st  search & search resutlts", "Passed");

			//Verify that One Way option is available & clicked  successfully ****
			if(mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_OneWay"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_OneWay")).click();
				mondeeInc.waitInSeconds(2);
				logMessage("Verify One Way is available & click", "One Way should be available & click","One Way is available & click successfully", "Passed");
			}else{
				logMessage("Verify One Way is available & click", "One Way should be available & click","One Way is NOT available & NOT click successfully", "Failed");
			}

			//Verify that Multi-city option is available & clicked  successfully ****
			if(mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_MultiCity"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_MultiCity")).click();
				mondeeInc.waitInSeconds(2);
				logMessage("Verify Multi-city is available & click", "Multi-city should be available & click","Multi-city is available & click successfully", "Passed");
			}else{
				logMessage("Verify Multi-city is available & click", "Multi-city should be available & click","Multi-city is NOT available & NOT click successfully", "Failed");
			}

			//Verify that Round Trip option is available & clicked  successfully ****
			if(mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Roundtrip"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Roundtrip")).click();
				mondeeInc.waitInSeconds(2);
				logMessage("Verify Round Trip is available & click", "Round Trip should be available & click","Round Trip is available & click successfully", "Passed");
			}else{
				logMessage("Verify Round Trip is available & click", "Round Trip should be available & click","Round Trip is NOT available & NOT click successfully", "Failed");
			}


			mondeeInc.waitInSeconds(2);
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Orgin"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Orgin")).clear();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Orgin")).sendKeys("SFO, San Francisco,US");
				logMessage("Verify that Origin is entered in Flight search", "Origin should be entred successfully","Origin is entred successfully", "Passed");
			}else {
				logMessage("Verify that Origin is entered in Flight search", "Origin should be entred successfully","Origin is NOT entred successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Destination"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Destination")).clear();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Destination")).sendKeys("HYD, Hyderabad,IN");
				logMessage("Verify that Destination is entered in Flight search", "Destination should be entred successfully","Destination is entred successfully", "Passed");
			}else {
				logMessage("Verify that Destination is entered in Flight search", "Destination should be entred successfully","Destination is NOT entred successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_StartDate"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_StartDate")).click();
				mondeeInc.waitInSeconds(2);
				driver.findElement(By.xpath("html/body/div[23]/div[1]/table/thead/tr[1]/th[3]")).click();
				mondeeInc.waitInSeconds(1);
				driver.findElement(By.xpath("html/body/div[23]/div[1]/table/thead/tr[1]/th[3]")).click();
				mondeeInc.waitInSeconds(1);
				driver.findElement(By.xpath("html/body/div[23]/div[1]/table/thead/tr[1]/th[3]")).click();
				mondeeInc.waitInSeconds(1);
				driver.findElement(By.xpath("html/body/div[23]/div[1]/table/tbody/tr[1]/td[4]")).click();
				mondeeInc.waitInSeconds(2);
				driver.findElement(By.xpath("html/body/div[23]/div[1]/table/tbody/tr[1]/td[2]")).click();
				logMessage("Verify that start date is selected in Flight search", "Start date should be selected successfully","Start date is selected successfully", "Passed");
				mondeeInc.waitInSeconds(1);
			}else {
				logMessage("Verify that start date is selected in Flight search", "Start date should be selected successfully","Start date is NOT selected successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Return"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Return")).click();
				mondeeInc.waitInSeconds(1);
				driver.findElement(By.xpath("html/body/div[23]/div[1]/table/tbody/tr[5]/td[5]")).click();
				logMessage("Verify that return date is selected in Flight search", "Return date should be selected successfully","Return date is selected successfully", "Passed");
			}else {
				logMessage("Verify that return date is selected in Flight search", "Return date should be selected successfully","Retrun date is NOT selected successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Travelers"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Travelers")).click();
				mondeeInc.waitInSeconds(2);
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("Business");
				logMessage("Verify that Business is selected search", "Business should be selected successfully","Business  class is selected successfully", "Passed");
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("Premium");
				logMessage("Verify that Premium is selected search", "Premium should be selected successfully","Premium  class is selected successfully", "Passed");
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("First");
				logMessage("Verify that First is selected search", "First should be selected successfully","First  class is selected successfully", "Passed");
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("Economy");
				logMessage("Verify that Economy is selected search", "Economy should be selected successfully","Economy  class is selected successfully", "Passed");
				mondeeInc.waitInSeconds(1);
			}else {
				logMessage("Verify that Class is selected search", "Class should be selected successfully","Class is NOT selected successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Search"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Search")).click();
				logMessage("Verify that Search button is clicked in Flight search", "Search button should be clicked successfully","Search button is clicked successfully", "Passed");
				mondeeInc.waitInSeconds(30);
				/*WebDriverWait wait = new WebDriverWait(driver, 2600);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("stopsCount_1")));*/
			}else {
				logMessage("Verify that Search button is clicked in Flight search", "Search button should be clicked successfully","Search button is NOT clicked successfully", "Failed");
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_FlightsFilters_1Stop"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_FlightsFilters_1Stop")).getText();
				System.out.println("\n");
				logMessage("Verify that One Stop filter is available", "One Stop filter is available successfully","1st Search results loaded in '30' Secs  successfully", "Passed");
				System.out.println("\n");
			}else {
				System.out.println("\n");
				logMessage("Verify that One Stop filter is available", "One Stop filter is available successfully","'FAILED':: 1st Search results are 'NOT'  loaded in '30' Secs  successfully", "Failed");
				System.out.println("\n");
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_Flight_MultipleSearch_2(){

		try{

			//Multiple Flight searchI ***
			mondeeInc.waitInSeconds(3);
			System.out.println("\n");
			logMessage("Verify 2nd search & search resutlts", "2nd search & search resutlts should be available","Executing 2nd search & search resutlts", "Passed");

			//Verify that One Way option is available & clicked  successfully ****
			if(mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_OneWay"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_OneWay")).click();
				mondeeInc.waitInSeconds(2);
				logMessage("Verify One Way is available & click", "One Way should be available & click","One Way is available & click successfully", "Passed");
			}else{
				logMessage("Verify One Way is available & click", "One Way should be available & click","One Way is NOT available & NOT click successfully", "Failed");
			}

			mondeeInc.waitInSeconds(2);
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Orgin"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Orgin")).clear();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Orgin")).sendKeys("SFO, San Francisco,US");
				logMessage("Verify that Origin is entered in Flight search", "Origin should be entred successfully","Origin is entred successfully", "Passed");
			}else {
				logMessage("Verify that Origin is entered in Flight search", "Origin should be entred successfully","Origin is NOT entred successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Destination"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Destination")).clear();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Destination")).sendKeys("LAX, Los Angeles,US");
				logMessage("Verify that Destination is entered in Flight search", "Destination should be entred successfully","Destination is entred successfully", "Passed");
			}else {
				logMessage("Verify that Destination is entered in Flight search", "Destination should be entred successfully","Destination is NOT entred successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_StartDate"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_StartDate")).click();
				mondeeInc.waitInSeconds(2);
				driver.findElement(By.xpath("html/body/div[23]/div[1]/table/thead/tr[1]/th[3]")).click();
				mondeeInc.waitInSeconds(1);
				driver.findElement(By.xpath("html/body/div[23]/div[1]/table/thead/tr[1]/th[3]")).click();
				mondeeInc.waitInSeconds(1);
				driver.findElement(By.xpath("html/body/div[23]/div[1]/table/thead/tr[1]/th[3]")).click();
				mondeeInc.waitInSeconds(1);
				driver.findElement(By.xpath("html/body/div[23]/div[1]/table/tbody/tr[1]/td[4]")).click();
				mondeeInc.waitInSeconds(2);
				//driver.findElement(By.xpath("html/body/div[23]/div[1]/table/tbody/tr[1]/td[2]")).click();
				logMessage("Verify that start date is selected in Flight search", "Start date should be selected successfully","Start date is selected successfully", "Passed");
				mondeeInc.waitInSeconds(1);
			}else {
				logMessage("Verify that start date is selected in Flight search", "Start date should be selected successfully","Start date is NOT selected successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Travelers"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Travelers")).click();
				mondeeInc.waitInSeconds(2);
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("Business");
				logMessage("Verify that Business is selected search", "Business should be selected successfully","Business  class is selected successfully", "Passed");
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("Premium");
				logMessage("Verify that Premium is selected search", "Premium should be selected successfully","Premium  class is selected successfully", "Passed");
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("First");
				logMessage("Verify that First is selected search", "First should be selected successfully","First  class is selected successfully", "Passed");
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("Economy");
				logMessage("Verify that Economy is selected search", "Economy should be selected successfully","Economy  class is selected successfully", "Passed");
				mondeeInc.waitInSeconds(1);
			}else {
				logMessage("Verify that Class is selected search", "Class should be selected successfully","Class is NOT selected successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Search"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Search")).click();
				logMessage("Verify that Search button is clicked in Flight search", "Search button should be clicked successfully","Search button is clicked successfully", "Passed");
				mondeeInc.waitInSeconds(30);
				/*WebDriverWait wait = new WebDriverWait(driver, 2600);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("stopsCount_1")));*/
			}else {
				logMessage("Verify that Search button is clicked in Flight search", "Search button should be clicked successfully","Search button is NOT clicked successfully", "Failed");
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_FlightsFilters_1Stop"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_FlightsFilters_1Stop")).getText();
				System.out.println("\n");
				logMessage("Verify that One Stop filter is available", "One Stop filter is available successfully","2nd Search results loaded in '30' Secs  successfully", "Passed");
				System.out.println("\n");
			}else {
				System.out.println("\n");
				logMessage("Verify that One Stop filter is available", "One Stop filter is available successfully","'FAILED':: 2nd Search results are 'NOT'  loaded in '30' Secs  successfully", "Failed");
				System.out.println("\n");
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_Flight_MultipleSearch_3(){

		try{

			//Multiple Flight searchI ***
			mondeeInc.waitInSeconds(2);
			System.out.println("\n");
			logMessage("Verify 3rd search & search resutlts", "3rd search & search resutlts should be available","Executing 3rd search & search resutlts", "Passed");

			//Verify that Multi-city option is available & clicked  successfully ****
			if(mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_MultiCity"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_MultiCity")).click();
				mondeeInc.waitInSeconds(2);
				logMessage("Verify Multi-city is available & click", "Multi-city should be available & click","Multi-city is available & click successfully", "Passed");
			}else{
				logMessage("Verify Multi-city is available & click", "Multi-city should be available & click","Multi-city is NOT available & NOT click successfully", "Failed");
			}

			mondeeInc.waitInSeconds(2);
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Orgin"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Orgin")).clear();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Orgin")).sendKeys("SFO, San Francisco,US");
				logMessage("Verify that Origin is entered in Flight search", "Origin should be entred successfully","Origin is entred successfully", "Passed");
			}else {
				logMessage("Verify that Origin is entered in Flight search", "Origin should be entred successfully","Origin is NOT entred successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Destination"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Destination")).clear();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Destination")).sendKeys("HYD, Hyderabad,IN");
				logMessage("Verify that Destination is entered in Flight search", "Destination should be entred successfully","Destination is entred successfully", "Passed");
			}else {
				logMessage("Verify that Destination is entered in Flight search", "Destination should be entred successfully","Destination is NOT entred successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_StartDate"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_StartDate")).click();
				mondeeInc.waitInSeconds(2);
				driver.findElement(By.xpath("html/body/div[23]/div[1]/table/thead/tr[1]/th[3]")).click();
				mondeeInc.waitInSeconds(1);
				driver.findElement(By.xpath("html/body/div[23]/div[1]/table/thead/tr[1]/th[3]")).click();
				mondeeInc.waitInSeconds(1);
				driver.findElement(By.xpath("html/body/div[23]/div[1]/table/thead/tr[1]/th[3]")).click();
				mondeeInc.waitInSeconds(1);
				driver.findElement(By.xpath("html/body/div[23]/div[1]/table/tbody/tr[1]/td[4]")).click();
				mondeeInc.waitInSeconds(2);
				//driver.findElement(By.xpath("html/body/div[23]/div[1]/table/tbody/tr[1]/td[2]")).click();
				logMessage("Verify that start date is selected in Flight search", "Start date should be selected successfully","Start date is selected successfully", "Passed");
				mondeeInc.waitInSeconds(1);
			}else {
				logMessage("Verify that start date is selected in Flight search", "Start date should be selected successfully","Start date is NOT selected successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Entr_MultiCityOrigin"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Entr_MultiCityOrigin")).clear();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Entr_MultiCityOrigin")).sendKeys("HYD, Hyderabad,IN");
				logMessage("Verify that Origin is entered in Flight search", "Origin should be entred successfully","Origin is entred successfully", "Passed");
			}else {
				logMessage("Verify that Origin is entered in Flight search", "Origin should be entred successfully","Origin is NOT entred successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Entr_MultiCity_Destination"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Entr_MultiCity_Destination")).clear();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Entr_MultiCity_Destination")).sendKeys("LON, London UK,GB");
				logMessage("Verify that Destination is entered in Flight search", "Destination should be entred successfully","Destination is entred successfully", "Passed");
			}else {
				logMessage("Verify that Destination is entered in Flight search", "Destination should be entred successfully","Destination is NOT entred successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Entr_MultiCity_ReturnDate"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Entr_MultiCity_ReturnDate")).click();
				mondeeInc.waitInSeconds(1);
				driver.findElement(By.xpath("html/body/div[23]/div[1]/table/tbody/tr[5]/td[5]")).click();
				logMessage("Verify that return date is selected in Flight search", "Return date should be selected successfully","Return date is selected successfully", "Passed");
			}else {
				logMessage("Verify that return date is selected in Flight search", "Return date should be selected successfully","Retrun date is NOT selected successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Travelers"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Travelers")).click();
				mondeeInc.waitInSeconds(2);
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("Business");
				logMessage("Verify that Business is selected search", "Business should be selected successfully","Business  class is selected successfully", "Passed");
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("Premium");
				logMessage("Verify that Premium is selected search", "Premium should be selected successfully","Premium  class is selected successfully", "Passed");
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("First");
				logMessage("Verify that First is selected search", "First should be selected successfully","First  class is selected successfully", "Passed");
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("Economy");
				logMessage("Verify that Economy is selected search", "Economy should be selected successfully","Economy  class is selected successfully", "Passed");
				mondeeInc.waitInSeconds(1);
			}else {
				logMessage("Verify that Class is selected search", "Class should be selected successfully","Class is NOT selected successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Search"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Search")).click();
				logMessage("Verify that Search button is clicked in Flight search", "Search button should be clicked successfully","Search button is clicked successfully", "Passed");
				mondeeInc.waitInSeconds(30);
				/*WebDriverWait wait = new WebDriverWait(driver, 2600);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("stopsCount_1")));*/
			}else {
				logMessage("Verify that Search button is clicked in Flight search", "Search button should be clicked successfully","Search button is NOT clicked successfully", "Failed");
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_FlightsFilters_1Stop"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_FlightsFilters_1Stop")).getText();
				System.out.println("\n");
				logMessage("Verify that One Stop filter is available", "One Stop filter is available successfully","3rd Search results loaded in '30' Secs  successfully", "Passed");
				System.out.println("\n");
			}else {
				System.out.println("\n");
				logMessage("Verify that One Stop filter is available", "One Stop filter is available successfully","'FAILED':: 3rd Search results are 'NOT'  loaded in '30' Secs  successfully", "Failed");
				System.out.println("\n");
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_Flight_MultipleSearch_4(){

		try{

			//Multiple Flight searchI ***
			mondeeInc.waitInSeconds(2);
			System.out.println("\n");
			logMessage("Verify 4th search & search resutlts", "4th search & search resutlts should be available","Executing 4th search & search resutlts", "Passed");

			//Verify that One Way option is available & clicked  successfully ****
			if(mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_OneWay"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_OneWay")).click();
				mondeeInc.waitInSeconds(2);
				logMessage("Verify One Way is available & click", "One Way should be available & click","One Way is available & click successfully", "Passed");
			}else{
				logMessage("Verify One Way is available & click", "One Way should be available & click","One Way is NOT available & NOT click successfully", "Failed");
			}

			//Verify that Multi-city option is available & clicked  successfully ****
			if(mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_MultiCity"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_MultiCity")).click();
				mondeeInc.waitInSeconds(2);
				logMessage("Verify Multi-city is available & click", "Multi-city should be available & click","Multi-city is available & click successfully", "Passed");
			}else{
				logMessage("Verify Multi-city is available & click", "Multi-city should be available & click","Multi-city is NOT available & NOT click successfully", "Failed");
			}

			//Verify that Round Trip option is available & clicked  successfully ****
			if(mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Roundtrip"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Roundtrip")).click();
				mondeeInc.waitInSeconds(2);
				logMessage("Verify Round Trip is available & click", "Round Trip should be available & click","Round Trip is available & click successfully", "Passed");
			}else{
				logMessage("Verify Round Trip is available & click", "Round Trip should be available & click","Round Trip is NOT available & NOT click successfully", "Failed");
			}

			mondeeInc.waitInSeconds(2);
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Orgin"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Orgin")).clear();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Orgin")).sendKeys("SFO, San Francisco,US");
				logMessage("Verify that Origin is entered in Flight search", "Origin should be entred successfully","Origin is entred successfully", "Passed");
			}else {
				logMessage("Verify that Origin is entered in Flight search", "Origin should be entred successfully","Origin is NOT entred successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Destination"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Destination")).clear();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Destination")).sendKeys("LAX, Los Angeles,US");
				logMessage("Verify that Destination is entered in Flight search", "Destination should be entred successfully","Destination is entred successfully", "Passed");
			}else {
				logMessage("Verify that Destination is entered in Flight search", "Destination should be entred successfully","Destination is NOT entred successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_StartDate"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_StartDate")).click();
				mondeeInc.waitInSeconds(2);
				logMessage("Verify that start date is selected in Flight search", "Start date should be selected successfully","Start date is selected successfully", "Passed");
				mondeeInc.waitInSeconds(1);
			}else {
				logMessage("Verify that start date is selected in Flight search", "Start date should be selected successfully","Start date is NOT selected successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Return"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Return")).click();
				mondeeInc.waitInSeconds(1);
				driver.findElement(By.xpath("html/body/div[23]/div[1]/table/tbody/tr[5]/td[5]")).click();
				logMessage("Verify that return date is selected in Flight search", "Return date should be selected successfully","Return date is selected successfully", "Passed");
			}else {
				logMessage("Verify that return date is selected in Flight search", "Return date should be selected successfully","Retrun date is NOT selected successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Travelers"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Travelers")).click();
				mondeeInc.waitInSeconds(2);
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("Business");
				logMessage("Verify that Business is selected search", "Business should be selected successfully","Business  class is selected successfully", "Passed");
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("Premium");
				logMessage("Verify that Premium is selected search", "Premium should be selected successfully","Premium  class is selected successfully", "Passed");
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("First");
				logMessage("Verify that First is selected search", "First should be selected successfully","First  class is selected successfully", "Passed");
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("Economy");
				logMessage("Verify that Economy is selected search", "Economy should be selected successfully","Economy  class is selected successfully", "Passed");
				mondeeInc.waitInSeconds(1);
			}else {
				logMessage("Verify that Class is selected search", "Class should be selected successfully","Class is NOT selected successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Search"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Search")).click();
				logMessage("Verify that Search button is clicked in Flight search", "Search button should be clicked successfully","Search button is clicked successfully", "Passed");
				mondeeInc.waitInSeconds(30);
				/*WebDriverWait wait = new WebDriverWait(driver, 2600);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("stopsCount_1")));*/
			}else {
				logMessage("Verify that Search button is clicked in Flight search", "Search button should be clicked successfully","Search button is NOT clicked successfully", "Failed");
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_FlightsFilters_1Stop"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_FlightsFilters_1Stop")).getText();
				System.out.println("\n");
				logMessage("Verify that One Stop filter is available", "One Stop filter is available successfully","4th Search results loaded in '30' Secs  successfully", "Passed");
				System.out.println("\n");
			}else {
				System.out.println("\n");
				logMessage("Verify that One Stop filter is available", "One Stop filter is available successfully","'FAILED':: 4th Search results are 'NOT'  loaded in '30' Secs  successfully", "Failed");
			}

			// Verifying alternate date option from search results******
			if(driver.getPageSource().contains(" Basic Economy")){
				logMessage("Verify that  Basic Economy in Flight search results", " Basic Economy should be clicked successfully","'' Basic Economy'' option is present successfully", "Passed");
				mondeeInc.waitInSeconds(3);
			}else{
				logMessage("Verify that  Basic Economy in Flight search results", " Basic Economy should be clicked successfully","'' Basic Economy'' option is NOT present for this OD pairs", "Failed");
				Assert.assertTrue(false);
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_Flight_MultipleSearch_5(){

		try{

			//Multiple Flight searchI ***
			mondeeInc.waitInSeconds(2);
			System.out.println("\n");
			logMessage("Verify 5th search & search resutlts", "5th search & search resutlts should be available","Executing 5th search & search resutlts", "Passed");

			//Verify that One Way option is available & clicked  successfully ****
			if(mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_OneWay"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_OneWay")).click();
				mondeeInc.waitInSeconds(2);
				logMessage("Verify One Way is available & click", "One Way should be available & click","One Way is available & click successfully", "Passed");
			}else{
				logMessage("Verify One Way is available & click", "One Way should be available & click","One Way is NOT available & NOT click successfully", "Failed");
			}

			//Verify that Multi-city option is available & clicked  successfully ****
			if(mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_MultiCity"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_MultiCity")).click();
				mondeeInc.waitInSeconds(2);
				logMessage("Verify Multi-city is available & click", "Multi-city should be available & click","Multi-city is available & click successfully", "Passed");
			}else{
				logMessage("Verify Multi-city is available & click", "Multi-city should be available & click","Multi-city is NOT available & NOT click successfully", "Failed");
			}

			//Verify that Round Trip option is available & clicked  successfully ****
			if(mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Roundtrip"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Roundtrip")).click();
				mondeeInc.waitInSeconds(2);
				logMessage("Verify Round Trip is available & click", "Round Trip should be available & click","Round Trip is available & click successfully", "Passed");
			}else{
				logMessage("Verify Round Trip is available & click", "Round Trip should be available & click","Round Trip is NOT available & NOT click successfully", "Failed");
			}


			mondeeInc.waitInSeconds(2);
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Orgin"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Orgin")).clear();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Orgin")).sendKeys("LON, London UK,GB");
				logMessage("Verify that Origin is entered in Flight search", "Origin should be entred successfully","Origin is entred successfully", "Passed");
			}else {
				logMessage("Verify that Origin is entered in Flight search", "Origin should be entred successfully","Origin is NOT entred successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Destination"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Destination")).clear();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Destination")).sendKeys("HYD, Hyderabad,IN");
				logMessage("Verify that Destination is entered in Flight search", "Destination should be entred successfully","Destination is entred successfully", "Passed");
			}else {
				logMessage("Verify that Destination is entered in Flight search", "Destination should be entred successfully","Destination is NOT entred successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_StartDate"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_StartDate")).click();
				mondeeInc.waitInSeconds(2);
				/*driver.findElement(By.xpath("html/body/div[23]/div[1]/table/thead/tr[1]/th[3]")).click();
				mondeeInc.waitInSeconds(1);
				driver.findElement(By.xpath("html/body/div[23]/div[1]/table/thead/tr[1]/th[3]")).click();
				mondeeInc.waitInSeconds(1);
				driver.findElement(By.xpath("html/body/div[23]/div[1]/table/thead/tr[1]/th[3]")).click();
				mondeeInc.waitInSeconds(1);
				driver.findElement(By.xpath("html/body/div[23]/div[1]/table/tbody/tr[1]/td[4]")).click();
				mondeeInc.waitInSeconds(2);
				driver.findElement(By.xpath("html/body/div[23]/div[1]/table/tbody/tr[1]/td[2]")).click();*/
				logMessage("Verify that start date is selected in Flight search", "Start date should be selected successfully","Start date is selected successfully", "Passed");
				mondeeInc.waitInSeconds(1);
			}else {
				logMessage("Verify that start date is selected in Flight search", "Start date should be selected successfully","Start date is NOT selected successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Return"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Return")).click();
				mondeeInc.waitInSeconds(1);
				driver.findElement(By.xpath("html/body/div[23]/div[1]/table/tbody/tr[5]/td[5]")).click();
				logMessage("Verify that return date is selected in Flight search", "Return date should be selected successfully","Return date is selected successfully", "Passed");
			}else {
				logMessage("Verify that return date is selected in Flight search", "Return date should be selected successfully","Retrun date is NOT selected successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Travelers"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Travelers")).click();
				mondeeInc.waitInSeconds(2);
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("Business");
				logMessage("Verify that Business is selected search", "Business should be selected successfully","Business  class is selected successfully", "Passed");
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("Premium");
				logMessage("Verify that Premium is selected search", "Premium should be selected successfully","Premium  class is selected successfully", "Passed");
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("First");
				logMessage("Verify that First is selected search", "First should be selected successfully","First  class is selected successfully", "Passed");
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("Economy");
				logMessage("Verify that Economy is selected search", "Economy should be selected successfully","Economy  class is selected successfully", "Passed");
				mondeeInc.waitInSeconds(1);
			}else {
				logMessage("Verify that Class is selected search", "Class should be selected successfully","Class is NOT selected successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Search"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Search")).click();
				logMessage("Verify that Search button is clicked in Flight search", "Search button should be clicked successfully","Search button is clicked successfully", "Passed");
				mondeeInc.waitInSeconds(30);
				/*WebDriverWait wait = new WebDriverWait(driver, 2600);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("stopsCount_1")));*/
			}else {
				logMessage("Verify that Search button is clicked in Flight search", "Search button should be clicked successfully","Search button is NOT clicked successfully", "Failed");
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_FlightsFilters_1Stop"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_FlightsFilters_1Stop")).getText();
				System.out.println("\n");
				logMessage("Verify that One Stop filter is available", "One Stop filter is available successfully","5th Search results loaded in '30' Secs  successfully", "Passed");
				System.out.println("\n");
			}else {
				System.out.println("\n");
				logMessage("Verify that One Stop filter is available", "One Stop filter is available successfully","'FAILED'::5th Search results are 'NOT'  loaded in '30' Secs  successfully", "Failed");
				System.out.println("\n");
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_Flight_MultipleSearch_6(){

		try{

			//Multiple Flight searchI ***
			mondeeInc.waitInSeconds(2);
			System.out.println("\n");
			logMessage("Verify 6th search & search resutlts", "6th search & search resutlts should be available","Executing 6th search & search resutlts", "Passed");


			//Verify that One Way option is available & clicked  successfully ****
			if(mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_OneWay"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_OneWay")).click();
				mondeeInc.waitInSeconds(2);
				logMessage("Verify One Way is available & click", "One Way should be available & click","One Way is available & click successfully", "Passed");
			}else{
				logMessage("Verify One Way is available & click", "One Way should be available & click","One Way is NOT available & NOT click successfully", "Failed");
			}

			//Verify that Multi-city option is available & clicked  successfully ****
			if(mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_MultiCity"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_MultiCity")).click();
				mondeeInc.waitInSeconds(2);
				logMessage("Verify Multi-city is available & click", "Multi-city should be available & click","Multi-city is available & click successfully", "Passed");
			}else{
				logMessage("Verify Multi-city is available & click", "Multi-city should be available & click","Multi-city is NOT available & NOT click successfully", "Failed");
			}

			//Verify that Round Trip option is available & clicked  successfully ****
			if(mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Roundtrip"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Roundtrip")).click();
				mondeeInc.waitInSeconds(2);
				logMessage("Verify Round Trip is available & click", "Round Trip should be available & click","Round Trip is available & click successfully", "Passed");
			}else{
				logMessage("Verify Round Trip is available & click", "Round Trip should be available & click","Round Trip is NOT available & NOT click successfully", "Failed");
			}


			mondeeInc.waitInSeconds(2);
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Orgin"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Orgin")).clear();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Orgin")).sendKeys("SFO, San Francisco,US");
				logMessage("Verify that Origin is entered in Flight search", "Origin should be entred successfully","Origin is entred successfully", "Passed");
			}else {
				logMessage("Verify that Origin is entered in Flight search", "Origin should be entred successfully","Origin is NOT entred successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Destination"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Destination")).clear();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Destination")).sendKeys("HYD, Hyderabad,IN");
				logMessage("Verify that Destination is entered in Flight search", "Destination should be entred successfully","Destination is entred successfully", "Passed");
			}else {
				logMessage("Verify that Destination is entered in Flight search", "Destination should be entred successfully","Destination is NOT entred successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_StartDate"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_StartDate")).click();
				mondeeInc.waitInSeconds(2);
				/*driver.findElement(By.xpath("html/body/div[23]/div[1]/table/thead/tr[1]/th[3]")).click();
				mondeeInc.waitInSeconds(1);
				driver.findElement(By.xpath("html/body/div[23]/div[1]/table/thead/tr[1]/th[3]")).click();
				mondeeInc.waitInSeconds(1);
				driver.findElement(By.xpath("html/body/div[23]/div[1]/table/thead/tr[1]/th[3]")).click();
				mondeeInc.waitInSeconds(1);
				driver.findElement(By.xpath("html/body/div[23]/div[1]/table/tbody/tr[1]/td[4]")).click();
				mondeeInc.waitInSeconds(2);
				driver.findElement(By.xpath("html/body/div[23]/div[1]/table/tbody/tr[1]/td[2]")).click();*/
				logMessage("Verify that start date is selected in Flight search", "Start date should be selected successfully","Start date is selected successfully", "Passed");
				mondeeInc.waitInSeconds(1);
			}else {
				logMessage("Verify that start date is selected in Flight search", "Start date should be selected successfully","Start date is NOT selected successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Return"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Return")).click();
				mondeeInc.waitInSeconds(1);
				driver.findElement(By.xpath("html/body/div[23]/div[1]/table/tbody/tr[5]/td[5]")).click();
				logMessage("Verify that return date is selected in Flight search", "Return date should be selected successfully","Return date is selected successfully", "Passed");
			}else {
				logMessage("Verify that return date is selected in Flight search", "Return date should be selected successfully","Retrun date is NOT selected successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Travelers"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Travelers")).click();
				mondeeInc.waitInSeconds(2);
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("Business");
				logMessage("Verify that Business is selected search", "Business should be selected successfully","Business  class is selected successfully", "Passed");
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("Premium");
				logMessage("Verify that Premium is selected search", "Premium should be selected successfully","Premium  class is selected successfully", "Passed");
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("First");
				logMessage("Verify that First is selected search", "First should be selected successfully","First  class is selected successfully", "Passed");
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("Economy");
				logMessage("Verify that Economy is selected search", "Economy should be selected successfully","Economy  class is selected successfully", "Passed");
				mondeeInc.waitInSeconds(1);
			}else {
				logMessage("Verify that Class is selected search", "Class should be selected successfully","Class is NOT selected successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Search"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Search")).click();
				logMessage("Verify that Search button is clicked in Flight search", "Search button should be clicked successfully","Search button is clicked successfully", "Passed");
				mondeeInc.waitInSeconds(30);
				/*WebDriverWait wait = new WebDriverWait(driver, 2600);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("stopsCount_1")));*/
			}else {
				logMessage("Verify that Search button is clicked in Flight search", "Search button should be clicked successfully","Search button is NOT clicked successfully", "Failed");
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_FlightsFilters_1Stop"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_FlightsFilters_1Stop")).getText();
				System.out.println("\n");
				logMessage("Verify that One Stop filter is available", "One Stop filter is available successfully","6th Search results loaded in '30' Secs  successfully", "Passed");
				System.out.println("\n");
			}else {
				System.out.println("\n");
				logMessage("Verify that One Stop filter is available", "One Stop filter is available successfully","'FAILED':: 6th Search results are 'NOT'  loaded in '30' Secs  successfully", "Failed");
				System.out.println("\n");
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_Flight_MultipleSearch_7(){

		try{

			//Multiple Flight searchI ***
			mondeeInc.waitInSeconds(2);
			System.out.println("\n");
			logMessage("Verify 7th search & search resutlts", "7th search & search resutlts should be available","Executing 7th search & search resutlts", "Passed");

			//Verify that One Way option is available & clicked  successfully ****
			if(mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_OneWay"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_OneWay")).click();
				mondeeInc.waitInSeconds(2);
				logMessage("Verify One Way is available & click", "One Way should be available & click","One Way is available & click successfully", "Passed");
			}else{
				logMessage("Verify One Way is available & click", "One Way should be available & click","One Way is NOT available & NOT click successfully", "Failed");
			}

			//Verify that Multi-city option is available & clicked  successfully ****
			if(mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_MultiCity"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_MultiCity")).click();
				mondeeInc.waitInSeconds(2);
				logMessage("Verify Multi-city is available & click", "Multi-city should be available & click","Multi-city is available & click successfully", "Passed");
			}else{
				logMessage("Verify Multi-city is available & click", "Multi-city should be available & click","Multi-city is NOT available & NOT click successfully", "Failed");
			}

			//Verify that Round Trip option is available & clicked  successfully ****
			if(mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Roundtrip"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Roundtrip")).click();
				mondeeInc.waitInSeconds(2);
				logMessage("Verify Round Trip is available & click", "Round Trip should be available & click","Round Trip is available & click successfully", "Passed");
			}else{
				logMessage("Verify Round Trip is available & click", "Round Trip should be available & click","Round Trip is NOT available & NOT click successfully", "Failed");
			}


			mondeeInc.waitInSeconds(2);
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Orgin"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Orgin")).clear();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Orgin")).sendKeys("BJS, BEIJING");
				logMessage("Verify that Origin is entered in Flight search", "Origin should be entred successfully","Origin is entred successfully", "Passed");
			}else {
				logMessage("Verify that Origin is entered in Flight search", "Origin should be entred successfully","Origin is NOT entred successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Destination"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Destination")).clear();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Destination")).sendKeys("HYD, Hyderabad,IN");
				logMessage("Verify that Destination is entered in Flight search", "Destination should be entred successfully","Destination is entred successfully", "Passed");
			}else {
				logMessage("Verify that Destination is entered in Flight search", "Destination should be entred successfully","Destination is NOT entred successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_StartDate"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_StartDate")).click();
				mondeeInc.waitInSeconds(2);
			}else {
				logMessage("Verify that start date is selected in Flight search", "Start date should be selected successfully","Start date is NOT selected successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Return"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Return")).click();
				mondeeInc.waitInSeconds(1);
				driver.findElement(By.xpath("html/body/div[23]/div[1]/table/tbody/tr[5]/td[5]")).click();
				logMessage("Verify that return date is selected in Flight search", "Return date should be selected successfully","Return date is selected successfully", "Passed");
			}else {
				logMessage("Verify that return date is selected in Flight search", "Return date should be selected successfully","Retrun date is NOT selected successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Travelers"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Travelers")).click();
				mondeeInc.waitInSeconds(2);
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("Business");
				logMessage("Verify that Business is selected search", "Business should be selected successfully","Business  class is selected successfully", "Passed");
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("Premium");
				logMessage("Verify that Premium is selected search", "Premium should be selected successfully","Premium  class is selected successfully", "Passed");
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("First");
				logMessage("Verify that First is selected search", "First should be selected successfully","First  class is selected successfully", "Passed");
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("Economy");
				logMessage("Verify that Economy is selected search", "Economy should be selected successfully","Economy  class is selected successfully", "Passed");
				mondeeInc.waitInSeconds(1);
			}else {
				logMessage("Verify that Class is selected search", "Class should be selected successfully","Class is NOT selected successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Search"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Search")).click();
				logMessage("Verify that Search button is clicked in Flight search", "Search button should be clicked successfully","Search button is clicked successfully", "Passed");
				mondeeInc.waitInSeconds(30);
				/*WebDriverWait wait = new WebDriverWait(driver, 2600);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("stopsCount_1")));*/
			}else {
				logMessage("Verify that Search button is clicked in Flight search", "Search button should be clicked successfully","Search button is NOT clicked successfully", "Failed");
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_FlightsFilters_1Stop"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_FlightsFilters_1Stop")).getText();
				System.out.println("\n");
				logMessage("Verify that One Stop filter is available", "One Stop filter is available successfully","Search results loaded in '30' Secs  successfully", "Passed");
				System.out.println("\n");
			}else {
				System.out.println("\n");
				logMessage("Verify that One Stop filter is available", "One Stop filter is available successfully","'FAILED':: 7th Search results are 'NOT'  loaded in '30' Secs  successfully", "Failed");
				System.out.println("\n");
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_Flight_MultipleSearch_8(){

		try{

			//Multiple Flight searchI ***
			mondeeInc.waitInSeconds(1);
			System.out.println("\n");
			logMessage("Verify 8th search & search resutlts", "8th search & search resutlts should be available","Executing 8th search & search resutlts", "Passed");

			//Verify that One Way option is available & clicked  successfully ****
			if(mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_OneWay"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_OneWay")).click();
				mondeeInc.waitInSeconds(2);
				logMessage("Verify One Way is available & click", "One Way should be available & click","One Way is available & click successfully", "Passed");
			}else{
				logMessage("Verify One Way is available & click", "One Way should be available & click","One Way is NOT available & NOT click successfully", "Failed");
			}

			//Verify that Multi-city option is available & clicked  successfully ****
			if(mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_MultiCity"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_MultiCity")).click();
				mondeeInc.waitInSeconds(2);
				logMessage("Verify Multi-city is available & click", "Multi-city should be available & click","Multi-city is available & click successfully", "Passed");
			}else{
				logMessage("Verify Multi-city is available & click", "Multi-city should be available & click","Multi-city is NOT available & NOT click successfully", "Failed");
			}

			//Verify that Round Trip option is available & clicked  successfully ****
			if(mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Roundtrip"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Roundtrip")).click();
				mondeeInc.waitInSeconds(2);
				logMessage("Verify Round Trip is available & click", "Round Trip should be available & click","Round Trip is available & click successfully", "Passed");
			}else{
				logMessage("Verify Round Trip is available & click", "Round Trip should be available & click","Round Trip is NOT available & NOT click successfully", "Failed");
			}


			mondeeInc.waitInSeconds(2);
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Orgin"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Orgin")).clear();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Orgin")).sendKeys("SFO, San Francisco,US");
				logMessage("Verify that Origin is entered in Flight search", "Origin should be entred successfully","Origin is entred successfully", "Passed");
			}else {
				logMessage("Verify that Origin is entered in Flight search", "Origin should be entred successfully","Origin is NOT entred successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Destination"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Destination")).clear();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Destination")).sendKeys("BJS, BEIJIN");
				logMessage("Verify that Destination is entered in Flight search", "Destination should be entred successfully","Destination is entred successfully", "Passed");
			}else {
				logMessage("Verify that Destination is entered in Flight search", "Destination should be entred successfully","Destination is NOT entred successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_StartDate"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_StartDate")).click();
				mondeeInc.waitInSeconds(2);
				logMessage("Verify that start date is selected in Flight search", "Start date should be selected successfully","Start date is selected successfully", "Passed");
				mondeeInc.waitInSeconds(1);
			}else {
				logMessage("Verify that start date is selected in Flight search", "Start date should be selected successfully","Start date is NOT selected successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Return"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Return")).click();
				mondeeInc.waitInSeconds(1);
				driver.findElement(By.xpath("html/body/div[23]/div[1]/table/tbody/tr[5]/td[5]")).click();
				logMessage("Verify that return date is selected in Flight search", "Return date should be selected successfully","Return date is selected successfully", "Passed");
			}else {
				logMessage("Verify that return date is selected in Flight search", "Return date should be selected successfully","Retrun date is NOT selected successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Travelers"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Travelers")).click();
				mondeeInc.waitInSeconds(2);
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("Business");
				logMessage("Verify that Business is selected search", "Business should be selected successfully","Business  class is selected successfully", "Passed");
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("Premium");
				logMessage("Verify that Premium is selected search", "Premium should be selected successfully","Premium  class is selected successfully", "Passed");
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("First");
				logMessage("Verify that First is selected search", "First should be selected successfully","First  class is selected successfully", "Passed");
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("Economy");
				logMessage("Verify that Economy is selected search", "Economy should be selected successfully","Economy  class is selected successfully", "Passed");
				mondeeInc.waitInSeconds(1);
			}else {
				logMessage("Verify that Class is selected search", "Class should be selected successfully","Class is NOT selected successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Search"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Search")).click();
				logMessage("Verify that Search button is clicked in Flight search", "Search button should be clicked successfully","Search button is clicked successfully", "Passed");
				mondeeInc.waitInSeconds(30);
				/*WebDriverWait wait = new WebDriverWait(driver, 2600);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("stopsCount_1")));*/
			}else {
				logMessage("Verify that Search button is clicked in Flight search", "Search button should be clicked successfully","Search button is NOT clicked successfully", "Failed");
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_FlightsFilters_1Stop"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_FlightsFilters_1Stop")).getText();
				System.out.println("\n");
				logMessage("Verify that One Stop filter is available", "One Stop filter is available successfully","Search results loaded in '30' Secs  successfully", "Passed");
				System.out.println("\n");
			}else {
				System.out.println("\n");
				logMessage("Verify that One Stop filter is available", "One Stop filter is available successfully","'FAILED':: 8th Search results are 'NOT'  loaded in '30' Secs  successfully", "Failed");
				System.out.println("\n");
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_Flight_MultipleSearch_9(){

		try{

			//Multiple Flight searchI ***
			mondeeInc.waitInSeconds(3);
			System.out.println("\n");
			logMessage("Verify 9th search & search resutlts", "9th search & search resutlts should be available","Executing 9th search & search resutlts", "Passed");


			//Verify that One Way option is available & clicked  successfully ****
			if(mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_OneWay"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_OneWay")).click();
				mondeeInc.waitInSeconds(2);
				logMessage("Verify One Way is available & click", "One Way should be available & click","One Way is available & click successfully", "Passed");
			}else{
				logMessage("Verify One Way is available & click", "One Way should be available & click","One Way is NOT available & NOT click successfully", "Failed");
			}

			//Verify that Multi-city option is available & clicked  successfully ****
			if(mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_MultiCity"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_MultiCity")).click();
				mondeeInc.waitInSeconds(2);
				logMessage("Verify Multi-city is available & click", "Multi-city should be available & click","Multi-city is available & click successfully", "Passed");
			}else{
				logMessage("Verify Multi-city is available & click", "Multi-city should be available & click","Multi-city is NOT available & NOT click successfully", "Failed");
			}

			//Verify that Round Trip option is available & clicked  successfully ****
			if(mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Roundtrip"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Roundtrip")).click();
				mondeeInc.waitInSeconds(2);
				logMessage("Verify Round Trip is available & click", "Round Trip should be available & click","Round Trip is available & click successfully", "Passed");
			}else{
				logMessage("Verify Round Trip is available & click", "Round Trip should be available & click","Round Trip is NOT available & NOT click successfully", "Failed");
			}


			mondeeInc.waitInSeconds(2);
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Orgin"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Orgin")).clear();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Orgin")).sendKeys("SFO, San Francisco,US");
				logMessage("Verify that Origin is entered in Flight search", "Origin should be entred successfully","Origin is entred successfully", "Passed");
			}else {
				logMessage("Verify that Origin is entered in Flight search", "Origin should be entred successfully","Origin is NOT entred successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Destination"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Destination")).clear();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Destination")).sendKeys("HYD, Hyderabad,IN");
				logMessage("Verify that Destination is entered in Flight search", "Destination should be entred successfully","Destination is entred successfully", "Passed");
			}else {
				logMessage("Verify that Destination is entered in Flight search", "Destination should be entred successfully","Destination is NOT entred successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_StartDate"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_StartDate")).click();
				logMessage("Verify that start date is selected in Flight search", "Start date should be selected successfully","Start date is selected successfully", "Passed");
				mondeeInc.waitInSeconds(1);
			}else {
				logMessage("Verify that start date is selected in Flight search", "Start date should be selected successfully","Start date is NOT selected successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Return"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Return")).click();
				mondeeInc.waitInSeconds(1);
				driver.findElement(By.xpath("html/body/div[23]/div[1]/table/tbody/tr[5]/td[5]")).click();
				logMessage("Verify that return date is selected in Flight search", "Return date should be selected successfully","Return date is selected successfully", "Passed");
			}else {
				logMessage("Verify that return date is selected in Flight search", "Return date should be selected successfully","Retrun date is NOT selected successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Travelers"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Travelers")).click();
				mondeeInc.waitInSeconds(2);
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("Business");
				logMessage("Verify that Business is selected search", "Business should be selected successfully","Business  class is selected successfully", "Passed");
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("Premium");
				logMessage("Verify that Premium is selected search", "Premium should be selected successfully","Premium  class is selected successfully", "Passed");
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("First");
				logMessage("Verify that First is selected search", "First should be selected successfully","First  class is selected successfully", "Passed");
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("Economy");
				logMessage("Verify that Economy is selected search", "Economy should be selected successfully","Economy  class is selected successfully", "Passed");
				mondeeInc.waitInSeconds(1);
			}else {
				logMessage("Verify that Class is selected search", "Class should be selected successfully","Class is NOT selected successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Search"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Search")).click();
				logMessage("Verify that Search button is clicked in Flight search", "Search button should be clicked successfully","Search button is clicked successfully", "Passed");
				mondeeInc.waitInSeconds(30);
				/*WebDriverWait wait = new WebDriverWait(driver, 2600);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("stopsCount_1")));*/
			}else {
				logMessage("Verify that Search button is clicked in Flight search", "Search button should be clicked successfully","Search button is NOT clicked successfully", "Failed");
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_FlightsFilters_1Stop"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_FlightsFilters_1Stop")).getText();
				System.out.println("\n");
				logMessage("Verify that One Stop filter is available", "One Stop filter is available successfully","Search results loaded in '30' Secs  successfully", "Passed");
				System.out.println("\n");
			}else {
				System.out.println("\n");
				logMessage("Verify that One Stop filter is available", "One Stop filter is available successfully","'FAILED':: 9th Search results are 'NOT'  loaded in '30' Secs  successfully", "Failed");
				System.out.println("\n");
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_Flight_MultipleSearch_10(){

		try{

			//Multiple Flight searchI ***
			mondeeInc.waitInSeconds(3);
			System.out.println("\n");
			logMessage("Verify 10th search & search resutlts", "10th search & search resutlts should be available","Executing 10th search & search resutlts", "Passed");

			//Verify that One Way option is available & clicked  successfully ****
			if(mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_OneWay"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_OneWay")).click();
				mondeeInc.waitInSeconds(2);
				logMessage("Verify One Way is available & click", "One Way should be available & click","One Way is available & click successfully", "Passed");
			}else{
				logMessage("Verify One Way is available & click", "One Way should be available & click","One Way is NOT available & NOT click successfully", "Failed");
			}

			//Verify that Multi-city option is available & clicked  successfully ****
			if(mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_MultiCity"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_MultiCity")).click();
				mondeeInc.waitInSeconds(2);
				logMessage("Verify Multi-city is available & click", "Multi-city should be available & click","Multi-city is available & click successfully", "Passed");
			}else{
				logMessage("Verify Multi-city is available & click", "Multi-city should be available & click","Multi-city is NOT available & NOT click successfully", "Failed");
			}

			//Verify that Round Trip option is available & clicked  successfully ****
			if(mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Roundtrip"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Roundtrip")).click();
				mondeeInc.waitInSeconds(2);
				logMessage("Verify Round Trip is available & click", "Round Trip should be available & click","Round Trip is available & click successfully", "Passed");
			}else{
				logMessage("Verify Round Trip is available & click", "Round Trip should be available & click","Round Trip is NOT available & NOT click successfully", "Failed");
			}


			mondeeInc.waitInSeconds(2);
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Orgin"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Orgin")).clear();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Orgin")).sendKeys("ATH, Athens,GR");
				logMessage("Verify that Origin is entered in Flight search", "Origin should be entred successfully","Origin is entred successfully", "Passed");
			}else {
				logMessage("Verify that Origin is entered in Flight search", "Origin should be entred successfully","Origin is NOT entred successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Destination"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Destination")).clear();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Destination")).sendKeys("SFO, San Francisco,US");
				logMessage("Verify that Destination is entered in Flight search", "Destination should be entred successfully","Destination is entred successfully", "Passed");
			}else {
				logMessage("Verify that Destination is entered in Flight search", "Destination should be entred successfully","Destination is NOT entred successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_StartDate"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_StartDate")).click();
				mondeeInc.waitInSeconds(2);
				logMessage("Verify that start date is selected in Flight search", "Start date should be selected successfully","Start date is selected successfully", "Passed");
				mondeeInc.waitInSeconds(1);
			}else {
				logMessage("Verify that start date is selected in Flight search", "Start date should be selected successfully","Start date is NOT selected successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Return"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Return")).click();
				mondeeInc.waitInSeconds(1);
				driver.findElement(By.xpath("html/body/div[23]/div[1]/table/tbody/tr[5]/td[5]")).click();
				logMessage("Verify that return date is selected in Flight search", "Return date should be selected successfully","Return date is selected successfully", "Passed");
			}else {
				logMessage("Verify that return date is selected in Flight search", "Return date should be selected successfully","Retrun date is NOT selected successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Travelers"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Travelers")).click();
				mondeeInc.waitInSeconds(2);
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("Business");
				logMessage("Verify that Business is selected search", "Business should be selected successfully","Business  class is selected successfully", "Passed");
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("Premium");
				logMessage("Verify that Premium is selected search", "Premium should be selected successfully","Premium  class is selected successfully", "Passed");
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("First");
				logMessage("Verify that First is selected search", "First should be selected successfully","First  class is selected successfully", "Passed");
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("Economy");
				logMessage("Verify that Economy is selected search", "Economy should be selected successfully","Economy  class is selected successfully", "Passed");
				mondeeInc.waitInSeconds(1);
			}else {
				logMessage("Verify that Class is selected search", "Class should be selected successfully","Class is NOT selected successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Search"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Search")).click();
				logMessage("Verify that Search button is clicked in Flight search", "Search button should be clicked successfully","Search button is clicked successfully", "Passed");
				mondeeInc.waitInSeconds(30);
				/*WebDriverWait wait = new WebDriverWait(driver, 2600);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("stopsCount_1")));*/
			}else {
				logMessage("Verify that Search button is clicked in Flight search", "Search button should be clicked successfully","Search button is NOT clicked successfully", "Failed");
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_FlightsFilters_1Stop"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_FlightsFilters_1Stop")).getText();
				System.out.println("\n");
				logMessage("Verify that One Stop filter is available", "One Stop filter is available successfully","Search results loaded in '30' Secs  successfully", "Passed");
				System.out.println("\n");
			}else {
				System.out.println("\n");
				logMessage("Verify that One Stop filter is available", "One Stop filter is available successfully","'FAILED':: Search results are 'NOT'  loaded in '30' Secs  successfully", "Failed");
				System.out.println("\n");
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_Flight_MultipleSearch_11(){

		try{

			//Multiple Flight searchI ***
			driver.switchTo().frame("Flights");
			mondeeInc.waitInSeconds(1);

			//Verify that One Way option is available & clicked  successfully ****
			if(mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_OneWay"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_OneWay")).click();
				mondeeInc.waitInSeconds(2);
				logMessage("Verify One Way is available & click", "One Way should be available & click","One Way is available & click successfully", "Passed");
			}else{
				logMessage("Verify One Way is available & click", "One Way should be available & click","One Way is NOT available & NOT click successfully", "Failed");
			}

			//Verify that Multi-city option is available & clicked  successfully ****
			if(mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_MultiCity"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_MultiCity")).click();
				mondeeInc.waitInSeconds(2);
				logMessage("Verify Multi-city is available & click", "Multi-city should be available & click","Multi-city is available & click successfully", "Passed");
			}else{
				logMessage("Verify Multi-city is available & click", "Multi-city should be available & click","Multi-city is NOT available & NOT click successfully", "Failed");
			}

			//Verify that Round Trip option is available & clicked  successfully ****
			if(mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Roundtrip"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Roundtrip")).click();
				mondeeInc.waitInSeconds(2);
				logMessage("Verify Round Trip is available & click", "Round Trip should be available & click","Round Trip is available & click successfully", "Passed");
			}else{
				logMessage("Verify Round Trip is available & click", "Round Trip should be available & click","Round Trip is NOT available & NOT click successfully", "Failed");
			}


			mondeeInc.waitInSeconds(2);
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Orgin"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Orgin")).clear();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Orgin")).sendKeys("SFO, San Francisco,US");
				logMessage("Verify that Origin is entered in Flight search", "Origin should be entred successfully","Origin is entred successfully", "Passed");
			}else {
				logMessage("Verify that Origin is entered in Flight search", "Origin should be entred successfully","Origin is NOT entred successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Destination"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Destination")).clear();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Destination")).sendKeys("HYD, Hyderabad,IN");
				logMessage("Verify that Destination is entered in Flight search", "Destination should be entred successfully","Destination is entred successfully", "Passed");
			}else {
				logMessage("Verify that Destination is entered in Flight search", "Destination should be entred successfully","Destination is NOT entred successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_StartDate"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_StartDate")).click();
				logMessage("Verify that start date is selected in Flight search", "Start date should be selected successfully","Start date is selected successfully", "Passed");
				mondeeInc.waitInSeconds(1);
			}else {
				logMessage("Verify that start date is selected in Flight search", "Start date should be selected successfully","Start date is NOT selected successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Return"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Return")).click();
				mondeeInc.waitInSeconds(1);
				driver.findElement(By.xpath("html/body/div[23]/div[1]/table/tbody/tr[5]/td[5]")).click();
				logMessage("Verify that return date is selected in Flight search", "Return date should be selected successfully","Return date is selected successfully", "Passed");
			}else {
				logMessage("Verify that return date is selected in Flight search", "Return date should be selected successfully","Retrun date is NOT selected successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Travelers"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Travelers")).click();
				mondeeInc.waitInSeconds(2);
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("Business");
				logMessage("Verify that Business is selected search", "Business should be selected successfully","Business  class is selected successfully", "Passed");
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("Premium");
				logMessage("Verify that Premium is selected search", "Premium should be selected successfully","Premium  class is selected successfully", "Passed");
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("First");
				logMessage("Verify that First is selected search", "First should be selected successfully","First  class is selected successfully", "Passed");
				driver.findElement(By.xpath(".//*[@id='businessClass']")).sendKeys("Economy");
				logMessage("Verify that Economy is selected search", "Economy should be selected successfully","Economy  class is selected successfully", "Passed");
				mondeeInc.waitInSeconds(1);
			}else {
				logMessage("Verify that Class is selected search", "Class should be selected successfully","Class is NOT selected successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Search"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Search")).click();
				logMessage("Verify that Search button is clicked in Flight search", "Search button should be clicked successfully","Search button is clicked successfully", "Passed");
				mondeeInc.waitInSeconds(30);
				/*WebDriverWait wait = new WebDriverWait(driver, 2600);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("stopsCount_1")));*/
			}else {
				logMessage("Verify that Search button is clicked in Flight search", "Search button should be clicked successfully","Search button is NOT clicked successfully", "Failed");
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_FlightsFilters_1Stop"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_FlightsFilters_1Stop")).getText();
				System.out.println("\n");
				logMessage("Verify that One Stop filter is available", "One Stop filter is available successfully","Search results loaded in '30' Secs  successfully", "Passed");
				System.out.println("\n");
			}else {
				System.out.println("\n");
				logMessage("Verify that One Stop filter is available", "One Stop filter is available successfully","'FAILED':: Search results are 'NOT'  loaded in '30' Secs  successfully", "Failed");
				System.out.println("\n");
				Assert.assertTrue(false);
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}



	/********************************************************************************************
	 * @Function_Name :  Verify sending agent markup through email Id
	 * @Description : Verify sending agent markup through email Id
	 * @author Kalyan Gumpa
	 * @param TestCase_Name - Name of the TestCase
	 * @param TestData - testData
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws Exception
	 * @return boolean
	 * @version 1.0
	 * ********************************************************************************************/


	public synchronized void tripProUnicorn_SendAgentMarkupThroughEmailID(){

		try{
			
			//Verify E-mail available & clicked successfully ****
			boolean nearbyAirports = driver.findElement(By.id("tripProUnicorn_SendAgentMarkupThroughEmailID_ClickEmailIcon")).isDisplayed();
			if(nearbyAirports==true) {
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_SendAgentMarkupThroughEmailID_ClickEmailIcon")).click();
				mondeeInc.waitInSeconds(1);
				logMessage("Verify are E-mail icon available & clicked", " E-mail icon is available & clicked successfully","''E-mail' icon is available & clicked successfully", "Passed");
			}else {
				logMessage("Verify are E-mail icon available & clicked", " E-mail icon is available & clicked successfully","'FAILED':: 'E-mail' icon is NOT available & NOT clicked successfully", "Failed");
				Assert.assertTrue(false);
			}

			// Enter Email id & Agent Markup ****
			if(mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_SendAgentMarkupThroughEmailID_Enter"))){
				String flightSight =  mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_SendAgentMarkupThroughEmailID")).getText();
				mondeeInc.waitInSeconds(3);
				logMessage("Verify Fare sight loaded from search results", "Fare sight should be loaded from search results successfully","Fare sight are loaded from search results successfully", "Passed");
			}else{
				logMessage("Verify Total Itinerary loaded from search results", "Total # Itinerary should be loaded from search results successfully","Fare sight are NOT loaded from search results successfully", "Passed");
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}
	
	public synchronized void tripProUnicorn_Flight_verifysearchResultsData(){

		try{

			// Verifying Near By Airports option from search results******
			boolean nearbyAirports = driver.findElement(By.id("sortByNearBy")).isDisplayed();
			if(nearbyAirports==true) {
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_NearbyAirports")).click();
				logMessage("Verify Near By Airports loaded from search results", "Near By Airports should be loaded from search results successfully","''Near By Airports'' are available & clicked successfully", "Passed");
			}else {
				logMessage("Verify Near By Airports loaded from search results", "Near By Airports should be loaded from search results successfully","''Near By Airports'' are NOT available", "Passed");

			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}






	public synchronized void tripProUnicorn_Flight_selectItinerary(){

		try{

			//Select in Itinerary from search results ***
			List<WebElement> list = driver.findElements(By.xpath(".//*[contains(@id, 'bookNow_')]"));
			System.out.println("List Size:" +list.size());
			list.get(1).click();
			mondeeInc.waitInSeconds(4);

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}


	public synchronized void tripProUnicorn_Flight_passengerInfo(){

		try{

			//Passenger Information for Itinerary ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Title"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Title")).sendKeys("Mr");
				mondeeInc.waitInSeconds(2);
				logMessage("Verify that Title options is available & entered", "Title options should be available & entered successfully","Title options is available & entered successfully", "Passed");
			}else {
				logMessage("Verify that Title options is available & entered", "Title options should be available & entered successfully","Title options is NOT available & NOT entered successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_LastName"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_LastName")).clear();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_LastName")).sendKeys("Kalyan");
				mondeeInc.waitInSeconds(2);
				logMessage("Verify that Last Name options is available & entered", "Last Name options should be available & entered successfully","Last Name options is available & entered successfully", "Passed");
			}else {
				logMessage("Verify that Last Name options is available & entered", "Last Name options should be available & entered successfully","Last Name options is NOT available & NOT entered successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_FirstName"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_FirstName")).clear();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_FirstName")).sendKeys("Kalyan");
				mondeeInc.waitInSeconds(2);
				logMessage("Verify that First Name options is available & entered", "First Name options should be available & entered successfully","First Name options is available & entered successfully", "Passed");
			}else {
				logMessage("Verify that First Name options is available & entered", "First Name options should be available & entered successfully","First Name options is NOT available & NOT entered successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Gender"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Gender")).sendKeys("Female");
				mondeeInc.waitInSeconds(2);
				logMessage("Verify that Gender options is available & entered", "Gender options should be available & entered successfully","Gender options is available & entered successfully", "Passed");
			}else {
				logMessage("Verify that Gender options is available & entered", "Gender options should be available & entered successfully","Gender options is NOT available & NOT entered successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_BirthMonth"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_BirthMonth")).sendKeys("MAY");
				mondeeInc.waitInSeconds(2);
				logMessage("Verify that Birth Month options is available & entered", "Birth Month options should be available & entered successfully","Birth Month options is available & entered successfully", "Passed");
			}else {
				logMessage("Verify that Birth Month options is available & entered", "Birth Month options should be available & entered successfully","Birth Month options is NOT available & NOT entered successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Birthdate"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_Birthdate")).sendKeys("25");
				mondeeInc.waitInSeconds(2);
				logMessage("Verify that Birth Date options is available & entered", "Birth Date options should be available & entered successfully","Birth Date options is available & entered successfully", "Passed");
			}else {
				logMessage("Verify that Birth Date options is available & entered", "Birth Date options should be available & entered successfully","Birth Date options is NOT available & NOT entered successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_BirthYear"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_BirthYear")).sendKeys("1981");
				mondeeInc.waitInSeconds(2);
				logMessage("Verify that Birth Year options is available & entered", "Birth Year options should be available & entered successfully","Birth Year options is available & entered successfully", "Passed");
			}else {
				logMessage("Verify that Birth Year options is available & entered", "Birth Year options should be available & entered successfully","Birth Year options is NOT available & NOT entered successfully", "Failed");
				Assert.assertTrue(false);
			}


		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}


	public synchronized void tripProUnicorn_Flight_paymentInfo(){

		try{

			//Passenger Payment Information for Itinerary ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_CardType"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_CardType")).sendKeys("VISA");
				mondeeInc.waitInSeconds(2);
				logMessage("Verify that Card Type options is available & entered", "Card Type options should be available & entered successfully","Card Type options is available & entered successfully", "Passed");
			}else {
				logMessage("Verify that Card Type options is available & entered", "Card Type options should be available & entered successfully","Card Type options is NOT available & NOT entered successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_CardHolderName"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_CardHolderName")).clear();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_CardHolderName")).sendKeys("Kalyan");
				mondeeInc.waitInSeconds(2);
				logMessage("Verify that Cardholder Name options is available & entered", "Cardholder Name options should be available & entered successfully","Cardholder Name options is available & entered successfully", "Passed");
			}else {
				logMessage("Verify that Last Cardholder options is available & entered", "Cardholder Name options should be available & entered successfully","Cardholder Name options is NOT available & NOT entered successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_CardNumber"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_CardNumber")).clear();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_CardNumber")).sendKeys("411111111111121");
				mondeeInc.waitInSeconds(2);
				logMessage("Verify that Card Number options is available & entered", "Card Number options should be available & entered successfully","Card Number options is available & entered successfully", "Passed");
			}else {
				logMessage("Verify that Card Number options is available & entered", "Card Number options should be available & entered successfully","Card Number options is NOT available & NOT entered successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_CardExpiryMonth"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_CardExpiryMonth")).sendKeys("MAY");
				mondeeInc.waitInSeconds(2);
				logMessage("Verify that Card Expiry Month is available & entered", "Card Expiry Month options should be available & entered successfully","Card Expiry Month options is available & entered successfully", "Passed");
			}else {
				logMessage("Verify that Card Expiry Month options is available & entered", "Card Expiry Month options should be available & entered successfully","Card Expiry Month options is NOT available & NOT entered successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_CardExpiryYear"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_CardExpiryYear")).sendKeys("2010");
				mondeeInc.waitInSeconds(2);
				logMessage("Verify that Card Expiry Year is available & entered", "Card Expiry Year options should be available & entered successfully","Card Expiry Year options is available & entered successfully", "Passed");
			}else {
				logMessage("Verify that Card Expiry Year options is available & entered", "Card Expiry Year options should be available & entered successfully","Card Expiry Year options is NOT available & NOT entered successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_CardSecurityCode"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_CardSecurityCode")).sendKeys("123");
				mondeeInc.waitInSeconds(2);
				logMessage("Verify that Card Security Code is available & entered", "Card Security Code options should be available & entered successfully","Card Security Code options is available & entered successfully", "Passed");
			}else {
				logMessage("Verify that Card Security Code options is available & entered", "Card Security Code options should be available & entered successfully","Card Security Code options is NOT available & NOT entered successfully", "Failed");
				Assert.assertTrue(false);
			}


		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}


	public synchronized void tripProUnicorn_Flight_billingInfo(){

		try{

			//Passenger Billing Information for Itinerary ***
			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_BillingAddress"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_BillingAddress")).clear();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_BillingAddress")).sendKeys("Foster City");
				mondeeInc.waitInSeconds(2);
				logMessage("Verify that Billing Address options is available & entered", "Billing Address options should be available & entered successfully","Billing Address options is available & entered successfully", "Passed");
			}else {
				logMessage("Verify that Billing Address options is available & entered", "Billing Address options should be available & entered successfully","Billing Address options is NOT available & NOT entered successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_BillingState"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_BillingState")).sendKeys("CALIFORNIA");
				mondeeInc.waitInSeconds(2);
				logMessage("Verify that Passenger State is available & entered", "Passenger State options should be available & entered successfully","Passenger State options is available & entered successfully", "Passed");
			}else {
				logMessage("Verify that Passenger State options is available & entered", "Passenger State options should be available & entered successfully","Passenger State options is NOT available & NOT entered successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_City"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_City")).clear();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_City")).sendKeys("Foster City");
				mondeeInc.waitInSeconds(2);
				logMessage("Verify that Passenger City is available & entered", "Passenger City options should be available & entered successfully","Passenger City options is available & entered successfully", "Passed");
			}else {
				logMessage("Verify that Passenger City options is available & entered", "Passenger City options should be available & entered successfully","Passenger City options is NOT available & NOT entered successfully", "Failed");
				Assert.assertTrue(false);
			}

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_ZipCode"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_ZipCode")).clear();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_ZipCode")).sendKeys("94404");
				mondeeInc.waitInSeconds(2);
				logMessage("Verify that Passenger Zip Code is available & entered", "Passenger Zip Code options should be available & entered successfully","Passenger Zip Code options is available & entered successfully", "Passed");
			}else {
				logMessage("Verify that Passenger Zip Code options is available & entered", "Passenger Zip Code options should be available & entered successfully","Passenger Zip Code options is NOT available & NOT entered successfully", "Failed");
				Assert.assertTrue(false);
			}


		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}


	public synchronized void tripProUnicorn_Flight_contactInfo(){

		try{

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_CardHolderName"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_CardHolderName")).clear();
				mondeeInc.waitInSeconds(1);
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_CardHolderName")).sendKeys("Kalyan");
				mondeeInc.waitInSeconds(2);
				logMessage("Verify that Cardholder Name options is available & entered", "Cardholder Name options should be available & entered successfully","Cardholder Name options is available & entered successfully", "Passed");
			}else {
				logMessage("Verify that Last Cardholder options is available & entered", "Cardholder Name options should be available & entered successfully","Cardholder Name options is NOT available & NOT entered successfully", "Failed");
				Assert.assertTrue(false);
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_Flight_orderTicket(){

		try{

			if (mondeeInc.isElementPresent(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_OrderTicket"))){
				mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Flights_OrderTicket")).click();
				mondeeInc.waitInSeconds(2);
				logMessage("Verify that Order Tick button is available & clicked", "Order Tick button should be available & clicked successfully","Order Tick button is available & clicked successfully", "Passed");
			}else {
				logMessage("Verify that Order Tick button is available & clicked", "Order Tick button should be available & clicked successfully","Order Tick button is NOT available & NOT clicked successfully", "Failed");
				Assert.assertTrue(false);
			}

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_Flight_shift_1(){

		try{

			mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Sabre_Entr_search")).sendKeys(Keys.SHIFT+"1");
			logMessage("Verify that Shift '1' is performed", "Shift '1' should be performed successfully","SHIFT '1' is performed successfully", "Passed");
			mondeeInc.waitInSeconds(20);
			driver.switchTo().defaultContent();
			mondeeInc.waitInSeconds(1);

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}


	public synchronized void tripProUnicorn_Flight_shift_2(){

		try{

			mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Sabre_Entr_search")).sendKeys(Keys.SHIFT+"2");
			logMessage("Verify that Shift '2' is performed", "Shift '2' should be performed successfully","SHIFT '2' is performed successfully", "Passed");

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}


	public synchronized void tripProUnicorn_Flight_shift_3(){

		try{

			mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Sabre_Entr_search")).sendKeys(Keys.SHIFT+"2");
			logMessage("Verify that Shift '3' is performed", "Shift '3' should be performed successfully","SHIFT '3' is performed successfully", "Passed");

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_Flight_shift_4(){

		try{

			mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Sabre_Entr_search")).sendKeys(Keys.SHIFT+"4");
			logMessage("Verify that Shift '4' is performed", "Shift '4' should be performed successfully","SHIFT '4' is performed successfully", "Passed");

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_Flight_shift_5(){

		try{

			mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Sabre_Entr_search")).sendKeys(Keys.SHIFT+"5");
			logMessage("Verify that Shift '5' is performed", "Shift '5' should be performed successfully","SHIFT '5' is performed successfully", "Passed");

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_Flight_shift_6(){

		try{

			mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Sabre_Entr_search")).sendKeys(Keys.SHIFT+"6");
			logMessage("Verify that Shift '6' is performed", "Shift '6' should be performed successfully","SHIFT '6' is performed successfully", "Passed");

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_Flight_shift_7(){

		try{

			mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Sabre_Entr_search")).sendKeys(Keys.SHIFT+"7");
			logMessage("Verify that Shift '7' is performed", "Shift '7' should be performed successfully","SHIFT '7' is performed successfully", "Passed");

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_Flight_shift_8(){

		try{

			mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Sabre_Entr_search")).sendKeys(Keys.SHIFT+"8");
			logMessage("Verify that Shift '8' is performed", "Shift '8' should be performed successfully","SHIFT '8' is performed successfully", "Passed");

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_Flight_shift_9(){

		try{

			mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Sabre_Entr_search")).sendKeys(Keys.SHIFT+"9");
			logMessage("Verify that Shift '9' is performed", "Shift '9' should be performed successfully","SHIFT '9' is performed successfully", "Passed");

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_Flight_shift_0(){

		try{

			mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Sabre_Entr_search")).sendKeys(Keys.SHIFT+"0");
			logMessage("Verify that Shift '0' is performed", "Shift '0' should be performed successfully","SHIFT '0' is performed successfully", "Passed");

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}


	public synchronized void tripProUnicorn_Flight_shift_IG(){

		try{

			mondeeInc.waitInSeconds(4);
			mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Sabre_Entr_search")).sendKeys(Keys.SHIFT+"1");
			logMessage("Verify that Shift '1' is performed", "Shift '1' should be performed successfully","SHIFT '1' is performed successfully", "Failed");

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_Flight_shift_M(){

		try{

			mondeeInc.waitInSeconds(4);
			mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Sabre_Entr_search")).sendKeys(Keys.SHIFT+"M");
			logMessage("Verify that Shift 'M' is performed", "Shift 'M' should be performed successfully","SHIFT 'M' is performed successfully", "Failed");

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_Flight_shift_R(){

		try{

			mondeeInc.waitInSeconds(4);
			mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Sabre_Entr_search")).sendKeys(Keys.SHIFT+"R");
			logMessage("Verify that Shift 'R' is performed", "Shift 'R' should be performed successfully","SHIFT 'R' is performed successfully", "Failed");

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}

	public synchronized void tripProUnicorn_Flight_shift_X(){

		try{

			mondeeInc.waitInSeconds(4);
			mondeeInc.findElement(driver, PropertiesFileReader.getProperty("tripProUnicorn_Sabre_Entr_search")).sendKeys(Keys.SHIFT+"X");
			logMessage("Verify that Shift 'X' is performed", "Shift 'X' should be performed successfully","SHIFT 'X' is performed successfully", "Failed");

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception :",e);
			String errmsg=e.getMessage();
			logMessage("Step Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}
	}







}