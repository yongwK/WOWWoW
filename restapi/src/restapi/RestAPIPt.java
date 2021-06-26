package restapi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class RestAPIPt {
	//https://developers.kakao.com/docs/latest/ko/local/dev-guide#search-by-keyword
	private static String GEOCODE_URL="http://dapi.kakao.com/v2/local/search/address.json?query=";
	
	public static void main(String[] args) {
		try { //인코딩한 String을 넘겨야 원하는 데이터를 받을 수 있다.
			String address = URLEncoder.encode("대구광역시 중구 동성로2가 동성로2길 81", "UTF-8");
			URL obj = new URL(GEOCODE_URL+address);
			HttpURLConnection con = (HttpURLConnection)obj.openConnection();
			//get으로 받아오면 된다. 자세한 사항은 카카오개발자센터에 나와있다.
			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization","KakaoAK 5f2ebacde8e21b4688d2c5281a1d91aa");
			con.setRequestProperty("content-type", "application/json");
			con.setDoOutput(true); con.setUseCaches(false);
			con.setDefaultUseCaches(false);
			Charset charset = Charset.forName("UTF-8");
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));
			String inputLine; StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine+"\n");
			}
			//response 객체를 출력해보자
			System.out.println(response.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
