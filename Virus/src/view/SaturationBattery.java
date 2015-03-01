package view;

import java.util.Random;

import overcharging.WeatherService;

import com.example.virus.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class SaturationBattery extends Activity{
	
	private static Random rdm = new Random();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_saturation_battery);
		
		final Switch switchGoogle = (Switch) findViewById(R.id.switchGoogleMap);
		final Switch switchBluetooth = (Switch) findViewById(R.id.switchBluetooth);
		final Switch switchService = (Switch) findViewById(R.id.switchService);
		
	}
	
	public void saturationGoogleMap(View v){
		Boolean on = ((Switch) v).isChecked();
		if(on){
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + rdm.nextFloat() + "," + rdm.nextFloat())));
		}
		else {
			
		}
		
	}
	
	public void saturationBluetooth(View v){
		Boolean on = ((Switch) v).isChecked();
		if(on){
			startActivity(new Intent(SaturationBattery.this, BluetoothChat.class));
		}
		else{
			
		}
	
	}

	public void onSwitchClicked(View v){
		Boolean on = ((Switch) v).isChecked();
		Intent intent = new Intent(SaturationBattery.this, WeatherService.class);
		if(on){
			startService(intent);
		}
		else{
			stopService(intent);
		}
	}
}
