package lib.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import lib.cisco.util.DBConnectionManager;
import lib.cisco.util.PropertiesFileReader;

public class ApplicationDB{
	
	private final static Logger LOGGER = Logger.getLogger(ApplicationDB.class.getName());
	
	String testCaseScriptIds = "";
	String tableName = "";
	List<String> failedDataIdsList = new ArrayList<String>();
	String dataIds = "";
	
	private Connection getConnection()
	{
		Connection connection = null;
		try {
			
			String driver = "oracle.jdbc.Driver";
			 String url = "jdbc:oracle:thin:@localhost:1521:XE";
			 // load jdbc driver
			// Class.forName(driver);
			 Class.forName("oracle.jdbc.driver.OracleDriver"); 
			 // connection info
			 Properties props = new Properties();
			 props.put("user","SYS");
			 props.put("password", "atom123");
			 props.put("internal_logon", "sysdba");
			 // connect as sysdba
			 connection = DriverManager.getConnection(url, props);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public Map<String,AppTestDTO> getTestData() {

		Map<String,AppTestDTO> appTestDTOMap = null;
		ResultSet resultSet = null;
		Connection connection = null;
		PreparedStatement pstmt=null;
		String query="";
		try {
			//connection = DBConnectionManager.getConnection();
			connection = getConnection();

			query="Select * from atomdata";
			System.out.println("Primary DATA Sql Query :" + query);

			//Get ResultSet
			pstmt=connection.prepareStatement(query);
			resultSet = pstmt.executeQuery();

			if(resultSet != null)
			{
				appTestDTOMap = new HashMap<String, AppTestDTO>();
				while (resultSet.next()) {

					String testCaseName = resultSet.getString("TESTCASENAME");
					
					System.out.println("TestCaseName:: " + testCaseName);
					AppTestDTO appTestDTO = new AppTestDTO();
					appTestDTO.setTestCaseName(testCaseName);
					appTestDTO.setUrl(resultSet.getString("URL"));
					appTestDTO.setUserName(resultSet.getString("USERNAME"));
					appTestDTO.setPassword(resultSet.getString("PASSWORD"));
					appTestDTO.setBrowserType(resultSet.getString("BROWSERTYPE"));
					
					appTestDTOMap.put(testCaseName, appTestDTO);
				}
			}

		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Exception :",e);
		}finally {
			DBConnectionManager.close(connection);
			DBConnectionManager.close(pstmt);
			DBConnectionManager.close(resultSet);
			testCaseScriptIds="";
		}
		return appTestDTOMap;
	}
	
	
	public Map<String, List<AppTestDTO>> getTestData(String releaseId,String appId,String cycle) {

		ResultSet resultSet = null;
		ResultSet resultSet1 = null;
		String testMethodName = null;
		String className=null;
		Connection connection = null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt1=null;
		Map<String, List<AppTestDTO>> appTestDataMap = new HashMap<String, List<AppTestDTO>>();
		List<AppTestDTO> appDTOListData = null;
		AppTestDTO appDTO = null;
		String query="";
		String testScriptIds="";
		try {
			connection = DBConnectionManager.getConnection();
				if(failedDataIdsList.size() == 0){
					List<String> testIds=getTestScriptIdsForAllExecution(connection,releaseId,appId,cycle,Runner.testCaseIds);
					for(String id:testIds){
						testScriptIds +="'"+id+"',";
					}
				}
				if(testScriptIds.length() > 0){
					testScriptIds=testScriptIds.substring(0, testScriptIds.length()-1);
				}
				
				if(failedDataIdsList.size() > 0){
					System.out.println("Failed dataIds in getting the data=="+dataIds);
					query="select * from "+tableName+" where DATA_ID in ("+dataIds+") and EXECUTION='Y' order by DATA_ID";
					System.out.println("Secondary DATA Sql Query :" + query);
				}else{
					System.out.println("TestScript IDs to fetech all the testdata==="+testScriptIds);
					query="select * from "+tableName+" where TEST_SCRIPT_ID in ("+testScriptIds+") and EXECUTION='Y' order by DATA_ID";
					System.out.println("Primary DATA Sql Query :" + query);
				}
				
				//Get ResultSet
				pstmt=connection.prepareStatement(query);
				//pstmt.setString(1, testCaseScriptIds);
				resultSet = pstmt.executeQuery();
			
				while (resultSet.next()) {
					
					testMethodName = resultSet.getString("TEST_METHOD_NAME");
					className = resultSet.getString("TEST_CLASS_NAME");
					if (null != testMethodName && !"".equalsIgnoreCase(testMethodName)) {

						if (appTestDataMap.get(className+"."+testMethodName) == null) {
							appDTOListData = new ArrayList<AppTestDTO>();
							appDTO = setAndGetTestDataDTO(resultSet);
							appDTOListData.add(appDTO);
							appTestDataMap.put(className+"."+testMethodName, appDTOListData);

						} else {
							appDTOListData = (List<AppTestDTO>) appTestDataMap.get(className+"."+testMethodName);
							appDTO = setAndGetTestDataDTO(resultSet);
							appDTOListData.add(appDTO);
						}
					}
				}
				System.out.println("appTestDataMap=="+appTestDataMap);

		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Exception :",e);
		}finally {
			DBConnectionManager.close(connection);
			DBConnectionManager.close(pstmt);
			DBConnectionManager.close(pstmt1);
			DBConnectionManager.close(resultSet);
			DBConnectionManager.close(resultSet1);
			testCaseScriptIds="";
		}
		return appTestDataMap;

	}
	
	
	public Map<String, List<AppTestDTO>> getTestDataforAdhoc(String appId, String testScriptIdList) {
		String className = null;
		ResultSet resultSet = null;
		ResultSet resultSet1 = null;
		String testMethodName = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		Map<String, List<AppTestDTO>> appTestDataMap = new HashMap<String, List<AppTestDTO>>();
		List<AppTestDTO> appDTOListData = null;
		AppTestDTO appDTO = null;
		String query = "";
		String testScriptIds = "";
		String mastertrackTable = "";
		try {
			try {
				connection = DBConnectionManager.getConnection();
			} catch (Exception e) {
				LOGGER.log(Level.SEVERE, "Error:DB Connection failed :", e);
			}
			if (null != connection) {
				mastertrackTable = PropertiesFileReader.getProperty("mastertracktable");
				System.out.println("Master table in getTestDataforAdhoc:" + mastertrackTable);
				String tableQuery = "select Execution_TABLE from " + mastertrackTable + " where APP_ID='" + appId + "'";
				pstmt = connection.prepareStatement(tableQuery);
				resultSet1 = pstmt.executeQuery();
				if (resultSet1.next()) {
					tableName = resultSet1.getString("EXECUTION_TABLE");

				}
				query = "select * from " + tableName + " where TEST_SCRIPT_ID in (" + testScriptIdList
						+ ") and EXECUTION='Y' order by DATA_ID";
				System.out.println("===getTestDataforAdhoc====TEST DATA Sql Query :" + query);
				// Get ResultSet
				pstmt = connection.prepareStatement(query);
				resultSet = pstmt.executeQuery();

				while (resultSet.next()) {

					testMethodName = resultSet.getString("TEST_METHOD_NAME");
					className = resultSet.getString("TEST_CLASS_NAME");
					if (null != testMethodName && !"".equalsIgnoreCase(testMethodName)) {

						if (appTestDataMap.get(className + "." + testMethodName) == null) {
							appDTOListData = new ArrayList<AppTestDTO>();
							appDTO = setAndGetTestDataDTO(resultSet);
							appDTOListData.add(appDTO);
							appTestDataMap.put(className + "." + testMethodName, appDTOListData);

						} else {
							appDTOListData = (List<AppTestDTO>) appTestDataMap.get(className + "." + testMethodName);
							appDTO = setAndGetTestDataDTO(resultSet);
							appDTOListData.add(appDTO);

						}
					}
				}
				System.out.println("TestData Map Size   : " + appTestDataMap);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBConnectionManager.close(connection);
			DBConnectionManager.close(pstmt);
			DBConnectionManager.close(pstmt1);
			DBConnectionManager.close(resultSet);
			DBConnectionManager.close(resultSet1);
			testCaseScriptIds = "";
		}
		return appTestDataMap;

	}
	
	public static List<String> getTestScriptIdsForAllExecution(Connection connection, String releaseId, String appId, String cycle,String testCaseIds)
	{
		List<String> testCaseIdList = new ArrayList<String>();
		
		return testCaseIdList;
	}


	/**
	 * @param testDataResultSet
	 * @return
	 * @throws SQLException
	 */
	public AppTestDTO setAndGetTestDataDTO(ResultSet resultSet)throws SQLException {
		AppTestDTO appDto;
		appDto = new AppTestDTO();
		System.out.println("DataId-===="+resultSet.getString("DATA_ID")+"====");
		
        
        /*appDto.setPortal_URL(resultSet.getString("I_PORTALURL"));
        appDto.setWorkBasketType(resultSet.getString("I_WORKBASKETTYPE"));
        appDto.setPrimPhoneNum(resultSet.getString("I_PRIMPHONENUM")); 
        appDto.setReactionHours(resultSet.getString("I_REACTIONHOURS"));
        appDto.setServLevlVarice(resultSet.getString("I_SERVLEVLVARICE"));
        appDto.setServiceLevel(resultSet.getString("I_SERVICELEVEL"));
        appDto.setPremProdFamly(resultSet.getString("I_PREMPRODFAMLY"));
        appDto.setWareHouse(resultSet.getString("I_WHAREHOUSE"));
        appDto.setAduitLogTag1(resultSet.getString("I_ADTLOGTAG1"));
        appDto.setAduitLogTag2(resultSet.getString("I_ADTLOGTAG2"));
        appDto.setAduitLogTag3(resultSet.getString("I_ADTLOGTAG3"));
        appDto.setSchdDeliryDate(resultSet.getString("I_SCHDDELIRYDATE"));
        appDto.setSchdDeliryTime(resultSet.getString("I_SCHDDELIRYTIME"));
        appDto.setAuthorizedBy(resultSet.getString("I_AUTHORIZEDBY"));
        appDto.setPremFildEngAction(resultSet.getString("I_PREMFILDENGACTION"));
        appDto.setLborLoc(resultSet.getString("I_LBORLOC"));
        appDto.setRecevpart(resultSet.getString("I_RECEVPART"));
        appDto.setManUnManSite(resultSet.getString("I_MANUNMANSITE"));
        appDto.setSecutyclear(resultSet.getString("I_SECUTYCLEAR"));
        appDto.setPartServiceLevel(resultSet.getString("I_PARTSERVLEVL"));
        appDto.setOnSitServLevl(resultSet.getString("I_ONSITSERVLEVL"));
        appDto.setOnSitServLevl(resultSet.getString("I_ONSITSERVLEVL"));
        appDto.setAddFildEng(resultSet.getString("I_ADDFILDENG"));
        */
        

        /*********************************************************************************************************************************************
						          		@@@@@@@@@@@@@@@@@@@@@@@@@@   RMA Simplification   @@@@@@@@@@@@@@@@@@@@@@@@@@ 
        **********************************************************************************************************************************************/
        /*appDto.setDataId(resultSet.getString("DATA_ID"));
        appDto.setTestScriptId(resultSet.getString("TEST_SCRIPT_ID"));
        appDto.setTestClassName(resultSet.getString("TEST_CLASS_NAME"));
        appDto.setTestCaseName(resultSet.getString("TEST_CLASS_NAME"));
        appDto.setTestMethodName(resultSet.getString("TEST_METHOD_NAME"));
        appDto.setBrowserVersion(resultSet.getString("BROWSER_VERSION"));
        appDto.setOsType(resultSet.getString("OS_TYPE"));
        appDto.setBrowserType(resultSet.getString("BROWSER_TYPE"));
        appDto.setGridUrl(resultSet.getString("GRID_URL"));
        appDto.setInvocationMethod(resultSet.getString("INVOCATION_METHOD"));
        appDto.SURL(resultSet.getString("I_SURL"));

        //****
        appDto.setFPRLink(resultSet.getString("I_FPRLINK"));
        appDto.setRmauserName(resultSet.getString("I_USERNAME"));
        appDto.setRmaPassword(resultSet.getString("I_PASSWORD"));
        appDto.setCcoId(resultSet.getString("I_CCOID"));
       // appDto.setSiteId(resultSet.getString("I_SITEID"));
        appDto.setSerialNum(resultSet.getString("I_SERIALNUM"));
        appDto.setContractNum(resultSet.getString("I_CONTRACTNUM"));
        
        //Part Info
        appDto.setSerialNum_I(resultSet.getString("I_SERIALNUM_I"));
        appDto.setSerialNum_II(resultSet.getString("I_SERIALNUM_II"));
        appDto.setSerialNum_III(resultSet.getString("I_SERIALNUM_III"));
        appDto.setPrtInfoServiceLevel(resultSet.getString("I_PRTINFOSERVLEVL"));
        appDto.setPrtInfoServiceLevel_II(resultSet.getString("I_PRTINFOSERVLEVL_II"));
        appDto.setPrtInfContrt(resultSet.getString("I_I_PRTINFOCONTRTID"));
        appDto.setPrtInfContrt_II(resultSet.getString("I_PRTINFOCONTRTID_II"));


        //Product Id 
        appDto.setPartNum(resultSet.getString("I_PARTNUM"));
        appDto.setPartNum_II(resultSet.getString("I_PARTNUM_II"));
        appDto.setPartNum_III(resultSet.getString("I_PARTNUM_III"));
        
        //Quantity
        appDto.setQty(resultSet.getString("I_QTY"));

        //Service Level
       // appDto.setPartServiceLevel(resultSet.getString("I_PARTSERVLEVL"));
        appDto.setServiceLevel(resultSet.getString("I_SERVICELEVEL"));
        appDto.setReactionHours(resultSet.getString("I_REACTIONHOURS"));
        appDto.setServLevlVarice(resultSet.getString("I_SERVLEVLVARICE"));
        
        appDto.setServiceLevel_II(resultSet.getString("I_SERVICELEVEL_II"));
        appDto.setReactionHours_II(resultSet.getString("I_REACTIONHOURS_II"));
        appDto.setServLevlVarice_II(resultSet.getString("I_SERVLEVLVARICE_II"));
        
        //Any Data
        appDto.setAnyData(resultSet.getString("I_ANYDATA"));
        appDto.setAnyDataII(resultSet.getString("I_ANYDATAII"));
        appDto.setAnyDataIII(resultSet.getString("I_ANYDATAIII"));
        appDto.setAnyDataIV(resultSet.getString("I_ANYDATAIV"));
        appDto.setAnyDataV(resultSet.getString("I_ANYDATAV"));
        //appDto.setAnyDataVI(resultSet.getString("I_ANYDATAVI"));
        //appDto.setAnyDataVII(resultSet.getString("I_ANYDATAVII"));
        
        //Failure Code
        appDto.setParFaliurCode(resultSet.getString("I_FALIURCODE"));

        //Shipping Address ***
        appDto.setCompanyName(resultSet.getString("I_COMPANYNAME"));
        appDto.setAddress_I(resultSet.getString("I_ADDRESS_I"));
        appDto.setAddress_II(resultSet.getString("I_ADDRESS_II"));
        appDto.setCountry(resultSet.getString("I_COUNTRY"));
        appDto.setCity(resultSet.getString("I_CITY"));
        appDto.setState(resultSet.getString("I_STATE"));
        appDto.setZip(resultSet.getString("I_ZIP"));
        appDto.setOriginalAddrs(resultSet.getString("I_ORIGINALADDRS"));
        appDto.setFlag(resultSet.getString("I_FLAG"));
        
        appDto.setSiteType(resultSet.getString("I_SITETYPE"));
        appDto.setSecutyclear(resultSet.getString("I_SECUTYCLEAR"));
        appDto.setManUnManSite(resultSet.getString("I_MANUNMANSITE")); */







        


                  

        

        /*********************************************************************************************************************************************
     
     
     
        //Fields for Mobile and Device Autmation
       /* appDto.setDeviceName(resultSet.getString("DEVICE_NAME"));
        appDto.setAppType(resultSet.getString("APP_TYPE"));
        appDto.setPlatformName(resultSet.getString("PLATFORM_NAME"));
        appDto.setPlatformVersion(resultSet.getString("PLATFORM_VERSION"));
        appDto.setHubUrl(resultSet.getString("HUB_URL"));
        appDto.setApp(resultSet.getString("APP"));
        appDto.setAppPackage(resultSet.getString("APP_PACKAGE"));
        appDto.setAppActivity(resultSet.getString("APP_ACTIVITY"));
        appDto.setDeviceType(resultSet.getString("DEVICE_TYPE"));
        appDto.setMobileBrowser(resultSet.getString("MOBILE_BROWSER"));
        appDto.setAppiumVersion(resultSet.getString("APPIUM_VERSION"));*/
        
		return appDto;
	}



}
