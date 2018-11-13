package com.TripProUnicorn;

import lib.cisco.util.LocalDriverFactory;
import lib.cisco.util.Report;
import lib.util.AppTestDTO;
import lib.util.ApplicationUtility;
import lib.util.WrapperActionTest;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TPUnicorn_Booking {
	public static long tcStartTime;
	public static long tcendTime;
	
	private Report report = new Report();
	private LocalDriverFactory driverFactory=new LocalDriverFactory();
	//TEST_SCRIPT_ID =  1018177 *****
	@Test(dataProviderClass = lib.cisco.util.AppDataProvider.class,dataProvider="getDataProvider")
	
	
	public TPUnicorn_Booking(AppTestDTO appDto){
		ApplicationUtility appUtility=new ApplicationUtility(report,driverFactory,appDto);
		try {
			tcStartTime=System.currentTimeMillis();
			
			//Calling Reusable functions ****
			appUtility.launchPortal();
			appUtility.tripProUnicorn_login();
			appUtility.tripProUnicorn_selectSabre();
			appUtility.tripProUnicorn_selectAmadeus();
			appUtility.tripProUnicorn_selectFlights();
			appUtility.tripProUnicorn_selectHotles();
			appUtility.tripProUnicorn_selectUserManagement();
			appUtility.mondeeInc_selectCustomerCare();
			appUtility.tripProUnicorn_selectReports();
			appUtility.tripProUnicorn_selectFlightBookingReports();
			appUtility.tripProUnicorn_Menu_HotelBookingReports();
			appUtility.tripProUnicorn_Menu_BookingFailureReports();
			appUtility.mondeeInc_selectDebitMemos();
			appUtility.tripProUnicorn_selectMarketing();
			appUtility.tripProUnicorn_selectSabre();
			appUtility.tripProUnicorn_searchSabre();
			appUtility.tripProUnicorn_Flight_shift_1();
			appUtility.tripProUnicorn_selectFlights();
			appUtility.tripProUnicorn_Flight_Search();
			appUtility.tripProUnicorn_Flight_selectItinerary();
			appUtility.tripProUnicorn_Flight_passengerInfo();
			appUtility.tripProUnicorn_Flight_paymentInfo();
			appUtility.tripProUnicorn_Flight_billingInfo();
			appUtility.tripProUnicorn_Flight_orderTicket();


			
			
			
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
