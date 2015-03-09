package view;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import overcharging.WeatherService;
import bdd.CommentsDataSource;

import com.example.virus.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;

public class SaturationBattery extends Activity{
	
	private static Random rdm = new Random();
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
					datasource.createComment("[GoogleMaps lanc�]" + " - [" + s +"]");
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + rdm.nextFloat() + "," + rdm.nextFloat())));
				}
				else {
					datasource.createComment("[GoogleMaps arr�t�]" + " - [" + s +"]");
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
					datasource.createComment("[Service bluetooth lanc�]" + " - [" + s +"]");
					startActivity(new Intent(SaturationBattery.this, BluetoothChat.class));
				}
				else{
					datasource.createComment("[Service bluetooth arr�t�]" + " - [" + s +"]");
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
					datasource.createComment("[Service m�t�o lanc�]" + " - [" + s +"]");
					startService(intent);
				}
				else{
					stopService(intent);
					datasource.createComment("[Service m�t�o arr�t�]" + " - [" + s +"]");
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
			datasource.createComment("[GoogleMaps lanc�]" + " - [" + s +"]");
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + rdm.nextFloat() + "," + rdm.nextFloat())));
		}
		else {
			datasource.createComment("[GoogleMaps arr�t�]" + " - [" + s +"]");
		}
		
	}
	
	public void saturationBluetooth(View v){
		Boolean on = ((Switch) v).isChecked();
		Date d = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		String s = dateFormat.format(d);
		if(on){
			datasource.createComment("[Service bluetooth lanc�]" + " - [" + s +"]");
			startActivity(new Intent(SaturationBattery.this, BluetoothChat.class));
		}
		else{
			datasource.createComment("[Service bluetooth arr�t�]" + " - [" + s +"]");
		}
	
	}

	public void onSwitchClicked(View v){
		Boolean on = ((Switch) v).isChecked();
		Intent intent = new Intent(SaturationBattery.this, WeatherService.class);
		Date d = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		String s = dateFormat.format(d);
		if(on){
			datasource.createComment("[Service m�t�o lanc�]" + " - [" + s +"]");
			startService(intent);
		}
		else{
			stopService(intent);
			datasource.createComment("[Service m�t�o arr�t�]" + " - [" + s +"]");
		}
	}
}
