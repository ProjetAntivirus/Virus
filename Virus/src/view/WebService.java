package view;

import com.example.virus.R;

import overcharging.HandleXML;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class WebService extends Activity{
	
	//private final String URL = "http://www.webservicex.net/globalweather.asmx?WSDL";
	private String url1 = "http://api.openweathermap.org/data/2.5/weather?q=";
	private String url2 = "&mode=xml";
	private HandleXML obj; 
	private EditText location;
	private TextView country, temperature, humidity, pressure;
	private TextView tCountry, tTemperature, tHumidity, tPressure;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weather);
		
		location = (EditText) findViewById(R.id.editTextLocation);
		country = (TextView) findViewById(R.id.textViewCountry);
		country.setVisibility(View.GONE);
		temperature = (TextView) findViewById(R.id.textViewTemperature);
		temperature.setVisibility(View.GONE);
		humidity = (TextView) findViewById(R.id.textViewHumidity);
		humidity.setVisibility(View.GONE);
		pressure = (TextView) findViewById(R.id.textViewPressure);
		pressure.setVisibility(View.GONE);
		
		tCountry = (TextView) findViewById(R.id.labelCountry);
		tCountry.setVisibility(View.GONE);
		tTemperature = (TextView) findViewById(R.id.labelTemperature);
		tTemperature.setVisibility(View.GONE);
		tHumidity = (TextView) findViewById(R.id.labelHumidity);
		tHumidity.setVisibility(View.GONE);
		tPressure = (TextView) findViewById(R.id.labelPressure);
		tPressure.setVisibility(View.GONE);
	}
	
	public void open(View view){
		String url = location.getText().toString();
		String finalUrl = url1 + url + url2;
		country.setText(finalUrl);
		
		obj = new HandleXML(finalUrl);
		obj.fetchXML();
		while(obj.parsingComplete);
		country.setText(obj.getCountry());
		tCountry.setVisibility(View.VISIBLE);
		country.setVisibility(View.VISIBLE);
		temperature.setText(obj.getTemperature());
		tTemperature.setVisibility(View.VISIBLE);
		temperature.setVisibility(View.VISIBLE);
		humidity.setText(obj.getHumidity());
		tHumidity.setVisibility(View.VISIBLE);
		humidity.setVisibility(View.VISIBLE);
		pressure.setText(obj.getPressure());
		tPressure.setVisibility(View.VISIBLE);
		pressure.setVisibility(View.VISIBLE);
	}
	
}
