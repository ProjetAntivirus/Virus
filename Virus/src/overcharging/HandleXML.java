package overcharging;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class HandleXML {
	
	private String country = "country";
	private String temperature = "temperature";
	private String humidity = "humidity";
	private String pressure = "pressure";
	private String urlString = null;
	private XmlPullParserFactory xFactory;
	public volatile boolean parsingComplete = true;
	
	public HandleXML(String url){
		this.urlString = url;
	}

	public String getCountry() {
		return country;
	}

	public String getTemperature() {
		return temperature;
	}

	public String getHumidity() {
		return humidity;
	}

	public String getPressure() {
		return pressure;
	}
	
	private void parseXMLAndStoreIt(XmlPullParser myParser){
		int event;
		String text = null;
		try{
			event = myParser.getEventType();
			while(event != XmlPullParser.END_DOCUMENT){
				String name = myParser.getName();
				switch (event){
					case XmlPullParser.START_TAG : 
						break;
					case XmlPullParser.TEXT : 
						text = myParser.getText();
						break;
					case XmlPullParser.END_TAG : 
						if(name.equals("country")){
							country = text;
						}
						else if(name.equals("humidity")){
							humidity = myParser.getAttributeValue(null, "value");
						}
						else if(name.equals("pressure")){
							pressure = myParser.getAttributeValue(null, "value");
						}				
						if(name.equals("temperature")){
							temperature = myParser.getAttributeValue(null, "value");
						}
						break;
					}
						event = myParser.next();
				}
				parsingComplete = false;
		}catch(Exception e ){
			e.printStackTrace();
		}
	}

	public void fetchXML() {
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try{
					URL url = new URL(urlString);
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
						connection.setReadTimeout(10000 /*ms*/);
						connection.setConnectTimeout(15000 /*ms*/);
						connection.setRequestMethod("GET");
						connection.setDoInput(true);
						connection.connect();
					InputStream stream = connection.getInputStream();
					
					xFactory = XmlPullParserFactory.newInstance();
					XmlPullParser myParser = xFactory.newPullParser();
					
					myParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false);
					myParser.setInput(stream, null);
					parseXMLAndStoreIt(myParser);
					stream.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		thread.start();
		
	}
	

}
