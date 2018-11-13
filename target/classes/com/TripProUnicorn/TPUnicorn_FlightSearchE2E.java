package com.TripProUnicorn;

import lib.cisco.util.LocalDriverFactory;
import lib.cisco.util.Report;
import lib.util.AppTestDTO;
import lib.util.ApplicationUtility;
import lib.util.WrapperActionTest;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TPUnicorn_FlightSearchE2E {
	public static long tcStartTime;
	public static long tcendTime;
	
	private Report report = new Report();
	private LocalDriverFactory driverFactory=new LocalDriverFactory();
	//TEST_SCRIPT_ID =  1018177 *****
	@Test(dataProviderClass = lib.cisco.util.AppDataProvider.class,dataProvider="getDataProvider")
	
	
	public TPUnicorn_FlightSearchE2E(AppTestDTO appDto){
		ApplicationUtility appUtility=new ApplicationUtility(report,driverFactory,appDto);
		try {
			tcStartTime=System.currentTimeMillis();
			
			//Calling Reusable functions ****
			appUtility.launchPortal();
			appUtility.tripProUnicorn_login();
			appUtility.tripProUnicorn_selectFlights();
			appUtility.tripProUnicorn_Flight_Search();
			appUtility.tripProUnicorn_Flight_verifysearchResultsData();
			appUtility.tripProUnicorn_SendAgentMarkupThroughEmailID();
			
			/*appUtility.tripProUnicorn_closeTabs();
			appUtility.tripProUnicorn_Signout();*/
		
		


			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			String errmsg=e.getMessage();
			if(errmsg.length() > 500){
				errmsg=errmsg.substring(0,500);
			}
			appUtility.logMessage("Test Execution Failed","Exception Occurred", "Exception Occurred "+errmsg, "Failed");
			Assert.assertTrue(false);
		}finally{
				tcendTime=System.currentTimeMillis();
				//WrapperActionTest.shutDownDriver(appUtility.driver);
		}
	}	
	

}
