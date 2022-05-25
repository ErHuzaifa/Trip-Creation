package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification req;
	public RequestSpecification requestSpecification(String authToken) throws IOException {
		
		if (req == null) 
		{
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			
		 RequestSpecification req =new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
				 .addHeader("Authorization", authToken )
				 .addHeader("deviceid", "123")
				 .addHeader("buildnumber", "1234")
				 .addHeader("platform", "postman")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
			return req;
		}
		return req;
				 //.setContentType(ContentType.JSON).build();
		 
}
	
	public static String getGlobalValue(String key) throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\hanu\\eclipse-workspace\\TripCreationFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
		
	}
	
	public String getJsonPath(Response response, String key) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
	}
	
	public String getDBJson(String ColumnName, String JsonKey) {
		
		    JsonPath jsonPath = JsonPath.from(ColumnName);
		    String dbSenderDetailsName = jsonPath.getString(JsonKey);
		    return dbSenderDetailsName;
	}
	
	public String addQuotes(String name) {
	
	String removeQuotes = name.replace("name ", "");
    String finalName = '\''+ removeQuotes + '\''; 
    return finalName;
    }
	
	public  String tomorrowsDate() {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 1);
		date = c.getTime();
		String tomorrowsDate = sd.format(date);
		return tomorrowsDate;
	}
	
	public ResultSet getDBConnectionAndExecuteQuery(String queryToExecute) throws SQLException {
		ResultSet rs = null;
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(
					"jdbc:postgresql://database-1.csxwfz3yi9pw.ap-south-1.rds.amazonaws.com:5432/pidge-1", "postgres",
					"bycdsXmws9wHDUirqdVX");
			Statement statement = conn.createStatement();

			rs = statement.executeQuery(queryToExecute);
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			conn.close();
			System.exit(0);
		}
		return rs;
	}
	
	public int stringToInt(List<String> a, int b) {
		int c = Integer.parseInt(a.get(b));
		return c;
	}
	
	
}


