package lib.util;

import static lib.cisco.util.DBConnectionManager.commonProperties;
import static lib.cisco.util.MailUtil.mFReport;
//import static lib.cisco.util.ParallelExecution.threadList;
import static lib.cisco.util.ReportHomePage.buildId;
import static lib.cisco.util.ReportHomePage.buildNo;
import static lib.cisco.util.ReportHomePage.buildURL;
import static lib.cisco.util.ReportHomePage.mailReport;
import static lib.cisco.util.ReportHomePage.pbuildId;
import static lib.cisco.util.ReportHomePage.pbuildNo;
import static lib.cisco.util.ReportHomePage.pbuildURL;
import static lib.cisco.util.ReportHomePage.sonarUrl;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import lib.cisco.util.CountersDTO;
import lib.cisco.util.CreateDefectXml;
import lib.cisco.util.DBUtill;
import lib.cisco.util.MailUtil;
import lib.cisco.util.ParallelExecution;
import lib.cisco.util.PropertiesFileReader;
import lib.cisco.util.Report;
import lib.cisco.util.ReportHomePage;
import lib.cisco.util.SequentialExecution;


public class Runner{

	static String releaseId;
	static String appId;
	static String cycle;
	static String testCaseName="";
	static String testCaseIds = "";
	static String executionMode = "";
	public static String groupId="";
	public static String artifactId="";
	static Map<String, List<AppTestDTO>> testDataMap = null;
	static Map<String, AppTestDTO> testingDataMap = null;
	static Map<String, List<AppTestDTO>> testDataMap1 = null;
	static long startTime =0;
	static long endTime=0;
	static Date executionDate = new Date();
	static long executionTime = 0; 
	public static ApplicationDB applicationDB = new ApplicationDB();
	public static String testingType = "";
	private static Map<Integer, Integer> countmap = null;
	private static String secondaryExecution = "";
	static Set<Object> appHashSet=new HashSet<Object>();//Line add
	public static CountersDTO countersDto=new CountersDTO(); 



	public static void main(String[] args)throws Exception{

		String defectXmlPath=System.getProperty("user.dir")+File.separator+"ListOfDefects.xml";
		CreateDefectXml.deleteTestDefectXML(defectXmlPath);
		CreateDefectXml.create(defectXmlPath);

		startTime = System.currentTimeMillis();
		Date dateStartTime = new Date();
		Report report = new Report();
		report.createReportFolders();
		String reportFolderDateTime = Report.getDateTime();


		//######################### Execute from Runner #############################

		//ALMStage ***
		testingType = "release";
		//releaseId = "26738";
		//Q2FY17 ***
		releaseId = "26739";
		appId = "5249";
		cycle = "Regression";

		executionMode = "Parallel"; //Parallel or Sequential


		testCaseName = "com.TripProUnicorn.TPUnicorn_FlightSearchE2E";
		//testCaseName = "com.TripProUnicorn.TPUnicorn_VerifyMenuOptionsE2E";
		
		//testCaseName = "com.TripProUnicorn.TPUnicorn_FlightMultipleSearches";



		/************************************************* TripProUnicorn Test Scripts ***********************************************************/

		/*TripProUnicorn ***
		com.TripProUnicorn.TPUnicorn_Booking
		com.TripProUnicorn.TPUnicorn_Enrollment
		com.TripProUnicorn.TPUnicorn_VerifyMenuOptionsE2E
		com.TripProUnicorn.TPUnicorn_FlightSearchE2E
		com.TripProUnicorn.TPUnicorn_FlightMultipleSearches

		 */



		/************************************************************************************************************************************
		 ************************************************************************************************************************************/

		buildId = "NA";
		buildNo = "NA";
		buildURL = "NA";

		//Maven Goal to execute from pom.xml
		/*
		 clean install exec:java -Dtesting.type=release -Drelease.id=26181
		  -Dapp.id=2008 -Dcycle=Sanity -Dtest.case.Ids=11955 -Dbuild.id=NA
		  -Dbuild.no=NA -Dbuild.url=NA -Dpbuild.id=NA -Dpbuild.no=NA
		  -Dpbuild.url=NA
		 */

		//####################### Execute from POM.Xml ###############################
		/*System.out.println("Got ARGS:" + args[0] + ":" + args[4]);
		testingType = args[0];
		releaseId = args[1];
		appId = args[2];
		cycle = args[3];
		testCaseName = args[4];
		buildId = args[5].trim();
		buildNo = args[6].trim();
		buildURL = args[7].trim();
		pbuildId = args[8].trim() ;
		pbuildNo = args[9].trim() ;
		pbuildURL = args[10].trim() ;
		artifactId=args[11].trim();
		groupId=args[12].trim();
		sonarUrl=args[13]+"dashboard/index/"+groupId+":"+artifactId;*/

		System.out.println("Build Details:"+buildId+":"+buildNo+":"+buildURL);
		if (buildId == null || buildNo == null || buildURL == null || buildId.equalsIgnoreCase("NA") || buildNo.equalsIgnoreCase("NA") || buildURL.equalsIgnoreCase("NA")) {
			System.out.println("=====Execution on Local environment=====");

			buildId = "NA/Local Execution";
			buildNo = "NA/Local Execution";
			buildURL = "";
		}

		if(pbuildId==null || pbuildNo==null || pbuildURL==null || pbuildId=="NA" || pbuildNo=="NA" || pbuildURL=="NA")
		{
			System.out.println("=====No Parent Job=====");
			pbuildId="NA/Local Execution";
			pbuildNo="NA/Local Execution";
			pbuildURL="";
		}
		if(groupId==null || artifactId==null || groupId.equalsIgnoreCase("NA") || artifactId.equalsIgnoreCase("NA") || sonarUrl==null || sonarUrl.equalsIgnoreCase("NA"))
		{
			System.out.println("=====Not integrated with Sonar Qube=====");
			groupId="";
			artifactId="";
			sonarUrl="";
		}
		if(sonarUrl==null || "".equals(sonarUrl)){
			countersDto.setSonarCube("No");
		}else{
			countersDto.setSonarCube("Yes");
		}
		if (testingType.equalsIgnoreCase("Adhoc")) {
			releaseId="0";
			DBUtill.currentCycle="Adhoc";
			System.out.println("Adhoc testing.....");
			adhocTesting();
		} else if (testingType.equalsIgnoreCase("release")) {
			if ("NA".equalsIgnoreCase(testCaseName)) {
				testCaseName = "";
			}
			releaseTesting();
		}

		// main Report
		endTime = System.currentTimeMillis();
		executionTime = endTime - startTime; // Main report line
		Date dateEndTime = new Date();
		
	}
	
	
	public static void adhocTesting() throws Exception {
		countersDto.setAdhocTesting("Yes");
		countersDto.setReleaseTesting("No");
		testDataMap = applicationDB.getTestDataforAdhoc(appId, testCaseName);
		ArrayList<String> items = new ArrayList<String>(Arrays.asList(testCaseName.split(",")));
		System.out.println("=============================Adhoc Execution Started=================================");
		commonProperties = PropertiesFileReader.getInstance().readProperties("common.properties");
		for (int i = 0; i < items.size(); i++) {
			final Map<String, List<String>> testCasesMap = DBUtill.getTestCaseNamesForAdhocTesting(appId, items.get(i).toString());
			SequentialExecution.runTestNGSequential(testCasesMap);
		}
		System.out.println("=============================Adhoc Execution Completed=================================");
	}

	public static void releaseTesting() throws Exception {
		Map<String,AppTestDTO> appTestDTOMap = applicationDB.getTestData();
		String[] testCaseNames = testCaseName.split(",");
		for(int i=0;i<testCaseNames.length;i++)
		{
			if(executionMode.equals("Parallel"))
			{
				ParallelExecutionThread parallelExecutionThread = new Runner().new ParallelExecutionThread(testCaseNames[i],appTestDTOMap);
				parallelExecutionThread.start();
			}else
			{
				AppTestDTO appTestDTO = appTestDTOMap.get(testCaseNames[i]);
				primaryExecution(appTestDTO, testCaseNames[i]);
			}
		}
		//secondaryExecution = commonProperties.getProperty("secondaryExecution").trim();

	}

	public static void primaryExecution(AppTestDTO appTestDTO, String testCaseName)
			throws Exception {

		System.out.println("====Sequential Execution Started============");

		Class.forName(testCaseName).getConstructor(AppTestDTO.class).newInstance( new Object[] { appTestDTO });

		//SequentialExecution.runTestNGSequential(testCaseName);

		System.out.println("====Sequential Execution Completed============");
	}

	public static void secondaryExecution(Map<Integer, Integer> countmap,Map<String, List<AppTestDTO>> testDataMap) throws Exception {
		countersDto.setPrimaryExecution("No");
		countersDto.setSecondaryExecution("Yes");
		Iterator it = countmap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			int parallelCount = (Integer) pairs.getValue();
			int sequenceNumber = (Integer) pairs.getKey();
			System.out.println("Sequnce Number==" + sequenceNumber+ " and Corrsponding count = " + parallelCount);
			if (parallelCount == 1) {
				final Map<String, List<String>> testCasesMap = DBUtill.getTestCaseNamesForSecondaryExecution(releaseId,appId, cycle, sequenceNumber);
				List<String> values=null;
				if (!testCasesMap.isEmpty()) {
					try{
						String key=(String)testCasesMap.keySet().iterator().next();
						values=testCasesMap.get(key);
						for(int i=0;i<values.size();i++){
							SequentialExecution.runTestNGSequential(testCasesMap);
						}
					}catch(Exception e){
						Report.errorToLog4j(e.getLocalizedMessage());
						e.printStackTrace();
					}
				}
				testCasesMap.clear();
				System.out.println("====Sequential Execution Completed============");
			} else {
				System.out.println("=========Parallel Execution Started=======");
				final Map<String, List<String>> testCasesMap = DBUtill.getTestCaseNamesForSecondaryExecution(releaseId,appId, cycle, sequenceNumber);
				ParallelExecution.runTestNGParallel(testCasesMap);
				/*for (int i = 0; i < threadList.size(); i++) {
					threadList.get(i).join();
				}*/
				System.out.println("==============Parallel Execution Completed===========");
			}

			it.remove(); // avoids a ConcurrentModificationException
		}

	}

	public class ParallelExecutionThread extends Thread
	{
		Map<String,AppTestDTO> appTestDTOMap = null;
		String testCaseName = null;
		ParallelExecutionThread(String testCaseName, Map<String,AppTestDTO> appTestDTOMap)
		{
			this.testCaseName = testCaseName;
			this.appTestDTOMap = appTestDTOMap;
		}
		public void run()
		{
			System.out.println(testCaseName);
			try {
				AppTestDTO appTestDTO = appTestDTOMap.get(testCaseName);
				primaryExecution(appTestDTO, testCaseName);
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
	}


}
