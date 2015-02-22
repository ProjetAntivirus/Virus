package overcharging;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class WebService {
	
	private final String URL = "http://www.webservicex.net/globalweather.asmx?WSDL";
	
	Gson gson;
	
	public WebService(){
		gson = new Gson();
	}
	
	private InputStream sendRequest(URL url) throws Exception{
		try{
			HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
			urlConnection.connect();
			
			if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
				return urlConnection.getInputStream();
			}
		}
		catch(Exception e){
			throw new Exception();
		}
		return null;
	}
	
	public Weather getWeather(){
		try{
			InputStream inputStream = sendRequest(new URL(URL));
			if(inputStream != null){
				InputStreamReader reader = new InputStreamReader(inputStream);
				return gson.fromJson(reader,new TypeToken<Weather>(){}.getType());
			}
		}catch(Exception e){
			Log.e("WebService", "Impossible de récupérer les données.");
		}
		return null;
	}
}
