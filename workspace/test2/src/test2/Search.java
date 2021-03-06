package test2;


//네이버 검색 API 예제 - blog 검색
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.swing.text.html.HTMLEditorKit.Parser;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Search {


 public static void main(String[] args) {
     String clientId = "kOtuvc0AiVzqQyXPAImE"; //애플리케이션 클라이언트 아이디값"
     String clientSecret = "PcR3sT8LVb"; //애플리케이션 클라이언트 시크릿값"


     String text = null;
     try {
         text = URLEncoder.encode("그린팩토리", "UTF-8");
     } catch (UnsupportedEncodingException e) {
         throw new RuntimeException("검색어 인코딩 실패",e);
     }


     String apiURL = "https://openapi.naver.com/v1/search/blog?query=" + text;    // json 결과
     //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과


     Map<String, String> requestHeaders = new HashMap<>();
     requestHeaders.put("X-Naver-Client-Id", clientId);
     requestHeaders.put("X-Naver-Client-Secret", clientSecret);
     String responseBody = "{\"lastBuildDate\": \"Thu, 27 May 2021 11:27:24 +0900\","
     		+ "\"total\": 9218,"
     		+ "\"start\": 1,"
     		+ "\"display\": 10,"
     		+ "\"items\": "
     		+ "[{\"title\": "
     		+ "\"제네시스 GV70 유리막코팅은 <b>그린팩토리</b>카케어 적극 추천\","
     		+ "\"link\": \"https:\\/\\/blog.naver.com\\/kwak4893?Redirect=Log&logNo=222257681840\","
     		+ "\"description\": \"안녕하세요 대전유리막코팅 전문점 <b>그린팩토리</b>카케어 곽영문입니다. 제네시스GV70 유리막코팅... 대표적으로 워터스팟, 스월마크등이 있는데, <b>그린팩토리</b>카케어에서는 유리막코팅 패키지에 이런 제거작업이... \",\"bloggername\": \"그린팩토리카케어 :: 블로그\",\"bloggerlink\": \"https://blog.naver.com/kwak4893\",\"postdate\": \"20210226\"},{\"title\": \"제라늄 씨앗 파종, 발아조건  (fr, <b>그린팩토리</b>님)\",\"link\": \"https:\\/\\/blog.naver.com\\/kke5825?Redirect=Log&logNo=222254719953\",\"description\": \"때마침 <b>그린팩토리</b>님께서 제라늄 씨앗 나눔을 하셨답니다. 네이버 리빙판에 노출이 되어 기념 나눔을... 제라늄 씨앗 이름은 그린스프링핑크 + 송살구 (1) 클로에 f2(2) 그린베아뜨리체 + 스오바(2) 엔젤크리스탈... \",\"bloggername\": \"행복 Serenade\","
     		+ "\"bloggerlink\": \"https://blog.naver.com/kke5825\","
     		+ "\"postdate\": \"20210224\"},"
     		+ "{\"title\": \"네이버 본사 <b>그린팩토리</b> 방문기 (네이버 라이브러리)\","
     		+ "\"link\": \"https:\\/\\/blog.naver.com\\/sera8668?Redirect=Log&logNo=221546264579\","
     		+ "\"description\": \"네이버 신규 사업에 대한 인터뷰가 있어서 처음으로 네이버 본사 <b>그린팩토리</b>에 다녀왔어요. 날씨가... <b>그린팩토리</b> 방문하실 때 신분증을 들고 오셔야 해요. 방문증을 받고 22층으로 올라갑니다. 프리랜서가... \",\"bloggername\": \"여행가세라 여행블로그\",\"bloggerlink\": \"https://blog.naver.com/sera8668\",\"postdate\": \"20190525\"},{\"title\": \"【Health】 내 몸속 신장 청소부 천연이뇨제 <b>그린팩토리</b>... \",\"link\": \"https:\\/\\/blog.naver.com\\/silvia0120?Redirect=Log&logNo=222347637545\",\"description\": \"내 몸속 신장 청소부 천연이뇨제 <b>그린팩토리</b> 청류차 마시면서 요로결석 배뇨장애 해결하세요! By 실비아... <b>그린팩토리</b> 청류차 1개월분 48,000원 By <b>그린팩토리</b> 친정어머니는 오래전부터 비뇨기과에서 배뇨장애로... \",\"bloggername\": \"행간으로 만나는 오감만족 Rainbow story\",\"bloggerlink\": \"https://blog.naver.com/silvia0120\","
     		+ "\"postdate\": \"20210512\"},{\"title\": \"신장에 좋은 음식. 내 몸의 신장 청소부 <b>그린팩토리</b> 청류 차\","
     		+ "\"link\": \"https:\\/\\/blog.naver.com\\/hyela120515?Redirect=Log&logNo=221587433685\","
     		+ "\"description\": \"녹더라고요 <b>그린 팩토리</b> 청류 차를 이렇게 매일 열흘 넘게 마시면서 관찰해보니 처음엔 소변 색이... 찌꺼기가 녹아 소변으로 빠졌나 봐요 <b>그린팩토리</b> 청류 차는 신장결석이나 요로결석. 통풍. 신염. 신... \",\"bloggername\": \"늘푸른바다\",\"bloggerlink\": \"https://blog.naver.com/hyela120515\",\"postdate\": \"20190716\"},{\"title\": \"파종제라늄 꽃,그린 나나[<b>그린팩토리</b>]\","
     		+ "\"link\": \"https:\\/\\/blog.naver.com\\/akubee?Redirect=Log&logNo=222304213026\","
     		+ "\"description\": \"주었습니다 (<b>그린팩토리</b> 개인적인 기준입니다) 숨막히는 겹은 아니지만 핑크색의 살짝 오므려 있는 장미화형에 귀엽고 발랄한 느낌의 꽃입니다 그린 나나 Green Nana 느낌대로이름도 귀엽고 심플하게 지어... \",\"bloggername\": \"바람의 정원\",\"bloggerlink\": \"https://blog.naver.com/akubee\",\"postdate\": \"20210409\"},{\"title\": \"붓기에좋은 천연이뇨제 <b>그린팩토리</b> 청류차 먹어봤어요~\",\"link\": \"https:\\/\\/blog.naver.com\\/fresh4864?Redirect=Log&logNo=222353581267\",\"description\": \"있겠지만 <b>그린팩토리</b> 청류차는 은은한 맛으로 애기도 잘먹을 수 있을 것같은 맛입니다 전 달고짜고매운걸 안좋아하는 사람이라 저한테딱이었어요 미지근하게 먹다가 냉장고에 넣어서 꺼내먹으니 갈증도 해소... \",\"bloggername\": \"경제적자유를꿈꾸는\",\"bloggerlink\": \"https://blog.naver.com/fresh4864\",\"postdate\": \"20210516\"},{\"title\": \"요로 결석 신장에좋은음식 <b>그린팩토리</b> 청류차\","
     		+ "\"link\": \"https:\\/\\/blog.naver.com\\/tpdud8705?Redirect=Log&logNo=222352271649\",\"description\": \"혈뇨에좋은 <b>그린팩토리</b> 청류차를 주문했어요 #<b>그린팩토리</b>청류차 는 신장에좋은 금전초,자바차,히비스커스를 농축해서 차로만든 제품이라 신장결석 , 요로결석 등 각종결석,혈뇨,신염,신증후군 등... \",\"bloggername\": \"• Dubu •\",\"bloggerlink\": \"https://blog.naver.com/tpdud8705\",\"postdate\": \"20210515\"},{\"title\": \"부종,배뇨장애 <b>그린팩토리</b> ★청류차★  효과있는거 아세요?\","
     		+ "\"link\": \"https:\\/\\/blog.naver.com\\/hms0077?Redirect=Log&logNo=222350520871\",\"description\": \"#천연이뇨제 #부종에좋은 #배뇨장애 #신장에좋은음식 #요로결석 #<b>그린팩토리</b>청류차 청류차 <b>그린팩토리</b> 맑을 청! 흐를 류! 바로 청류차인데요, 이것이 바로 천연이뇨제역할을 하는 차랍니다. 신장에 좋은 금전초... \",\"bloggername\": \"♥판돌군 이야기\",\"bloggerlink\": \"https://blog.naver.com/hms0077\","
     		+ "\"postdate\": \"20210514\"},{\"title\": \"[네이버 본사 카페] <b>그린팩토리</b> 그린카페\",\"link\": \"https:\\/\\/blog.naver.com\\/hoojungchung?Redirect=Log&logNo=221539050188\",\"description\": \"회사 사옥인 #<b>그린팩토리</b> 4층에 있는 카페가 새단장을 했다. 창을 막고 있던 구조물이 없어져 훨씬 밝아졌다. 참고로 사옥1층에 있는 카페와는 달리 4층의 그린카페는 출입증이 없으면 입장불가. 에스프레소... \",\"bloggername\": \"닥정의 순간적인 기록\",\"bloggerlink\": \"https://blog.naver.com/hoojungchung\","
     		+ "\"postdate\": \"20190516\"}]}\r\n" + 
     		" }";
     
     		// System.out.println(responseBody);
     
     		JSONParser parser = new JSONParser();
     		JSONObject obj = null;
     		try {
     			obj = (JSONObject)parser.parse("responseBody");
     			String lastBuildDate = obj.get("lastBuildDate").toString();
     			System.out.println("lastBuildDate" + lastBuildDate);
     					
     			
     		} catch (Exception e) {
				// TODO: handle exception
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