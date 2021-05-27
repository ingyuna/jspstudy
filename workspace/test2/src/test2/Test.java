package test2;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Test {

    public static void main(String[] args) {
        String clientId = "kOtuvc0AiVzqQyXPAImE"; // 애플리케이션 클라이언트 아이디
        String clientSecret = "PcR3sT8LVb"; // 애플리케이션 클라이언트 시크릿

        String apiUrl = "https://openapi.naver.com/v1/datalab/search";

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        requestHeaders.put("Content-Type", "application/json");

        String requestBody = "{\"startDate\":\"2019-01-02\"," +
                "\"endDate\":\"2019-02-03\"," +
                "\"timeUnit\":\"week\"," +
                "\"keywordGroups\":[{\"groupName\":\"한글\"," + "\"keywords\":[\"한글\",\"korean\"]}," +
                "{\"groupName\":\"영어\"," + "\"keywords\":[\"영어\",\"english\"]}]," +
                "\"device\":\"pc\"," +
                "\"ages\":[\"5\",\"6\"]," +
                "\"gender\":\"f\"}";

        String responseBody = "{\"startDate\":\"2018-12-31\",\"endDate\":\"2019-02-03\",\"timeUnit\":\"week\",\"results\":[{\"title\":\"한글\",\"keywords\":[\"한글\",\"korean\"],\"data\":[{\"period\":\"2018-12-31\",\"ratio\":64.86042},{\"period\":\"2019-01-07\",\"ratio\":89.81937},{\"period\":\"2019-01-14\",\"ratio\":86.04269},{\"period\":\"2019-01-21\",\"ratio\":100},{\"period\":\"2019-01-28\",\"ratio\":79.63875}]},{\"title\":\"영어\",\"keywords\":[\"영어\",\"english\"],\"data\":[{\"period\":\"2018-12-31\",\"ratio\":48.27586},{\"period\":\"2019-01-07\",\"ratio\":54.6798},{\"period\":\"2019-01-14\",\"ratio\":61.74055},{\"period\":\"2019-01-21\",\"ratio\":56.32183},{\"period\":\"2019-01-28\",\"ratio\":51.55993}]}]}";
       
       
       // 파싱하기
       JSONParser parser = new JSONParser();
       JSONObject obj = null;
       
       FileOutputStream fos = null;
       try {
    	  fos = new FileOutputStream("text.txt");
    	   
    	   obj = (JSONObject)parser.parse(responseBody); 	// 객체 먼저
    	   String sDate = obj.get("startDate").toString();
    	   String eDate = obj.get("endDate").toString();   
    	   System.out.println("startDate: " + sDate + ", endDate: " + eDate);	
    	   
    	   // 배열
    	   JSONArray results = (JSONArray)obj.get("results");
    	   for (int i = 0; i < results.size(); i++) {
    		   
    		   // 안에 객체
    		   JSONObject obj2 = (JSONObject)results.get(i);
    		   String title = obj2.get("title").toString();
    		   System.out.println("title: " + title);	
    		   
    		   // 다시 배열 
    		   JSONArray data = (JSONArray)obj2.get("data");
    		   for (int j = 0; j < results.size(); j++) {
    			   
    			   // 다시 객체 
    			JSONObject obj3 = (JSONObject)data.get(j);
    			String period = obj3.get("period").toString();
    			System.out.println("period: "+ period);
    		   }
    		   fos.write(responseBody.getBytes());
    	   }
    	   
    	
       } catch (Exception e) {
		// TODO: handle exception
	} finally {
		try {
			if (fos != null) { fos.close(); }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
    }

    private static String post(String apiUrl, Map<String, String> requestHeaders, String requestBody) {
        HttpURLConnection con = connect(apiUrl);

        try {
            con.setRequestMethod("POST");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(requestBody.getBytes());
                wr.flush();
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 응답
                return readBody(con.getInputStream());
            } else {  // 에러 응답
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect(); // Connection을 재활용할 필요가 없는 프로세스일 경우
        }
    }

    private static HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body, StandardCharsets.UTF_8);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
}