package view;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import overcharging.Maps;
import overcharging.WeatherService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import bdd.CommentsDataSource;
import bluetooth.BluetoothService;

import com.example.virus.R;

public class SaturationBattery extends Activity{
	
	private CommentsDataSource datasource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_saturation_battery);
		
		final Switch switchGoogle = (Switch) findViewById(R.id.switchGoogleMap);
		final Switch switchBluetooth = (Switch) findViewById(R.id.switchBluetooth);
		final Switch switchService = (Switch) findViewById(R.id.switchService);
		
		datasource = new CommentsDataSource(this);
		try {
			datasource.open();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		switchGoogle.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				Date d = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
				String s = dateFormat.format(d);
				if(isChecked){
					datasource.createComment("[GoogleMaps lancé]" + " - [" + s +"]");
					startActivity(new Intent(SaturationBattery.this, Maps.class));
				}
				else {
					datasource.createComment("[GoogleMaps arrêté]" + " - [" + s +"]");
				}
			}
		});
		switchBluetooth.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				Date d = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
				String s = dateFormat.format(d);
				if(isChecked){
					datasource.createComment("[Service bluetooth lancé]" + " - [" + s +"]");
					startService(new Intent(SaturationBattery.this, BluetoothService.class));
				}
				else{
					datasource.createComment("[Service bluetooth arrêté]" + " - [" + s +"]");
				}
			}
		});
		
		switchService.setOnCheckedChangeListener(new OnCheckedChangeListener() {	
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				Intent intent = new Intent(SaturationBattery.this, WeatherService.class);
				Date d = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
				String s = dateFormat.format(d);
				if(isChecked){
					datasource.createComment("[Service météo lancé]" + " - [" + s +"]");
					startService(intent);
				}
				else{
					stopService(intent);
					datasource.createComment("[Service météo arrêté]" + " - [" + s +"]");
				}
			}	
		});
		
		
	}
	
	public void saturationGoogleMap(View v){
		Boolean on = ((Switch) v).isChecked();
		Date d = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		String s = dateFormat.format(d);
		if(on){
			datasource.createComment("[GoogleMaps lancé]" + " - [" + s +"]");
			startActivity(new Intent(SaturationBattery.this, Maps.class));
		}
		else {
			datasource.createComment("[GoogleMaps arrêté]" + " - [" + s +"]");
		}
		
	}
	
	public void saturationBluetooth(View v){
		Boolean on = ((Switch) v).isChecked();
		Date d = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		String s = dateFormat.format(d);
		if(on){
			datasource.createComment("[Service bluetooth lancé]" + " - [" + s +"]");
			startService(new Intent(SaturationBattery.this, BluetoothService.class));
		}
		else{
			datasource.createComment("[Service bluetooth arrêté]" + " - [" + s +"]");
		}
	
	}

	public void onSwitchClicked(View v){
		Boolean on = ((Switch) v).isChecked();
		Intent intent = new Intent(SaturationBattery.this, WeatherService.class);
		Date d = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		String s = dateFormat.format(d);
		if(on){
			datasource.createComment("[Service météo lancé]" + " - [" + s +"]");
			startService(intent);
		}
		else{
			stopService(intent);
			datasource.createComment("[Service météo arrêté]" + " - [" + s +"]");
		}
	}
}
