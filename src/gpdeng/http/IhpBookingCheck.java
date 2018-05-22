/**
 * @author: sdeng
 *
 * @create date:2016 4 14
 */
package gpdeng.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import sdeng.CommonUtils;
import sdeng.DateUtils;

public class IhpBookingCheck {

	private final String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) throws Exception
	{

		IhpBookingCheck ihpBookingCheck = new IhpBookingCheck();

		int d = 0;
		ihpBookingCheck.checkOneDay(d);

		int start = 1;
		int days = 8;
		ihpBookingCheck.checkAllStatusByUpcomingDays(start, days);
	}

	public void checkOneDay(int d) throws Exception
	{
		IhpBookingCheck http = new IhpBookingCheck();

		// System.out.println("Testing 1 - Send Http GET request");
		// http.sendGet();

		System.out.println("\n------------------------");

		// String result =http.sendPost();
		Date today = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(today);
		c.add(Calendar.DATE, d);

		System.out.println(c.getTime());

		String todayValue = DateUtils.formatBySimpledate(c.getTime(),
				"dd-MM-yyyy");
		todayValue = todayValue.replaceAll("-", "%2F");
		String dayParam = "&mydate=" + todayValue;
		String sportParam = "&act_type_code=Badminton%3A%3A1";

		String LRMParam = "fac_type_code=LRM&centre_code=LRM";
		String FHPParam = "fac_type_code=FHM&centre_code=FHM";

		String allParam = "";

		// For test only
		// System.out.println(dayParam);

		// String LRMParam=
		// "fac_type_code=LRM&centre_code=LRM&mydate=14%2F04%2F2016&act_type_code=Badminton%3A%3A1";
		// String FHPParam=
		// "fac_type_code=FHM&centre_code=FHM&mydate=14%2F04%2F2016&act_type_code=Badminton%3A%3A1";

		// String LRMParam= "fac_type_code=LRM&centre_code=LRM";
		// String FHPParam= "fac_type_code=FHM&centre_code=FHM";

		allParam = LRMParam + dayParam + sportParam;
		String result = http.sendPostWithRespondse(allParam);
		extractBookingStatus(result);

		allParam = FHPParam + dayParam + sportParam;
		result = http.sendPostWithRespondse(allParam);
		extractBookingStatus(result);
		System.out.println(c.getTime());
	}

	public void checkAllStatusByUpcomingDays(int start, int days)
	{
		for (int i = start; i < days; i++)
		{
			try
			{
				checkOneDay(i);
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// HTTP GET request
	private void sendGet() throws Exception
	{

		String url = "http://www.google.com/search?q=mkyong";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		// while ((inputLine = in.read()) != null) {
		// response.append(inputLine);
		// }
		int value;

		while ((value = in.read()) != -1)
		{
			// converts int to character
			char c = (char) value;

			response.append(c);
			// prints character
			// System.out.println(c);
		}

		in.close();

		// print result
		System.out.println(response.toString());

	}

	// HTTP POST request
	private void sendPost() throws Exception
	{
		StringBuffer response = new StringBuffer();
		// String urlParameters =
		// "fac_type_code=FHM&centre_code=FHM&mydate=14/04/2016&act_type_code=Badminton::1";
		String urlParameters = "fac_type_code=LRM&centre_code=LRM&mydate=14%2F04%2F2016&act_type_code=Badminton%3A%3A1";

		// String urlParameters = "act_type_code=Badminton%3A%3A1";

		byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
		int postDataLength = postData.length;
		System.out.println("postDataLength:" + postDataLength);

		// Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(
		// "proxy1.scig.gov.hk", 8080));

		String url = "https://ihp.hku.hk/ihpbooking/servlet/IHP_Booking/displayCalendar1";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		// add reuqest header
		con.setDoOutput(true);
		con.setInstanceFollowRedirects(false);
		con.setRequestMethod("POST");
		con.setRequestProperty("Accept-Language", "zh-HK");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		con.setRequestProperty("Accept-Encoding", "gzip, deflate");
		con.setRequestProperty("Host", "ihp.hku.hk");

		// con.setRequestProperty( "charset", "utf-8");
		con.setRequestProperty("Content-Length",
				Integer.toString(postDataLength));
		con.setUseCaches(false);

		// String urlParameters =
		// "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

		// Send post request

		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.write(postData);
		// wr.writeBytes(urlParameters);
		// wr.flush();
		// wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;

		// while ((inputLine = in.readLine()) != null) {
		// response.append(inputLine);
		// }

		int value;

		while ((value = in.read()) != -1)
		{
			// converts int to character
			char c = (char) value;

			response.append(c);
			// prints character
			// System.out.println(c);
		}

		in.close();

		// print result
		System.out.println(response.toString());

	}

	private String sendPostWithRespondse(String urlParameters) throws Exception
	{
		StringBuffer response = new StringBuffer();

		byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
		int postDataLength = postData.length;
		// System.out.println("postDataLength:"+postDataLength);

		// Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(
		// "proxy1.scig.gov.hk", 8080));

		String url = "https://ihp.hku.hk/ihpbooking/servlet/IHP_Booking/displayCalendar1";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
		// HttpsURLConnection con = (HttpsURLConnection)
		// obj.openConnection(proxy);

		// add reuqest header
		con.setDoOutput(true);
		con.setInstanceFollowRedirects(false);
		con.setRequestMethod("POST");
		con.setRequestProperty("Accept-Language", "zh-HK");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		con.setRequestProperty("Accept-Encoding", "gzip, deflate");
		con.setRequestProperty("Host", "ihp.hku.hk");

		// con.setRequestProperty( "charset", "utf-8");
		con.setRequestProperty("Content-Length",
				Integer.toString(postDataLength));
		con.setUseCaches(false);

		// Send post request

		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.write(postData);

		int responseCode = con.getResponseCode();

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));

		int value;

		while ((value = in.read()) != -1)
		{
			// converts int to character
			char c = (char) value;
			response.append(c);

		}

		in.close();

		return response.toString();

	}

	// public static String extractBookingStatus(String in)
	// {
	// StringBuilder sb = new StringBuilder();
	//
	//
	// // <tr></tr>
	//
	// // <table border=1>
	//
	// String tablePatternStr = "<table border=1>*</table>";
	// String trPatternStr = "<tr>*</tr>";
	// String tdPatternStr = "<td>*</td>";
	// // Create a Pattern object
	// Pattern tablePattern = Pattern.compile(tablePatternStr);
	// Pattern trPattern = Pattern.compile(trPatternStr);
	// Pattern tdPattern = Pattern.compile(tdPatternStr);
	//
	// // Now create matcher object.
	// Matcher m;
	//
	//
	// // String [] arrLines = in.split("\n");
	// // for(String line: arrLines)
	// // {
	// // if(a.indexOf(arg0))
	//
	// m=tablePattern.matcher(in);
	// if(m.matches())
	// {
	// for(int i=0;i<m.groupCount();i++)
	// {
	// String table =m.group(i);
	//
	// System.out.println(table);
	//
	//
	// }
	// }
	// // }
	//
	//
	// return sb.toString();
	//
	// }

	public static String extractBookingStatus(String in)
	{
		StringBuilder sb = new StringBuilder();

		Document doc = Jsoup.parse(in);
		if (doc.select("table").size() < 7)
			return "";

		Element table = doc.select("table").get(7); // select the first table.

		int index = 0;
		// for (Element table : doc.select("table")) {

		for (Element row : table.select("tr"))
		{
			Elements tds = row.select("td");
			// System.out.print("row "+index+++": ");
			for (Element td : tds)
			{
				// System.out.print(td.text()+" ");
				if (!CommonUtils.isStrInValues(td.text(), new String[] { "B",
						"U" }))
				{
					System.out.print(td.text() + " ");
				} else
				{
					System.out.print("- ");
				}

			}

			System.out.println();
		}
		// }

		return sb.toString();

	}

}
