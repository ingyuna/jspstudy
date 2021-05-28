package exam;

//네이버 검색 API 예제 - blog 검색
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class ApiSearchMovie {


 public static void main(String[] args) {
     String clientId = "w6N04qpj2Nl4hFL89wZE"; //애플리케이션 클라이언트 아이디값"
     String clientSecret = "5zxX2b_8lU"; //애플리케이션 클라이언트 시크릿값"

     String text = null;
     try {
         text = URLEncoder.encode("코미디", "UTF-8");
         
     } catch (UnsupportedEncodingException e) {
         throw new RuntimeException("검색어 인코딩 실패",e);
     }


     String apiURL = "https://openapi.naver.com/v1/search/movie?query=" + text;    // json 결과
     //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과


     Map<String, String> requestHeaders = new HashMap<>();
     requestHeaders.put("X-Naver-Client-Id", clientId);
     requestHeaders.put("X-Naver-Client-Secret", clientSecret);
     
     String responseBody = get(apiURL,requestHeaders);
     /*String responseBody = "{\"lastBuildDate\": \"Thu, 27 May 2021 16:25:08 +0900\","
     		+ "\"total\": 86,\"start\": 1,"
     		+ "\"display\": 10,"
     		+ "\"items\": [{\"title\": \"어 리얼리 다크 <b>코미디</b>\",\"link\": \"https://movie.naver.com/movie/bi/mi/basic.nhn?code=203905\",\"image\": \"https://ssl.pstatic.net/imgmovie/mdi/mit110/2039/203905_P01_171050.jpg\",\"subtitle\": \"A Really Dark Comedy\",\"pubDate\": \"2021\",\"director\": \"마나시 우아드마테|\",\"actor\": \"\",\"userRating\": \"0.00\"},{\"title\": \"로맨틱 <b>코미디</b>\",\"link\": \"https://movie.naver.com/movie/bi/mi/basic.nhn?code=171026\",\"image\": \"https://ssl.pstatic.net/imgmovie/mdi/mit110/1710/171026_P06_140444.jpg\",\"subtitle\": \"Romantic Comedy\",\"pubDate\": \"2019\",\"director\": \"엘리자베스 샌키|\",\"actor\": \"제시카 바든|찰리 린|\",\"userRating\": \"5.38\"},{\"title\": \"나는 원래 대전에서 로맨틱 <b>코미디</b>를 찍으려고 했었다\",\"link\": \"https://movie.naver.com/movie/bi/mi/basic.nhn?code=181320\",\"image\": \"https://ssl.pstatic.net/imgmovie/mdi/mit110/1813/181320_P01_142943.jpg\",\"subtitle\": \"Daejeon Romantic comedy\",\"pubDate\": \"2018\",\"director\": \"배기원|\",\"actor\": \"이종철|이민아|조하영|\",\"userRating\": \"8.38\"},{\"title\": \"앨런 앤더스: 라이브 앳 더 <b>코미디</b> 캐슬 - 써카 1987\",\"link\": \"https://movie.naver.com/movie/bi/mi/basic.nhn?code=171664\",\"image\": \"https://ssl.pstatic.net/imgmovie/mdi/mit110/1716/171664_P01_174453.jpg\",\"subtitle\": \"Allen Anders: Live at the Comedy Castle - Circa 1987\",\"pubDate\": \"2018\",\"director\": \"로라 모스|\",\"actor\": \"\",\"userRating\": \"0.00\"},{\"title\": \"더 히스토리 오브 <b>코미디</b>\",\"link\": \"https://movie.naver.com/movie/bi/mi/basic.nhn?code=158238\",\"image\": \"https://ssl.pstatic.net/imgmovie/mdi/mit110/1582/158238_P01_165509.jpg\",\"subtitle\": \"The History of Comedy\",\"pubDate\": \"2017\",\"director\": \"크리스토퍼 G. 코웬|션 헤이즈|마크 허조그|토드 밀리너|\",\"actor\": \"\",\"userRating\": \"0.00\"},{\"title\": \"더 <b>코미디</b> 디바인\",\"link\": \"https://movie.naver.com/movie/bi/mi/basic.nhn?code=171600\",\"image\": \"https://ssl.pstatic.net/imgmovie/mdi/mit110/1716/171600_P03_135015.jpg\",\"subtitle\": \"The Comedy Divine\",\"pubDate\": \"2017\",\"director\": \"토니 벤투리|\",\"actor\": \"무릴로 로사|달튼 바히|\",\"userRating\": \"0.00\"},{\"title\": \"웜 <b>코미디</b> 어바웃 디프레션, 매드니스 앤 언풀필드 드림즈\",\"link\": \"https://movie.naver.com/movie/bi/mi/basic.nhn?code=177312\",\"image\": \"https://ssl.pstatic.net/imgmovie/mdi/mit110/1773/177312_P01_160312.jpg\",\"subtitle\": \"Warm Comedy about Depression, Madness and Unfulfilled Dreams\",\"pubDate\": \"2017\",\"director\": \"미할 듀리스|\",\"actor\": \"\",\"userRating\": \"0.00\"},{\"title\": \"칸사이 자니스 주니어 <b>코미디</b> 스타 탄생!\",\"link\": \"https://movie.naver.com/movie/bi/mi/basic.nhn?code=164746\",\"image\": \"\",\"subtitle\": \"Kansai Johnny's Jr. no Owarai Star Tanjo\",\"pubDate\": \"2017\",\"director\": \"이시카와 카츠미|\",\"actor\": \"니시하타 다이고|무카이 코지|무로 류타|미치에다 슌스케 |\",\"userRating\": \"0.00\"},{\"title\": \"스탠드 업! <b>코미디</b>\",\"link\": \"https://movie.naver.com/movie/bi/mi/basic.nhn?code=150326\",\"image\": \"https://ssl.pstatic.net/imgmovie/mdi/mit110/1503/150326_P01_110403.jpg\",\"subtitle\": \"Stand Up ! Comedy\",\"pubDate\": \"2016\",\"director\": \"아리사 코토|\",\"actor\": \"\",\"userRating\": \"0.00\"},{\"title\": \"더 <b>코미디</b> 클럽\",\"link\": \"https://movie.naver.com/movie/bi/mi/basic.nhn?code=148034\",\"image\": \"https://ssl.pstatic.net/imgmovie/mdi/mit110/1480/148034_P01_134639.jpg\",\"subtitle\": \"The Comedy Club\",\"pubDate\": \"2016\",\"director\": \"데이비드 쉔델|\",\"actor\": \"다나 카비|밥 사겟|밥 골드웨이트|케빈 폴락|케빈 닐론|\",\"userRating\": \"0.00\"}]}\r\n" + 
     		" ";
     */
     
     
     // 파싱하기
     JSONParser parser = new JSONParser();
     JSONObject obj = null;
 
     Scanner sc = new Scanner(System.in);
	 System.out.println("영화에 관련된 검색어를 입력하세요>>>");
	 String titles = sc.next();
	 System.out.println(responseBody);
     
     FileOutputStream fos = null;
     try {
    	 
    	 fos = new FileOutputStream("search_result.txt");  	 
    	 obj = (JSONObject)parser.parse(responseBody);
    	 
    	 JSONArray items = (JSONArray)obj.get("items");
    	 for (int i = 0; i < items.size(); i++) {
    		 JSONObject obj2 = (JSONObject)items.get(i);
    		 String title = obj2.get("title").toString();
    		 System.out.println(title);
    		 sc.close();
    		 fos.write(title.getBytes());
    	 }
    	 
    	 
     } catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if ( fos != null) { fos.close(); }
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

     
 }


 private static String get(String apiUrl, Map<String, String> requestHeaders){
     HttpURLConnection con = connect(apiUrl);
     try {
         con.setRequestMethod("GET");
         for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
             con.setRequestProperty(header.getKey(), header.getValue());
         }


         int responseCode = con.getResponseCode();
         if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
             return readBody(con.getInputStream());
         } else { // 에러 발생
             return readBody(con.getErrorStream());
         }
     } catch (IOException e) {
         throw new RuntimeException("API 요청과 응답 실패", e);
     } finally {
         con.disconnect();
     }
 }


 private static HttpURLConnection connect(String apiUrl){
     try {
         URL url = new URL(apiUrl);
         return (HttpURLConnection)url.openConnection();
     } catch (MalformedURLException e) {
         throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
     } catch (IOException e) {
         throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
     }
 }


 private static String readBody(InputStream body){
     InputStreamReader streamReader = new InputStreamReader(body);


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