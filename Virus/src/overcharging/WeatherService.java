package overcharging;


import java.util.ArrayList;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class WeatherService extends Service{

	private ArrayList<String> locations = new ArrayList<String>();
	private String url1 = "http://api.openweathermap.org/data/2.5/weather?q=";
	private String url2 = "&mode=xml";
	private HandleXML obj; 
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {		
		locations.add("Paris");
		locations.add("Londres");
		locations.add("Clermont-Ferrand");
		locations.add("Kiev");
		locations.add("New-York");
		locations.add("Kyoto");		
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		for(String tmp : locations){
			try{
				String finalUrl = url1 + tmp + url2;
				obj = new HandleXML(finalUrl);
				obj.fetchXML();
				while(obj.parsingComplete);
				Thread.sleep(1000);
			}
			catch(InterruptedException e){};	
		}
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}
